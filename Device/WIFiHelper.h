#ifndef WIFI_HELPER_H
#define WIFI_HELPER_H

#include <Arduino.h>
#include <WiFi.h>
#include <Adafruit_NeoPixel.h>

class WiFiHelper {
public:
    WiFiHelper(Adafruit_NeoPixel* stripLed);
    
    void begin();
    void conectarWifi();
    void gerenciarLedConexao();

private:
    
    Adafruit_NeoPixel* strip;
    bool ledLigado;
    
    unsigned long tempoUltimoPisca;
    uint32_t corPiscando;
};

#endif