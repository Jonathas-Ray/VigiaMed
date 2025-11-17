#ifndef SENSORADJUSTER_H
#define SENSORADJUSTER_H

#include <Arduino.h>
#include "MAX30105.h"

class SensorAdjuster {
public:
    SensorAdjuster(MAX30105* sensor);
    
    void ajustar();
    bool getAjusteConcluido() const;
    byte getIntensidadeLedAtual() const;
    void setIntensidadeLedAtual(byte intensidade);

private:
    int calcularQualidadeSinal(long valorIR);

    MAX30105* sensorParticula;
    bool ajusteConcluido;
    byte intensidadeLedAtual;
};

#endif