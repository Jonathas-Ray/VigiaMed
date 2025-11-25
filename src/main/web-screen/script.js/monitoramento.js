
let intervalo;
let monitorandoAtivo = false;

// ⚠️ CONFIGURE A URL DA SUA API AQUI
const API_URL = 'http://localhost:8080/api/medicao-lista/monitorar';

async function buscarUltimaMedicao() {
    try {
        const response = await fetch(API_URL);
        
        if (!response.ok) {
            throw new Error('Erro ao buscar dados');
        }

        const data = await response.json();
        atualizarInterface(data);
        
        document.getElementById('statusIndicator').className = 'status-indicator online';
        document.getElementById('statusText').textContent = 'Conectado - Atualizando a cada 3s';
        
    } catch (error) {
        console.error('Erro:', error);
        mostrarErro();
        
        document.getElementById('statusIndicator').className = 'status-indicator offline';
        document.getElementById('statusText').textContent = 'Erro de conexão';
    }
}

function atualizarInterface(data) {
    const statusCard = document.getElementById('statusCard');
    
    const statusClass = data.acimaDaNormal ? 'alerta' : 'normal';
    const icone = data.acimaDaNormal ? '🚨' : '✅';
    
    statusCard.className = `status-card ${statusClass}`;
    statusCard.innerHTML = `
        <div class="mensagem">${icone} ${data.mensagem}</div>
        <div class="resultado">${data.resultado !== null ? data.resultado.toFixed(2) : 'N/A'}</div>
        <div class="timestamp">Última atualização: ${new Date().toLocaleTimeString('pt-BR')}</div>
    `;

    // Se estiver acima do normal, pode tocar um som ou mostrar notificação
    if (data.acimaDaNormal) {
        console.warn('ALERTA: Valor acima do normal!');
        // Opcional: tocar um som de alerta
        // new Audio('alerta.mp3').play();
    }
}

function mostrarErro() {
    const statusCard = document.getElementById('statusCard');
    statusCard.className = 'status-card loading';
    statusCard.innerHTML = `
        <div style="color: #f44336; font-size: 48px;">⚠️</div>
        <div style="color: #666; margin-top: 20px;">Erro ao conectar com o servidor</div>
        <div style="color: #999; margin-top: 10px; font-size: 14px;">Verifique se a API está rodando</div>
    `;
}

function iniciarMonitoramento() {
    if (monitorandoAtivo) return;
    
    monitorandoAtivo = true;
    buscarUltimaMedicao(); // Primeira busca imediata
    
    // Atualiza a cada 3 segundos
    intervalo = setInterval(buscarUltimaMedicao, 3000);
    
    console.log('Monitoramento iniciado');
}

function pararMonitoramento() {
    if (!monitorandoAtivo) return;
    
    clearInterval(intervalo);
    monitorandoAtivo = false;
    
    document.getElementById('statusIndicator').className = 'status-indicator offline';
    document.getElementById('statusText').textContent = 'Monitoramento pausado';
    
    console.log('⏸️ Monitoramento pausado');
}

function atualizarAgora() {
    buscarUltimaMedicao();
    console.log('🔄 Atualização manual');
}

// Inicia automaticamente quando a página carregar
window.addEventListener('load', () => {
    iniciarMonitoramento();
});

// Para o monitoramento quando a página for fechada
window.addEventListener('beforeunload', () => {
    pararMonitoramento();
});
