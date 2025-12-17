#ifndef AJUSTARSENSOR_H
#define AJUSTARSENSOR_H

#include <Arduino.h>
#include "MAX30105.h"

class AjustarSensor {
public:
    AjustarSensor(MAX30105* sensor);
    
    void ajustar();
    bool getAjusteConcluido() const;
    
    // Getter para a intensidade otimizada de BPM (usada para RED e IR)
    byte getIntensidadeLedAtual() const; 
    
private:
    int calcularQualidadeSinal(long valorIR);

    MAX30105* sensorParticula;
    bool ajusteConcluido;
    byte intensidadeLedAtual;
};

#endif