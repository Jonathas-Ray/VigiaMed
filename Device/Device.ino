#include "secrets.h"
#include "CapsulaFirebase.h"
#include "WiFiHelper.h"
#include "HeartRateSPO2.h"
#include <Wire.h>
#include <Adafruit_NeoPixel.h>

#define PINO_SDA 8
#define PINO_SCL 9
#define LEDRGB 48
#define LED_COUNT 1
#define ID_DO_APARELHO "vitals"

const int INTERVALO_ENVIO_BPM_MS = 3000;
const int INTERVALO_LOOP_MS = 2;
const int INTERVALO_LOG_HORARIO_MS = 3600000;

Adafruit_NeoPixel strip(LED_COUNT, LEDRGB, NEO_GRB + NEO_KHZ800);
WiFiHelper wifi(&strip);
CapsulaFirebase firebase;
HeartRateSPO2 hrSpO2(PINO_SDA, PINO_SCL);

String DEVICE_MAC_ADDRESS = "";
bool loginFirebaseSucesso = false;
unsigned long ultimoEnvioFB = 0;
unsigned long ultimoLogHorario = 0;
unsigned long ultimoCicloLeitura = 0;
enum EstadoErro { STATUS_OK, ERRO_WIFI, ERRO_FIREBASE_CONEXAO, ERRO_FIREBASE_ENVIO, ERRO_SENSOR_HR };
EstadoErro erroAtual = STATUS_OK; 

int BPMedia = 0;
int spo2Value = 0;

void gerenciarLED() {
    const unsigned long DURACAO_ERRO_FIXO = 3000;
    uint32_t corOK = strip.Color(0, 0, 160);
    
    switch (erroAtual) {
        case STATUS_OK:
            strip.setPixelColor(0, corOK);
            strip.show(); 
            break;
        case ERRO_WIFI:
            strip.setPixelColor(0, strip.Color(255, 255, 255));
            strip.show(); 
            delay(DURACAO_ERRO_FIXO);
            erroAtual = STATUS_OK;
            break;
        case ERRO_FIREBASE_CONEXAO:
            strip.setPixelColor(0, strip.Color(128, 0, 128));
            strip.show(); 
            delay(DURACAO_ERRO_FIXO);
            erroAtual = STATUS_OK;
            break;
        case ERRO_FIREBASE_ENVIO:
            strip.setPixelColor(0, strip.Color(255, 255, 0));
            strip.show(); 
            delay(DURACAO_ERRO_FIXO);
            erroAtual = STATUS_OK;
            break;
        case ERRO_SENSOR_HR:
            strip.setPixelColor(0, strip.Color(255, 0, 0)); 
            strip.show(); 
            delay(DURACAO_ERRO_FIXO);
            // Permanece no erro para indicar falha crítica do sensor
            break;
    }
}

void setup() {
    Serial.begin(115200);
    strip.begin();
    strip.setPixelColor(0, strip.Color(0, 0, 160));
    strip.show();

    if (hrSpO2.iniciarSensor() != CALIB_OK) {
        erroAtual = ERRO_SENSOR_HR;
        gerenciarLED();
    }
    
    wifi.begin(); 
    wifi.conectarWifi();

    if (WiFi.status() != WL_CONNECTED) {
        erroAtual = ERRO_WIFI;
        gerenciarLED();
    } else {
        DEVICE_MAC_ADDRESS = WiFi.macAddress(); 
        DEVICE_MAC_ADDRESS.replace(":", ""); 
        firebase.setDeviceID(DEVICE_MAC_ADDRESS); 
        
        loginFirebaseSucesso = firebase.login();
        if (!loginFirebaseSucesso) { 
            erroAtual = ERRO_FIREBASE_CONEXAO; 
            gerenciarLED();
        }
    }
}

void loop() {
    unsigned long agora = millis();
    
    hrSpO2.processarLeitura(BPMedia, spo2Value); 

    if (WiFi.status() == WL_CONNECTED) {
        if (!loginFirebaseSucesso) { 
            loginFirebaseSucesso = firebase.login();
            if (!loginFirebaseSucesso) {
                erroAtual = ERRO_FIREBASE_CONEXAO;
            }
        }
        
        if (loginFirebaseSucesso) {
            if ((BPMedia > 0 || spo2Value > 0) && agora - ultimoEnvioFB > INTERVALO_ENVIO_BPM_MS) {
                ultimoEnvioFB = agora;

                if (BPMedia > 0) {
                    if (!firebase.enviar("heartRate", String(BPMedia))) { 
                        erroAtual = ERRO_FIREBASE_ENVIO;
                    }
                }
                
                if (spo2Value > 0) {
                    if (!firebase.enviar("spo2", String(spo2Value))) {
                        erroAtual = ERRO_FIREBASE_ENVIO;
                    }
                } else {
                    firebase.enviar("spo2", String(spo2Value));
                }
            }
        }
    } else {
        wifi.conectarWifi();
        if (WiFi.status() != WL_CONNECTED) { 
            erroAtual = ERRO_WIFI;
        }
    }
    gerenciarLED(); 
}