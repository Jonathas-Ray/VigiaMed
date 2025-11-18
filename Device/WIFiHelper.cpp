//Aqui o Adafruit está sendo usado para controlar o LEDRGB nativo da placa esp32s3

#include "WiFiHelper.h"
#include "secrets.h"
#include <Adafruit_NeoPixel.h>
#include <WiFi.h>

// Definição da função de tratamento de eventos (Handler)
// CORREÇÃO: A função deve ser estática para funcionar com WiFi.onEvent ou deve
// usar uma lambda capture (que é mais robusta, mas exige a implementação correta do
// handler na classe. Vamos ajustar a implementação para evitar o erro de escopo.

void WiFiHelper::WifiReconectaAutomaticamente(WiFiEvent_t event, WiFiEventInfo_t info) {
    // Para simplificar a compilação e evitar problemas de escopo, esta função
    // agora é um método da classe e será chamada via lambda.
    
    Serial.printf("[WiFi Event] Evento: %d\n", event);
    switch (event) {
        // CORREÇÃO: Substituindo SYSTEM_EVENT por WIFI_EVENT
        case WIFI_EVENT_STA_START:
            // Serial.println("Estação iniciando...");
            break;
        case WIFI_EVENT_STA_DISCONNECTED:
            // Este é o local ideal para adicionar a lógica de RECONEXÃO automática
            Serial.println("WiFi desconectado. Tentando reconectar...");
            // Exemplo de reconexão: WiFi.begin(WIFI_SSIDS[0], WIFI_PASSWORDS[0]); 
            break;
        // CORREÇÃO: Substituindo SYSTEM_EVENT por IP_EVENT
        case IP_EVENT_STA_GOT_IP:
            // Serial.printf("IP obtido: %s\n", WiFi.localIP().toString().c_str());
            break;
        default:
            break;
    }
}


WiFiHelper::WiFiHelper(Adafruit_NeoPixel* stripLed) { //PONTEIRO NO CONSTRUTOR
    this->strip = stripLed;
    this->ledLigado = false;
    // CORREÇÃO: Inicialização dos membros faltantes
    this->tempoUltimoPisca = 0;
    this->corPiscando = stripLed->Color(128, 0, 128); // Roxo para conectar
}

void WiFiHelper::begin() {
    WiFi.mode(WIFI_STA);
    
    // CORREÇÃO: A sintaxe do onEvent deve usar a lambda capture para chamar um método de instância
    WiFi.onEvent([this](WiFiEvent_t event, WiFiEventInfo_t info) { 
        this->WifiReconectaAutomaticamente(event, info); 
    });
    
    // A chamada para conectarWifi() ainda é BLOQUEANTE no SETUP.
    // conectarWifi(); // Não deve ser chamado aqui se for chamado no setup()
}

void WiFiHelper::gerenciarLedConexao() {
    unsigned long agora = millis();
    const unsigned long INTERVALO_PISCA = 300; // Pisca a cada 300ms

    // CORREÇÃO: Usando this-> para acessar membros da classe
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
            this->gerenciarLedConexao(); // Pisca o LED (CORREÇÃO: adicionado this->)
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