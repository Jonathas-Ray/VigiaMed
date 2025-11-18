#ifndef AJUSTARSENSOR_H
#define AJUSTARSENSOR_H

#include <Arduino.h>
#include "MAX30105.h"

class AjustarSensor {
public:
    AjustarSensor(MAX30105* sensor);
    
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