#ifndef HEARTRATE_SPO2_H
#define HEARTRATE_SPO2_H

#include <Arduino.h>
#include <MAX30105.h> 
#include <Wire.h>     
#include "heartRate.h" 

enum CalibracaoStatus {
    CALIB_OK,
    ERRO_SENSOR_NAO_ENCONTRADO,
    ERRO_CALIBRACAO
};

class HeartRateSPO2 {
public:
    HeartRateSPO2(uint8_t sdaPin, uint8_t sclPin);

    CalibracaoStatus iniciarSensor();
    void processarLeitura(int& bpmMedia, int& spo2Value);
    
    long getIRValue() const { return irValue; }
    long getRedValue() const { return redValue; }

private:
    MAX30105 sensor;
    uint8_t sda;
    uint8_t scl;

    long irValue;
    long redValue;
    
    const byte RATE_SIZE = 4;
    byte rates[4];
    byte rateSpot = 0;
    long lastBeat = 0;
    
    int spo2Value;
    const int R_SPO2_SIZE = 25;
    long R_IR_AC_Buf[25];
    long R_RED_AC_Buf[25];
    byte R_SpO2_Index = 0;

    const long LIMIAR_NO_FINGER = 5000; 

    void calibrarSensor();
    int calcularSpO2();
};

#endif