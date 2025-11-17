// Aqui o Adafruit é usado para controlar o LED RGB nativo da placa esp32s3
#ifndef WIFI_HELPER_H
#define WIFI_HELPER_H

#include <WiFi.h>
#include <Adafruit_NeoPixel.h>

class WiFiHelper {
public:
    WiFiHelper(Adafruit_NeoPixel* strip);

    void begin();
    void conectarWifi();
    void WifiReconectaAutomaticamente(WiFiEvent_t event);

private:
    Adafruit_NeoPixel* strip;
    bool ledLigado;
};

#endif
