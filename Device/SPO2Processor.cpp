#include "SPO2Processor.h"
#include <Arduino.h>

// Constantes de Calibração (Migradas de HeartRateSPO2.cpp)
const long LIMIAR_SATURACAO = 200000; 
byte intensidades[] = {
    0x0F, 0x1F, 0x2F, 0x3F, 0x4F, 0x5F, 0x6F, 0x7F, 0x8F, 0x9F, 0xAF, 0xBF, 0xCF, 0xDF, 0xEF, 0xFF
};
const byte TOTAL_INTENSIDADES = sizeof(intensidades) / sizeof(intensidades[0]);

SPO2Processor::SPO2Processor(MAX30105* sensor) : sensorParticula(sensor) {
    // Inicialização do buffer de SpO2
    memset(R_IR_AC_Buf, 0, sizeof(R_IR_AC_Buf));
    memset(R_RED_AC_Buf, 0, sizeof(R_RED_AC_Buf));
}

// NOVO: Implementação da função de reset
void SPO2Processor::resetBuffer() {
    R_SpO2_Index = 0;
    // Opcional: Zerar os buffers AC para garantir que não haja dados antigos.
    memset(R_IR_AC_Buf, 0, sizeof(R_IR_AC_Buf));
    memset(R_RED_AC_Buf, 0, sizeof(R_RED_AC_Buf));
}

// ... (O restante da função calibrarSensor() e processarSpO2() é mantido)

CalibracaoStatus SPO2Processor::calibrarSensor() {
    // ... (Lógica para encontrar a melhor intensidade RED)
    byte melhorIntensidade = intensidades[0];
    long valorMaximo = 0;
    
    // Tenta encontrar uma intensidade de LED Red que não sature, mas seja forte
    for (byte i = 0; i < TOTAL_INTENSIDADES; i++) {
        sensorParticula->setPulseAmplitudeRed(intensidades[i]);
        delay(100); 

        long maxRed = 0;
        for (int j = 0; j < 10; j++) {
            sensorParticula->check(); 
            long redValue = sensorParticula->getRed();
            if (redValue > maxRed) maxRed = redValue;
            delay(2);
        }

        if (maxRed > LIMIAR_SATURACAO) {
            if (i > 0) {
                melhorIntensidade = intensidades[i-1];
                break;
            }
        } else if (i == TOTAL_INTENSIDADES - 1) {
            melhorIntensidade = intensidades[i];
            break; 
        }
        
        if (maxRed > valorMaximo) {
             valorMaximo = maxRed;
             melhorIntensidade = intensidades[i];
        }
    }
    
    sensorParticula->setPulseAmplitudeRed(melhorIntensidade);
    
    redPulseAmplitude = melhorIntensidade; 

    return CALIB_OK;
}

// ... (Restante do SPO2Processor.cpp, sem alterações no cálculo)

int SPO2Processor::calcularSpO2(long redValue, long irValue) {
    // ... (lógica de cálculo de SpO2)
    static long ir_DC = 0;
    static long red_DC = 0;
    float alpha = 0.95; 

    // Filtro DC (Low-pass)
    ir_DC = (long)(alpha * ir_DC + (1.0 - alpha) * irValue);
    red_DC = (long)(alpha * red_DC + (1.0 - alpha) * redValue);

    // Componente AC
    long ir_AC = irValue - ir_DC;
    long red_AC = redValue - red_DC;

    // Armazenamento no Buffer AC
    R_IR_AC_Buf[R_SpO2_Index] = ir_AC;
    R_RED_AC_Buf[R_SpO2_Index] = red_AC;
    R_SpO2_Index = (R_SpO2_Index + 1) % R_SPO2_SIZE;
    
    // ... (Cálculo de min/max, R e SpO2)
    long ir_min_AC = R_IR_AC_Buf[0], ir_max_AC = R_IR_AC_Buf[0];
    long red_min_AC = R_RED_AC_Buf[0], red_max_AC = R_RED_AC_Buf[0];

    for (byte i = 1; i < R_SPO2_SIZE; i++) {
        if (R_IR_AC_Buf[i] < ir_min_AC) ir_min_AC = R_IR_AC_Buf[i];
        if (R_IR_AC_Buf[i] > ir_max_AC) ir_max_AC = R_IR_AC_Buf[i];

        if (R_RED_AC_Buf[i] < red_min_AC) red_min_AC = R_RED_AC_Buf[i];
        if (R_RED_AC_Buf[i] > red_max_AC) red_max_AC = R_RED_AC_Buf[i];
    }
    
    long mean_IR_AC = ir_max_AC - ir_min_AC;
    long mean_RED_AC = red_max_AC - red_min_AC;

    if (mean_IR_AC == 0 || mean_RED_AC == 0 || ir_DC == 0 || red_DC == 0) {
        return 0; 
    }

    float R = ((float)mean_RED_AC / red_DC) / ((float)mean_IR_AC / ir_DC);
    
    int SpO2 = (int)(110 - 25.0 * R);

    if (SpO2 > 100) SpO2 = 100;
    if (SpO2 < 70) SpO2 = 0; 

    return SpO2;
}

int SPO2Processor::processarSpO2(long redValue, long irValue) {
    if (irValue < LIMIAR_NO_FINGER || redValue < LIMIAR_NO_FINGER) {
        return 0; 
    }
    return calcularSpO2(redValue, irValue);
}