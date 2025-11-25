#include "HeartRateSPO2.h"
#include "secrets.h" 
#include <Arduino.h>

const long LIMIAR_SATURACAO = 200000; 
byte intensidades[] = {
    0x0F, 0x1F, 0x2F, 0x3F, 0x4F, 0x5F, 0x6F, 0x7F, 0x8F, 0x9F, 0xAF, 0xBF, 0xCF, 0xDF, 0xEF, 0xFF
};
const byte TOTAL_INTENSIDADES = sizeof(intensidades) / sizeof(intensidades[0]);

HeartRateSPO2::HeartRateSPO2(uint8_t sdaPin, uint8_t sclPin) : sda(sdaPin), scl(sclPin) {
    irValue = 0;
    redValue = 0;
    spo2Value = 0;
    memset(rates, 0, RATE_SIZE);
}

CalibracaoStatus HeartRateSPO2::iniciarSensor() {
    Wire.begin(sda, scl);
    bool sensorEncontrado = false;
    
    do {
        sensorEncontrado = sensor.begin(Wire, I2C_SPEED_FAST);
        if (!sensorEncontrado) {
            delay(300);
        }
    } while (!sensorEncontrado);

    if (!sensorEncontrado) {
        return ERRO_SENSOR_NAO_ENCONTRADO;
    }

    sensor.setup(); 
    sensor.setPulseAmplitudeGreen(0);

    calibrarSensor();
    
    return CALIB_OK;
}

void HeartRateSPO2::calibrarSensor() {
    byte melhorIndice = 0;
    long maiorValorIR = 0;
    const unsigned long TEMPO_TESTE = 1000; 

    for (byte i = 0; i < TOTAL_INTENSIDADES; i++) {
        byte intensidade = intensidades[i];
        sensor.setPulseAmplitudeRed(intensidade);
        sensor.setPulseAmplitudeIR(intensidade); 

        delay(100);

        unsigned long inicio = millis();
        long somaIR = 0;
        int amostras = 0;

        while (millis() - inicio < TEMPO_TESTE) {
            long currentIrValue = sensor.getIR();
            if (currentIrValue > LIMIAR_NO_FINGER) { 
                somaIR += currentIrValue;
                amostras++;
            }
            sensor.nextSample(); 
            delay(5);
        }

        if (amostras > 0) {
            long mediaIR = somaIR / amostras;
            if (mediaIR > maiorValorIR && mediaIR < LIMIAR_SATURACAO) {
                maiorValorIR = mediaIR;
                melhorIndice = i;
            }

            if (mediaIR >= LIMIAR_SATURACAO) {
                melhorIndice = i > 0 ? i - 1 : 0; 
                break;
            }
        }
    }
    
    byte melhorIntensidade = intensidades[melhorIndice];
    sensor.setPulseAmplitudeRed(melhorIntensidade);
    sensor.setPulseAmplitudeIR(melhorIntensidade); 
}

int HeartRateSPO2::calcularSpO2() {
    static long ir_DC = 0;
    static long red_DC = 0;
    float alpha = 0.95; 

    ir_DC = (long)(alpha * ir_DC + (1.0 - alpha) * irValue);
    red_DC = (long)(alpha * red_DC + (1.0 - alpha) * redValue);

    long ir_AC = irValue - ir_DC;
    long red_AC = redValue - red_DC;

    R_IR_AC_Buf[R_SpO2_Index] = ir_AC;
    R_RED_AC_Buf[R_SpO2_Index] = red_AC;
    R_SpO2_Index = (R_SpO2_Index + 1) % R_SPO2_SIZE;

    long sum_IR_AC = 0;
    long sum_RED_AC = 0;
    for (int i = 0; i < R_SPO2_SIZE; i++) {
        sum_IR_AC += R_IR_AC_Buf[i];
        sum_RED_AC += R_RED_AC_Buf[i];
    }
    
    long mean_IR_AC = sum_IR_AC / R_SPO2_SIZE;
    long mean_RED_AC = sum_RED_AC / R_SPO2_SIZE;
    
    if (mean_IR_AC == 0 || mean_RED_AC == 0 || ir_DC == 0 || red_DC == 0) {
        return 0; 
    }

    float R = ((float)mean_RED_AC / red_DC) / ((float)mean_IR_AC / ir_DC);
    
    int SpO2 = (int)(110 - 25.0 * R);

    if (SpO2 > 100) SpO2 = 100;
    if (SpO2 < 70) SpO2 = 0; 

    return SpO2;
}

void HeartRateSPO2::processarLeitura(int& bpmMedia, int& spo2Output) {
    float BPM;

    sensor.check(); 
    irValue = sensor.getIR(); 
    redValue = sensor.getRed();
    
    if (irValue > LIMIAR_NO_FINGER && redValue > LIMIAR_NO_FINGER) { 
        spo2Value = calcularSpO2();
    } else {
        spo2Value = 0; 
    }
    spo2Output = spo2Value; 

    if (checkForBeat(irValue) == true){ 
        long delta = millis() - lastBeat;
        lastBeat = millis(); 

        BPM = 60 / (delta / 1000.0);

        if (BPM < 255 && BPM > 20){ 
            rates[rateSpot++] = (byte)BPM; 
            rateSpot %= RATE_SIZE; 

            bpmMedia = 0;
            for (byte x = 0 ; x < RATE_SIZE ; x++) 
                bpmMedia += rates[x];
            bpmMedia /= RATE_SIZE; 
        }
    }
}