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
    mediaBatimentos(0) {
    for (byte i = 0; i < TAMANHO_MEDIA; i++) {
        taxasBatimento[i] = 0;
    }
}

void BPMHelper::begin() {
    // Inicialização se necessário
}

void BPMHelper::atualizarMediaBPMExponencial() {
    const float ALPHA = 0.2;
    
    if (mediaBatimentos == 0) {
        mediaBatimentos = batimentosPorMinuto;
    } else {
        mediaBatimentos = (ALPHA * batimentosPorMinuto) + ((1 - ALPHA) * mediaBatimentos);
    }
    
    taxasBatimento[posicaoAtualTaxa] = (byte)batimentosPorMinuto;
    posicaoAtualTaxa = (posicaoAtualTaxa + 1) % TAMANHO_MEDIA;
}

bool BPMHelper::processarAmostraBatimento(long valorSensor, unsigned long agora) {
    if (!baselineEstabelecida) {
        baseline = (baseline * 0.99) + (valorSensor * 0.01);
        if (agora - tempoBaseline >= 5000) baselineEstabelecida = true;
        return false;
    }

    long valorNormalizado = valorSensor - baseline;
    bool estavaSubindo = subindo;
    subindo = (valorNormalizado > ultimoValor);

    if (estavaSubindo && !subindo && 
        valorNormalizado > 5000 && 
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
    return mediaBatimentos;
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
}