#include "CapsulaFirebase.h"
#include "secrets.h"
#include <WiFi.h>
#include <WiFiClientSecure.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>

const char* FIREBASE_ROOT_PATH = "vitals"; 

CapsulaFirebase::CapsulaFirebase() {
    cliente.setInsecure();
    cliente.setTimeout(10000);
}

void CapsulaFirebase::setDeviceID(const String& id) {
    this->deviceID = id;
}

bool CapsulaFirebase::login() {
    if (WiFi.status() != WL_CONNECTED) {
        return false;
    }

    String url = 
        String("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=") 
        + FIREBASE_API_KEY;

    http.begin(cliente, url);
    http.setTimeout(10000);
    http.addHeader("Content-Type", "application/json");

    StaticJsonDocument<256> doc;
    doc["email"] = FIREBASE_EMAIL;
    doc["password"] = FIREBASE_PASSWORD;
    doc["returnSecureToken"] = true;

    String payload;
    serializeJson(doc, payload);

    int code = http.POST(payload);
    
    if (code < 0) {
        http.end();
        return false;
    }
    
    String resposta = http.getString();

    if (code == HTTP_CODE_OK) {
        StaticJsonDocument<512> docResp;
        deserializeJson(docResp, resposta);
        idToken = docResp["idToken"].as<String>();

        http.end();
        return true;
    }
    http.end();
    return false;
}

bool CapsulaFirebase::enviar(const String &tipo, const String &valor) {
    if (WiFi.status() != WL_CONNECTED || idToken.length() == 0 || deviceID.length() == 0) {
        return false;
    }

    String url = 
        String(FIREBASE_DB_URL) + "/" + FIREBASE_ROOT_PATH + "/" + this->deviceID + "/" + tipo + 
        ".json?auth=" + idToken;
    
    http.begin(cliente, url);
    http.setTimeout(10000);
    http.addHeader("Content-Type", "application/json");

    StaticJsonDocument<128> doc;
    doc["value"] = valor; 

    String payload;
    serializeJson(doc, payload);

    int code = http.PUT(payload);

    if (code > 0) {
        if (code == HTTP_CODE_OK) {
            http.end();
            return true;
        } else {
            http.end();
            return false;
        }
    } else {
        http.end();
        return false;
    }
}