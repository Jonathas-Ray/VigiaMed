#ifndef BPMHELPER_H
#define BPMHELPER_H

#include <Arduino.h>

class BPMHelper {
public:
    BPMHelper();
    
    void begin();
    bool processarAmostraBatimento(long valorSensor, unsigned long agora);
    int getMediaBatimentos() const;
    float getBatimentosPorMinuto() const;
    void setBaselineEstabelecida(bool estabelecida);
    bool getBaselineEstabelecida() const;
    void resetBaseline();

private:
    void atualizarMediaBPMExponencial();

    // Variáveis do processamento de sinais
    long baseline;
    bool baselineEstabelecida;
    unsigned long tempoBaseline;
    long ultimoValor;
    bool subindo;
    unsigned long ultimoPico;
    static const unsigned long INTERVALO_MINIMO_PICOS = 300;
    
    // Cálculo BPM
    static const byte TAMANHO_MEDIA = 4;
    byte taxasBatimento[TAMANHO_MEDIA];
    byte posicaoAtualTaxa;
    float batimentosPorMinuto;
    int mediaBatimentos;
};

#endif