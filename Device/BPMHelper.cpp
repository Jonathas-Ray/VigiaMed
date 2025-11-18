#include "BPMHelper.h"

BPMHelper::BPMHelper() :
    baseline(0),
    baselineEstabelecida(false),
    tempoBaseline(0),
    ultimoValor(0),
    subindo(false),
    ultimoPico(0),
    posicaoAtualTaxa(0),
    batimentosPorMinuto(0),
    mediaBatimentos(0),
    maxNormalizado(0) { // Inicialização da nova variável
    for (byte i = 0; i < TAMANHO_MEDIA; i++) {
        taxasBatimento[i] = 0.0f; // CORREÇÃO: Inicialização float
    }
}

void BPMHelper::begin() {
    // Inicialização se necessário
}

void BPMHelper::atualizarMediaBPMExponencial() {
    const float ALPHA = 0.2;
    
    // Lógica para inicializar a média
    if (mediaBatimentos == 0) {
        mediaBatimentos = batimentosPorMinuto;
    } else {
        // CORREÇÃO: mediaBatimentos é float, garantindo precisão
        mediaBatimentos = (ALPHA * batimentosPorMinuto) + ((1 - ALPHA) * mediaBatimentos);
    }
    
    // CORREÇÃO: Armazena o valor float, sem truncamento
    taxasBatimento[posicaoAtualTaxa] = batimentosPorMinuto;
    posicaoAtualTaxa = (posicaoAtualTaxa + 1) % TAMANHO_MEDIA;
}

bool BPMHelper::processarAmostraBatimento(long valorSensor, unsigned long agora) {
    if (!baselineEstabelecida) {
        baseline = (baseline * 0.99) + (valorSensor * 0.01);
        if (agora - tempoBaseline >= 5000) baselineEstabelecida = true;
        return false;
    }

    long valorNormalizado = valorSensor - baseline;
    
    // CORREÇÃO C: Lógica para Limiar Dinâmico
    if (valorNormalizado > maxNormalizado) {
        maxNormalizado = valorNormalizado;
    } else if (valorNormalizado < maxNormalizado * 0.9) { 
        // Decay do pico máximo se o sinal cair significativamente
        maxNormalizado = (maxNormalizado * 0.95) + (valorNormalizado * 0.05);
    }
    
    bool estavaSubindo = subindo;
    subindo = (valorNormalizado > ultimoValor);
    
    // Limiar Dinâmico: 70% da amplitude máxima recente
    const long LIMIAR_DINAMICO = (long)(maxNormalizado * 0.70); 

    if (estavaSubindo && !subindo && 
        // CORREÇÃO: Substituindo o valor mágico 5000 pelo limiar dinâmico
        valorNormalizado > LIMIAR_DINAMICO && 
        (agora - ultimoPico) > INTERVALO_MINIMO_PICOS) {
        
        unsigned long intervalo = agora - ultimoPico;
        ultimoPico = agora;
        
        if (intervalo > 0 && intervalo < 3000) {
            batimentosPorMinuto = 60000.0 / intervalo;
            
            if (batimentosPorMinuto >= 20 && batimentosPorMinuto <= 255) {
                atualizarMediaBPMExponencial();
                return true;
            }
        }
    }

    ultimoValor = valorNormalizado;
    baseline = (baseline * 0.999) + (valorSensor * 0.001);
    return false;
}

int BPMHelper::getMediaBatimentos() const {
    // Retorna o float castado para int para uso externo (ex: Firebase)
    return (int)mediaBatimentos; 
}

float BPMHelper::getBatimentosPorMinuto() const {
    return batimentosPorMinuto;
}

void BPMHelper::setBaselineEstabelecida(bool estabelecida) {
    baselineEstabelecida = estabelecida;
}

bool BPMHelper::getBaselineEstabelecida() const {
    return baselineEstabelecida;
}

void BPMHelper::resetBaseline() {
    baseline = 0;
    baselineEstabelecida = false;
    tempoBaseline = millis();
    // Reinicia o máximo normalizado
    maxNormalizado = 0; 
}