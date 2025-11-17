//Aqui o Adafruit está sendo usado para controlar o LEDRGB nativo da placa esp32s3

#include "WiFiHelper.h"
#include "secrets.h"   // contém WIFI_SSIDS, WIFI_PASSWORDS, NUM_REDES

WiFiHelper::WiFiHelper(Adafruit_NeoPixel* stripLed) { //PONTEIRO NO CONSTRUTOR
    this->strip = stripLed;
    this->ledLigado = false;
}

void WiFiHelper::begin() {
    WiFi.mode(WIFI_STA);
    WiFi.onEvent([this](WiFiEvent_t event) { WifiReconectaAutomaticamente(event); });

    conectarWifi();
}

void WiFiHelper::conectarWifi() {
    for (int i = 0; i < NUM_REDES; i++) {
        Serial.printf("Tentando rede: %s\n", WIFI_SSIDS[i]);
        WiFi.begin(WIFI_SSIDS[i], WIFI_PASSWORDS[i]);

        unsigned long inicioWiFi = millis();

        while (WiFi.status() != WL_CONNECTED && millis() - inicioWiFi < 35000) {

            delay(600);
            Serial.print(".");

            // Pisca roxo
            if (!ledLigado) {
                strip->setPixelColor(0, strip->Color(128, 0, 128));
                strip->show();
                ledLigado = true;
            } else {
                strip->setPixelColor(0, 0);
                strip->show();
                ledLigado = false;
            }
        }

        if (WiFi.status() == WL_CONNECTED) {
            // Verde fixo 3s — "WiFi conectado"
            strip->setPixelColor(0, strip->Color(0, 255, 0));
            strip->show();
            delay(3000);
            strip->setPixelColor(0, 0);
            strip->show();
            return;
        } else {
            // Roxo fixo 3s — "Falha WiFi"
            strip->setPixelColor(0, strip->Color(128, 0, 128));
            strip->show();
            delay(3000);
            strip->setPixelColor(0, 0);
            strip->show();
        }
    }
}

void WiFiHelper::WifiReconectaAutomaticamente(WiFiEvent_t event) {
    if (event == SYSTEM_EVENT_STA_DISCONNECTED) {
        conectarWifi();
    }
}
