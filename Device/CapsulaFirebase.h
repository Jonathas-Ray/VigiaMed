#ifndef FIREBASE_HELPER_H
#define FIREBASE_HELPER_H

#include <Arduino.h>
#include <WiFi.h>
#include <WiFiClientSecure.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>

class CapsulaFirebase {
public:
    CapsulaFirebase();
    
    bool login();
    bool enviar(const String &tipo, const String &valor);

private:
    WiFiClientSecure cliente;
    HTTPClient http;
    String idToken;
};

#endif
