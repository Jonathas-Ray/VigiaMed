#include "secrets.h"
#include "CapsulaFirebase.h"
#include "WiFiHelper.h"          
#include <Wire.h>
#include <Adafruit_NeoPixel.h>
#include "MAX30105.h"
#include "BPMHelper.h"
#include "AjustarSensor.h"

//Constantes e Pinos
#define ID_DO_APARELHO "vitals" 
#define PINO_SDA 3
#define PINO_SCL 9
#define PIN_INTERRUPCAO 4
#define LEDRGB 48
#define LED_COUNT 1

//Objetos
MAX30105 sensorParticula;
Adafruit_NeoPixel strip(LED_COUNT, LEDRGB, NEO_GRB + NEO_KHZ800);
WiFiHelper wifi(&strip);
CapsulaFirebase firebase;
BPMHelper bpmHelper;
AjustarSensor ajustarSensor(&sensorParticula);

//Variáveis globais
volatile bool sensorQuaseCheio = false;
bool piscando = false;
unsigned long inicioPiscada = 0;
const unsigned long DURACAO_PISCADA = 30;
String DEVICE_MAC_ADDRESS = ""; // Variável global para armazenar o MAC Address


void IRAM_ATTR FIFOPronto() {
  sensorQuaseCheio = true;
}

void gerenciarLED() {
  unsigned long agora = millis();
  
  if (piscando) {
    if (agora - inicioPiscada >= DURACAO_PISCADA) {
      piscando = false;
      strip.setPixelColor(0, strip.Color(0, 0, 160));
      strip.show();
    }
  } else {
    strip.setPixelColor(0, strip.Color(0, 0, 160));
    strip.show();
  }
}

void iniciarSensor() {
  bool sensorEncontrado = false;
  Wire.begin(PINO_SDA, PINO_SCL);
  pinMode(PIN_INTERRUPCAO, INPUT_PULLUP);

  strip.setPixelColor(0, strip.Color(255, 255, 255));
  strip.show();
  
  do {
    sensorEncontrado = sensorParticula.begin(Wire, I2C_SPEED_FAST);
    strip.setPixelColor(0, strip.Color(255, 255, 255));
    strip.show();
    
    // CORREÇÃO: Retorna à configuração padrão (compatível com sua biblioteca)
    sensorParticula.setup(); 
    
    sensorParticula.setFIFOAlmostFull(4);
    sensorParticula.enableAFULL();
    sensorParticula.setPulseAmplitudeRed(ajustarSensor.getIntensidadeLedAtual());
    sensorParticula.setPulseAmplitudeGreen(0);
    attachInterrupt(digitalPinToInterrupt(PIN_INTERRUPCAO), FIFOPronto, FALLING);
    delay(300);
  } while (!sensorEncontrado);
  strip.setPixelColor(0, strip.Color(0, 0, 0));
  strip.show();
}

void setup() {
  Serial.begin(115200);
  strip.begin();
  strip.setPixelColor(0, strip.Color(0, 0, 160));
  strip.show();
  iniciarSensor();
  ajustarSensor.ajustar();
  wifi.begin();
  wifi.conectarWifi();
  
  // OBTENÇÃO E CONFIGURAÇÃO DO ID DO DISPOSITIVO (MAC ADDRESS)
  DEVICE_MAC_ADDRESS = WiFi.macAddress();
  DEVICE_MAC_ADDRESS.replace(":", ""); 
  Serial.printf("ID do Dispositivo (MAC): %s\n", DEVICE_MAC_ADDRESS.c_str());
  
  firebase.setDeviceID(DEVICE_MAC_ADDRESS);
  
  firebase.login();
  bpmHelper.begin();
}

void loop() {
  gerenciarLED();
  
  if (!sensorQuaseCheio) return;

  sensorQuaseCheio = false;
  byte amostrasDisponiveis = sensorParticula.available();
  
  for (int i = 0; i < amostrasDisponiveis; i++) {
    int redValue = sensorParticula.getFIFORed();
    int irValue = sensorParticula.getFIFOIR();
    
    bool batimentoDetectado = bpmHelper.processarAmostraBatimento(redValue, millis());
    
    if (batimentoDetectado) {
      piscando = true;
      inicioPiscada = millis();
      strip.setPixelColor(0, strip.Color(255, 0, 0));
      strip.show();
      
      int bpm = bpmHelper.getMediaBatimentos();
      if (bpm > 0) {
        // Envia o valor usando "heartRate"
        firebase.enviar("heartRate", String(bpm));
      }
    }
    
    sensorParticula.nextSample();
  }
}