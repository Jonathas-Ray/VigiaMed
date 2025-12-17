#ifndef BPMHELPER_H
#define BPMHELPER_H

#include <Arduino.h>

// Constantes
const byte TAMANHO_MEDIA = 4;       // Tamanho do array para calcular a média móvel.
const int INTERVALO_MINIMO_PICOS = 300; // 300ms = 200 BPM máximo

class BPMHelper {
public:
    BPMHelper();

    void begin();
    
    /**
     * @brief Processa uma nova amostra do sensor para detectar batimentos.
     * * @param valorSensor O valor lido do sensor (ex: valor Red).
     * @param agora O valor de millis() atual.
     * @return true Se um novo batimento foi detectado.
     */
    bool processarAmostraBatimento(long valorSensor, unsigned long agora);
    
    int getMediaBatimentos() const;
    float getBatimentosPorMinuto() const;

    // Métodos para gerenciamento da Baseline (Linha de base)
    void setBaselineEstabelecida(bool estabelecida);
    bool getBaselineEstabelecida() const;
    void resetBaseline();

private:
    void atualizarMediaBPMExponencial();

    long baseline;
    bool baselineEstabelecida;
    unsigned long tempoBaseline;
    long ultimoValor;
    bool subindo;
    unsigned long ultimoPico;
    
    // Armazenamento das taxas de batimento para cálculo da média
    float taxasBatimento[TAMANHO_MEDIA];
    byte posicaoAtualTaxa;
    
    float batimentosPorMinuto;
    float mediaBatimentos; // Variável para a média exponencial
    long maxNormalizado; // Para o Limiar Dinâmico
};

#endif