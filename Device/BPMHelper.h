#ifndef BPMHELPER_H
#define BPMHELPER_H

#include <Arduino.h>

class BPMHelper {
public:
    BPMHelper();
    
    void begin();
    bool processarAmostraBatimento(long valorSensor, unsigned long agora);
    int getMediaBatimentos() const; // Retorna int para uso externo (ex: Firebase)
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
    float taxasBatimento[TAMANHO_MEDIA]; // CORREÇÃO: Mudança para float
    byte posicaoAtualTaxa;
    float batimentosPorMinuto;
    float mediaBatimentos; // CORREÇÃO: Mudança para float (melhor precisão)
    long maxNormalizado; // CORREÇÃO: Para detecção de pico dinâmico
};

#endif