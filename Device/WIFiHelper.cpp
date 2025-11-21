#include "WiFiHelper.h"
#include "secrets.h"
#include <Adafruit_NeoPixel.h>
#include <WiFi.h>

WiFiHelper::WiFiHelper(Adafruit_NeoPixel* stripLed) {
    this->strip = stripLed;
    this->ledLigado = false;
    this->tempoUltimoPisca = 0;
    this->corPiscando = stripLed->Color(128, 0, 128); // Roxo para conectar
}

void WiFiHelper::begin() {
    WiFi.mode(WIFI_STA);
    
}

void WiFiHelper::gerenciarLedConexao() {
    unsigned long agora = millis();
    const unsigned long INTERVALO_PISCA = 300; // Pisca a cada 300ms

    if (agora - this->tempoUltimoPisca >= INTERVALO_PISCA) {
        this->tempoUltimoPisca = agora;

        if (this->ledLigado) {
            this->strip->setPixelColor(0, 0);
            this->ledLigado = false;
        } else {
            this->strip->setPixelColor(0, this->corPiscando);
            this->ledLigado = true;
        }
        this->strip->show();
    }
}

void WiFiHelper::conectarWifi() {
    for (int i = 0; i < NUM_REDES; i++) {
        Serial.printf("Tentando rede: %s\n", WIFI_SSIDS[i]);
        WiFi.begin(WIFI_SSIDS[i], WIFI_PASSWORDS[i]);

        unsigned long inicioWiFi = millis();
        
        // Timeout de 5.5s para cada rede
        while (WiFi.status() != WL_CONNECTED && millis() - inicioWiFi < 5500) {
            this->gerenciarLedConexao(); // Pisca o LED
            delay(50);
            Serial.print(".");
        }

        if (WiFi.status() == WL_CONNECTED) {
            Serial.printf("\nWiFi conectado! IP: %s\n", WiFi.localIP().toString().c_str());
            
            // Verde fixo 3s — "WiFi conectado"
            this->strip->setPixelColor(0, this->strip->Color(0, 255, 0));
            this->strip->show();
            delay(3000);
            this->strip->setPixelColor(0, 0);
            this->strip->show();
            return;
        } else {
            Serial.printf("\nFalha ao conectar com %s\n", WIFI_SSIDS[i]);
            // Roxo fixo 3s — "Falha WiFi" antes de tentar a próxima
            this->strip->setPixelColor(0, this->strip->Color(128, 0, 128));
            this->strip->show();
            delay(3000);
            this->strip->setPixelColor(0, 0);
            this->strip->show();
        }
    }

    Serial.println("Falha na conexão com todas as redes.");
    // Vermelho fixo — Falha geral
    this->strip->setPixelColor(0, this->strip->Color(255, 0, 0));
    this->strip->show();
}