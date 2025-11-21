#include "secrets.h"
#include "CapsulaFirebase.h"
#include "WiFiHelper.h"
#include <Wire.h>
#include <Adafruit_NeoPixel.h>
#include "MAX30105.h"
#include "heartRate.h"

// --- Constantes e Pinos ---
#define PINO_SDA 8
#define PINO_SCL 9
#define LEDRGB 48
#define LED_COUNT 1
#define ID_DO_APARELHO "vitals"

//Leitura Atual
const byte RATE_SIZE = 4; //Quantos leituras para calcular a média
byte rates[RATE_SIZE]; //array para armazenar as leituras
byte rateSpot = 0;
long lastBeat = 0; //Última Batida
float BPM;
int BPMedia;

// --- Configurações de Tempo ---
const int INTERVALO_ENVIO_BPM_MS = 3000; // Enviar BPM a cada 1.2s
const int INTERVALO_LOOP_MS = 2;
const int INTERVALO_LOG_HORARIO_MS = 3600000; // 1 hora

// --- Objetos ---
MAX30105 sensorParticula;
Adafruit_NeoPixel strip(LED_COUNT, LEDRGB, NEO_GRB + NEO_KHZ800);
WiFiHelper wifi(&strip);
CapsulaFirebase firebase;

// --- Variáveis Globais de Estado ---
String DEVICE_MAC_ADDRESS = "";
bool loginFirebaseSucesso = false;
unsigned long ultimoEnvioFB = 0; // Será usado para o Log Horário
unsigned long ultimoLogHorario = 0;
unsigned long ultimoCicloLeitura = 0;
enum EstadoErro { STATUS_OK, ERRO_WIFI, ERRO_FIREBASE_CONEXAO, ERRO_FIREBASE_ENVIO }; 
EstadoErro erroAtual = STATUS_OK; // Inicialização

// --- Funções Auxiliares ---

void iniciarSensor() {
    Wire.begin(PINO_SDA, PINO_SCL);
    bool sensorEncontrado = false;
    strip.setPixelColor(0, strip.Color(255, 255, 255));
    strip.show();
    
    // Tenta inicializar o sensor
    do {
        sensorEncontrado = sensorParticula.begin(Wire, I2C_SPEED_FAST);
        delay(300);
    } while (!sensorEncontrado);

    sensorParticula.setup();
    const byte INTENSIDADE_PADRAO = 0x3F; // Usando intensidade padrão (0x3F) ou uma fixa, já que o AjustarSensor foi removido.
    sensorParticula.setPulseAmplitudeRed(INTENSIDADE_PADRAO);
    sensorParticula.setPulseAmplitudeIR(INTENSIDADE_PADRAO); // Adicionado para consistência
    sensorParticula.setPulseAmplitudeGreen(0);
}

void gerenciarLED() {
    const unsigned long DURACAO_ERRO_FIXO = 3000; // 3 segundos fixo
    uint32_t corOK = strip.Color(0, 0, 160); // Azul (Padrão)
    
    // Gerencia a exibição dos erros
    switch (erroAtual) {
        case STATUS_OK:
            strip.setPixelColor(0, corOK);
            strip.show();
            break;
        case ERRO_WIFI:
            // Branco Fixo (3s)
            strip.setPixelColor(0, strip.Color(255, 255, 255));
            strip.show();
            delay(DURACAO_ERRO_FIXO);
            erroAtual = STATUS_OK;
            break;
        case ERRO_FIREBASE_CONEXAO:
            // Lilás Fixo (3s)
            strip.setPixelColor(0, strip.Color(128, 0, 128));
            strip.show();
            delay(DURACAO_ERRO_FIXO);
            erroAtual = STATUS_OK;
            break;
        case ERRO_FIREBASE_ENVIO:
            // Amarelo Fixo (3s)
            strip.setPixelColor(0, strip.Color(255, 255, 0));
            strip.show();
            delay(DURACAO_ERRO_FIXO);
            erroAtual = STATUS_OK;
            break;
    }
}

void setup() {
    Serial.begin(115200);
    strip.begin();
    strip.setPixelColor(0, strip.Color(0, 0, 160));
    strip.show();

    iniciarSensor();
    wifi.begin();
    wifi.conectarWifi();
    
    if (WiFi.status() != WL_CONNECTED) {
        erroAtual = ERRO_WIFI;
        gerenciarLED();
    } else {
        DEVICE_MAC_ADDRESS = WiFi.macAddress();
        DEVICE_MAC_ADDRESS.replace(":", "");
        firebase.setDeviceID(DEVICE_MAC_ADDRESS); //Tá passando para ser o nome do aparelho
        loginFirebaseSucesso = firebase.login();
        if (!loginFirebaseSucesso) {
            erroAtual = ERRO_FIREBASE_CONEXAO;
            gerenciarLED();
        }
    }
}

void loop() {
    unsigned long agora = millis();
    long irValue = sensorParticula.getIR();

    if (checkForBeat(irValue) == true){
        //piscar vermelho aqui, Gemini!!!
        long delta = millis() - lastBeat;
        lastBeat = millis();

        BPM = 60 / (delta / 1000.0);

        if (BPM < 255 && BPM > 20){
        rates[rateSpot++] = (byte)BPM; //Armazena no Array
        rateSpot %= RATE_SIZE; //Wrap variable

        //Take average of readings
        BPMedia = 0;
        for (byte x = 0 ; x < RATE_SIZE ; x++)
            BPMedia += rates[x];
        BPMedia /= RATE_SIZE;
        }
    }
    
    if (WiFi.status() == WL_CONNECTED) {
        if (!loginFirebaseSucesso) { // Tenta reconectar ao Firebase se o token expirou
            Serial.println("Tentando reconexão ao Firebase...");
            loginFirebaseSucesso = firebase.login(); 
            if (!loginFirebaseSucesso) {
                erroAtual = ERRO_FIREBASE_CONEXAO;
            }
        }
        
        if (loginFirebaseSucesso) {
            // A. ENVIO DO BPM ATUAL (1.2s)
            if (BPMedia > 0 && agora - ultimoEnvioFB > INTERVALO_ENVIO_BPM_MS) {
                ultimoEnvioFB = agora; 

                if (!firebase.enviar("heartRate", String(BPM))) {
                    erroAtual = ERRO_FIREBASE_ENVIO;
                }
                if (!firebase.enviar("heartRateAverage", String(BPMedia))) {
                    erroAtual = ERRO_FIREBASE_ENVIO;
                }
            }
        }
    } else {
        wifi.conectarWifi();
        if (WiFi.status() != WL_CONNECTED) {
            erroAtual = ERRO_WIFI; //Gestão de erros usa isso pra piscar na cor do erro
        }
    }
    gerenciarLED(); //Expõe o erro atual
}