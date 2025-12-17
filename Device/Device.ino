#include "secrets.h"
#include "CapsulaFirebase.h"
#include "WiFiHelper.h"          
#include <Wire.h>
#include <Adafruit_NeoPixel.h>
#include "Protocentral_MAX30205.h" 

#include "MAX30105.h"
#include "BPMHelper.h"    
#include "SPO2Processor.h" 
#include "AjustarSensor.h" 

#define ID_DO_APARELHO "vitals" 
#define PINO_SDA 8
#define PINO_SCL 9
#define LEDRGB 48
#define LED_COUNT 1

// Constantes de Controle
const int INTERVALO_ENVIO_FB_MS = 1200;
const int INTERVALO_LEITURA_TEMP_MS = 1000;
const int INTERVALO_ALTERNANCIA_MS = 3000; // Alterna a cada 3 segundos

Adafruit_NeoPixel strip(LED_COUNT, LEDRGB, NEO_GRB + NEO_KHZ800);
WiFiHelper wifi(&strip);
CapsulaFirebase firebase;
MAX30205 sensorDeTemperatura; 

MAX30105 sensorParticula;
BPMHelper bpmHelper;
SPO2Processor spo2Processor(&sensorParticula);
AjustarSensor ajustarSensor(&sensorParticula); 

// Variáveis para armazenar as INTENSIDADES OTIMIZADAS (Calibradas no setup)
byte IR_BPM_OTIMIZADO = 0x1F;
byte RED_BPM_OTIMIZADO = 0x1F;
byte RED_SPO2_OTIMIZADO = 0x1F; 

String DEVICE_MAC_ADDRESS = "";
bool loginFirebaseSucesso = false;
unsigned long ultimoEnvioFB = 0;
unsigned long ultimoCicloLeitura = 0; 

enum EstadoErro { STATUS_OK, ERRO_WIFI, ERRO_FIREBASE_CONEXAO, ERRO_FIREBASE_ENVIO, ERRO_SENSOR_HR };
EstadoErro erroAtual = STATUS_OK;
int BPMedia = 0;
int spo2Value = 0;
float temperatura = 0.0;
bool piscando = false;
unsigned long inicioPiscada = 0;
const unsigned long DURACAO_PISCADA = 30;

// Variáveis de Estado para Alternância
enum ModoOperacao { MODO_BPM, MODO_SPO2 };
ModoOperacao modoAtual = MODO_BPM; 
unsigned long ultimoAlternancia = 0;

// ===================================
// FUNÇÕES DE SERVIÇO (MANTIDAS)
// ===================================

void gerenciarLED() {
  unsigned long agora = millis();
  uint32_t corPadrao = strip.Color(0, 0, 160);

  if (erroAtual != STATUS_OK) {
    switch (erroAtual) {
        case ERRO_WIFI: strip.setPixelColor(0, strip.Color(255, 255, 255)); break;
        case ERRO_FIREBASE_CONEXAO: strip.setPixelColor(0, strip.Color(128, 0, 128)); break;
        case ERRO_FIREBASE_ENVIO: strip.setPixelColor(0, strip.Color(255, 255, 0)); break;
        case ERRO_SENSOR_HR: strip.setPixelColor(0, strip.Color(255, 0, 0)); break;
        default: break; 
    }
    strip.show();
    return;
  }

  if (piscando) {
    if (agora - inicioPiscada >= DURACAO_PISCADA) {
      piscando = false;
      strip.setPixelColor(0, corPadrao);
    } 
  } else {
    strip.setPixelColor(0, corPadrao);
  }
  strip.show();
}

void iniciarSensor() {
  bool sensorEncontrado = false;
  Wire.begin(PINO_SDA, PINO_SCL);
  strip.setPixelColor(0, strip.Color(255, 255, 255));
  strip.show();
  do {
    sensorEncontrado = sensorParticula.begin(Wire, I2C_SPEED_FAST);
    delay(100);
  } while (!sensorEncontrado);

  sensorParticula.setup(); 
  sensorParticula.setPulseAmplitudeGreen(0);
  strip.setPixelColor(0, strip.Color(0, 0, 0));
  strip.show();
}

float lerTemperaturaManual() {
  uint16_t rawValue = 0;
  
  Wire.beginTransmission(0x48); 
  Wire.write(0x00); 
  Wire.endTransmission(false);
  if (Wire.requestFrom(0x48, 2) == 2) {
    byte msb = Wire.read(); 
    byte lsb = Wire.read();
    rawValue = (msb << 8) | lsb;
    rawValue = rawValue & 0x7FFF;
  } else {
    return -99.0;
  }
  
  float temperatura = (float)rawValue / 1024.0;
  return temperatura;
}


void setup() {
  Serial.begin(115200);
  strip.begin();
  strip.setPixelColor(0, strip.Color(0, 0, 160));
  strip.show();
  
  iniciarSensor();

  // ==========================================================
  // PASSO 1: CALIBRAÇÃO ÚNICA DE BPM E ARMAZENAMENTO
  // ==========================================================
  Serial.println("Calibrando para BPM (busca o melhor sinal de pulso)...");
  ajustarSensor.ajustar(); 
  
  RED_BPM_OTIMIZADO = ajustarSensor.getIntensidadeLedAtual();
  IR_BPM_OTIMIZADO = ajustarSensor.getIntensidadeLedAtual();
  Serial.printf("BPM Otimizado (RED/IR): 0x%X\n", RED_BPM_OTIMIZADO);
  
  // ==========================================================
  // PASSO 2: CALIBRAÇÃO ÚNICA DE SPO2 E ARMAZENAMENTO
  // ==========================================================
  Serial.println("Ajustando RED para SpO2 (evitar saturação)...");
  if (spo2Processor.calibrarSensor() != CALIB_OK) {
        erroAtual = ERRO_SENSOR_HR;
  }
  RED_SPO2_OTIMIZADO = spo2Processor.getRedPulseAmplitude();
  Serial.printf("SpO2 Otimizado (RED): 0x%X\n", RED_SPO2_OTIMIZADO);
  
  // Configura o sensor para o modo inicial (MODO_BPM)
  sensorParticula.setPulseAmplitudeRed(RED_BPM_OTIMIZADO);
  sensorParticula.setPulseAmplitudeIR(IR_BPM_OTIMIZADO);

  // ... (Restante do setup)
  bpmHelper.begin();
  if (!sensorDeTemperatura.scanAvailableSensors()){
        // ...
  } else {
        sensorDeTemperatura.begin();
  }
  
  wifi.begin();
  wifi.conectarWifi();
  
  DEVICE_MAC_ADDRESS = WiFi.macAddress();
  DEVICE_MAC_ADDRESS.replace(":", ""); 
  firebase.setDeviceID(DEVICE_MAC_ADDRESS);
  loginFirebaseSucesso = firebase.login();
  // ...
  gerenciarLED();
}

// ===========================================================
// LOOP PRINCIPAL COM ALTERNÂNCIA RÁPIDA DE CONFIGURAÇÕES DE LED
// ===========================================================

void loop() {
  gerenciarLED();
  unsigned long agora = millis();
  
  // LÓGICA DE ALTERNÂNCIA RÁPIDA (Revezamento dos valores armazenados)
  if (agora - ultimoAlternancia >= INTERVALO_ALTERNANCIA_MS) {
      ultimoAlternancia = agora;

      if (modoAtual == MODO_BPM) {
          // Mudar para MODO_SPO2: Aplica a configuração SpO2
          modoAtual = MODO_SPO2;
          sensorParticula.setPulseAmplitudeRed(RED_SPO2_OTIMIZADO);
          sensorParticula.setPulseAmplitudeIR(IR_BPM_OTIMIZADO); 
          Serial.println("MODO: SpO2 (Aplicando RED otimizado)");
          
      } else {
          // Mudar para MODO_BPM: Aplica a configuração BPM
          modoAtual = MODO_BPM;
          sensorParticula.setPulseAmplitudeRed(RED_BPM_OTIMIZADO);
          sensorParticula.setPulseAmplitudeIR(IR_BPM_OTIMIZADO);
          Serial.println("MODO: BPM (Aplicando RED/IR otimizado)");
      }
      
      // CRÍTICO: Estabiliza as leituras após a troca de LED
      bpmHelper.resetBaseline();
      spo2Processor.resetBuffer(); // CORREÇÃO: Usando o novo método público
  }
  
  // Leitura e Processamento (com a configuração de LED ativa no momento)
  int redValue = sensorParticula.getRed();
  int irValue = sensorParticula.getIR();

  // Filtro de "Dedo"
  if (irValue < 500) { 
      spo2Value = 0; 
      BPMedia = 0;
      delay(2);
      return;
  }

  // 1. Processamento de BPM (Sempre ativo)
  bool batimentoDetectado = bpmHelper.processarAmostraBatimento(redValue, millis());
  if (batimentoDetectado) {
    piscando = true;
    inicioPiscada = millis();
    strip.setPixelColor(0, strip.Color(255, 0, 0));
    BPMedia = bpmHelper.getMediaBatimentos();
  }

  // 2. Processamento de SpO2 
  int tempSpo2 = spo2Processor.processarSpO2(redValue, irValue);
  
  if (modoAtual == MODO_SPO2 && tempSpo2 > 0) {
      spo2Value = tempSpo2;
  } else if (modoAtual == MODO_BPM) {
      spo2Value = 0; 
  }

  // Leitura de Temperatura (mantida)
  if (agora - ultimoCicloLeitura >= INTERVALO_LEITURA_TEMP_MS) { 
    ultimoCicloLeitura = agora;
    temperatura = lerTemperaturaManual();
  }

  // Envio para Firebase (mantido)
  if (WiFi.status() == WL_CONNECTED && loginFirebaseSucesso) {
    if (agora - ultimoEnvioFB > INTERVALO_ENVIO_FB_MS) {
      ultimoEnvioFB = agora;
      
      if (BPMedia > 0) {
          firebase.enviar("heartRate", String(BPMedia)); 
      }
      
      if (spo2Value > 0) { 
          firebase.enviar("spo2", String(spo2Value)); 
      }

      firebase.enviar("Temperature", String(temperatura, 2)); 
    }
  } 
  
  delay(2); 
}