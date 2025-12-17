#ifndef SPO2PROCESSOR_H
#define SPO2PROCESSOR_H

#include <Arduino.h>
#include <MAX30105.h> 

enum CalibracaoStatus {
    CALIB_OK,
    ERRO_CALIBRACAO
};

class SPO2Processor {
public:
    SPO2Processor(MAX30105* sensor);
    
    CalibracaoStatus calibrarSensor();
    
    int processarSpO2(long redValue, long irValue);

    byte getRedPulseAmplitude() const { return redPulseAmplitude; }
    
    // NOVO: Método público para resetar o buffer de SpO2 no .ino
    void resetBuffer();

private:
    MAX30105* sensorParticula;
    
    int calcularSpO2(long redValue, long irValue);

    // Variáveis de Cálculo de SpO2
    static const int R_SPO2_SIZE = 25;
    long R_IR_AC_Buf[R_SPO2_SIZE];
    long R_RED_AC_Buf[R_SPO2_SIZE];
    
    // Variável que estava privada e será manipulada pelo resetBuffer()
    byte R_SpO2_Index = 0; 
    
    const long LIMIAR_NO_FINGER = 5000;
    
    byte redPulseAmplitude = 0x1F; 
};

#endif