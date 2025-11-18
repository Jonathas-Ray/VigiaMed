#include "AjustarSensor.h"
#include <Arduino.h>

AjustarSensor::AjustarSensor(MAX30105* sensor) : 
    sensorParticula(sensor),
    ajusteConcluido(false),
    intensidadeLedAtual(0x1F) {}

void AjustarSensor::ajustar() {
    byte intensidades[] = {0x1F, 0x2F, 0x3F, 0x4F, 0x5F, 0x6F};
    byte totalIntensidades = sizeof(intensidades) / sizeof(intensidades[0]);
    byte melhorIntensidade = intensidades[0];
    int melhorQualidade = 0;

    for (byte i = 0; i < totalIntensidades; i++) {
        sensorParticula->setPulseAmplitudeRed(intensidades[i]);
        sensorParticula->setPulseAmplitudeIR(intensidades[i]);

        unsigned long inicio = millis();
        unsigned long watchdog = millis();
        long somaIR = 0;
        int amostras = 0;
        int batimentos = 0;
        float alpha = 0.10;
        float ema = 0;
        long baseline_local = 5000;
        float thresholdFator = 1.15;
        long threshold = baseline_local * thresholdFator;
        bool valorInicialDefinido = false;

        while (millis() - inicio < 2500) {
            if (millis() - watchdog > 300) {
                Serial.println("Watchdog: sensor travado, pulando intensidade.");
                break;
            }

            long ir = sensorParticula->getIR();
            if (ir > 50) watchdog = millis();

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
                delay(160);
            }
            delay(5);
        }

        if (amostras == 0) continue;

        long mediaIR = somaIR / amostras;
        int qualidade = calcularQualidadeSinal(mediaIR);
        qualidade += min(batimentos * 10, 20);

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
    if (valorIR > 8000)  return 30;
    if (valorIR > 3000)  return 10;
    return 0;
}

bool AjustarSensor::getAjusteConcluido() const {
    return ajusteConcluido;
}

byte AjustarSensor::getIntensidadeLedAtual() const {
    return intensidadeLedAtual;
}

void AjustarSensor::setIntensidadeLedAtual(byte intensidade) {
    intensidadeLedAtual = intensidade;
}