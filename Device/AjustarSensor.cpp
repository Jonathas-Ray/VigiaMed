#include "AjustarSensor.h"
#include <Arduino.h>

AjustarSensor::AjustarSensor(MAX30105* sensor) : 
    sensorParticula(sensor),
    ajusteConcluido(false),
    intensidadeLedAtual(0x1F) {}

void AjustarSensor::ajustar() {
    byte intensidades[] = {0x1F, 0x2B, 0x2F, 0x3B, 0x3F, 0x4B, 0x4F, 0x5F, 0x6F};
    byte totalIntensidades = sizeof(intensidades) / sizeof(intensidades[0]);
    byte melhorIntensidade = intensidades[0];
    int melhorQualidade = 0;

    for (byte i = 0; i < totalIntensidades; i++) {
        sensorParticula->setPulseAmplitudeRed(intensidades[i]);
        sensorParticula->setPulseAmplitudeIR(intensidades[i]);

        unsigned long inicio = millis();
        // unsigned long watchdog = millis(); // Removido
        long somaIR = 0;
        int amostras = 0;
        int batimentos = 0;
        float alpha = 0.10;
        float ema = 0;
        long baseline_local = 5000;
        float thresholdFator = 1.15;
        long threshold = baseline_local * thresholdFator;
        bool valorInicialDefinido = false;

        while (millis() - inicio < 2500) { // Tempo de teste: 2.5s
            long ir = sensorParticula->getIR();
            if (ir == 0) continue;

            if (!valorInicialDefinido) {
                ema = ir;
                valorInicialDefinido = true;
            } else {
                ema = alpha * ir + (1 - alpha) * ema;
            }

            somaIR += ir;
            amostras++;
            baseline_local = (baseline_local * 0.999) + (ema * 0.001);
            threshold = baseline_local * thresholdFator;

            if (ema > threshold) {
                batimentos++;
                delay(160); // Janela de inibição de pico
            }
            delay(5);
        }

        if (amostras == 0) continue;

        long mediaIR = somaIR / amostras;
        int qualidade = calcularQualidadeSinal(mediaIR);
        qualidade += min(batimentos * 10, 20); // Adiciona um bônus por detecção de batimento

        if (qualidade > melhorQualidade) {
            melhorQualidade = qualidade;
            melhorIntensidade = intensidades[i];
        }
    }

    sensorParticula->setPulseAmplitudeRed(melhorIntensidade);
    sensorParticula->setPulseAmplitudeIR(melhorIntensidade);
    intensidadeLedAtual = melhorIntensidade;
    ajusteConcluido = true;
}

int AjustarSensor::calcularQualidadeSinal(long valorIR) {
    if (valorIR > 80000) return 90;
    if (valorIR > 40000) return 70;
    if (valorIR > 15000) return 50;
    return 10;
}

bool AjustarSensor::getAjusteConcluido() const {
    return ajusteConcluido;
}

byte AjustarSensor::getIntensidadeLedAtual() const {
    return intensidadeLedAtual;
}
// REMOVIDO: void AjustarSensor::setIntensidadeLedAtual(byte intensidade) {} (A definição estava aqui)