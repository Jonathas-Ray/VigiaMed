#include "CapsulaFirebase.h"
#include "secrets.h"

CapsulaFirebase::CapsulaFirebase() {
    cliente.setInsecure();
    cliente.setTimeout(10000); // Timeout global do cliente WiFi (10 segundos)
}

bool CapsulaFirebase::login() {
    if (WiFi.status() != WL_CONNECTED) {
        return false;
    }

    String url = 
        String("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=") 
        + FIREBASE_API_KEY;

    http.begin(cliente, url);
    http.setTimeout(10000); // Timeout específico do HTTP (10 segundos)
    http.addHeader("Content-Type", "application/json");

    StaticJsonDocument<256> doc;
    doc["email"] = FIREBASE_EMAIL;
    doc["password"] = FIREBASE_PASSWORD;
    doc["returnSecureToken"] = true;

    String payload;
    serializeJson(doc, payload);

    int code = http.POST(payload);
    
    // Verificação específica para timeout
    if (code < 0) {
        Serial.printf("ERRO HTTP: %d - %s\n", code, http.errorToString(code).c_str());
        http.end();
        cliente.stop();
        return false;
    }
    
    String resposta = http.getString();

    if (code == HTTP_CODE_OK) {
        StaticJsonDocument<512> docResp;
        deserializeJson(docResp, resposta);

        idToken = docResp["idToken"].as<String>();
        
        http.end();
        cliente.stop();
        return true;
    }

    Serial.printf("Login falhou - Código HTTP: %d\n", code);
    http.end();
    cliente.stop();
    return false;
}

bool CapsulaFirebase::enviar(const String &tipo, const String &valor) {
    if (WiFi.status() != WL_CONNECTED || idToken.length() == 0) {
        return false;
    }

    String url = 
        String(FIREBASE_DB_URL) + "/vitals/04CDF8/" + tipo + 
        ".json?auth=" + idToken;

    http.begin(cliente, url);
    http.setTimeout(10000); //Timeout específico do HTTP (10 segundos)
    http.addHeader("Content-Type", "application/json");

    int code = http.PUT("\"" + valor + "\"");
    
    // Verificação específica para timeout
    if (code < 0) {
        Serial.printf("ERRO HTTP: %d - %s\n", code, http.errorToString(code).c_str());
        http.end();
        cliente.stop();
        return false;
    }

    http.end();
    cliente.stop();

    return (code == HTTP_CODE_OK);
}