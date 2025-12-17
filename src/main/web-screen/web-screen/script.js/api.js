// --- CONSTANTES DE CONFIGURAÇÃO ---
const API_BASE_URL = "http://localhost:8080/api";
const PACIENTE_ID = 1;
const DISPOSITIVO_ID = 1;

// ----------------------------------------------------------------------
// FUNÇÕES DE REQUISIÇÃO À API
// ----------------------------------------------------------------------

/**
 * Cria o registro principal na tabela 'medicao' (Cabeçalho).
 * @param {number} pacienteId - O ID do paciente.
 * @param {number} dispositivoId - O ID do dispositivo.
 * @param {string} dataHora - Data e hora formatada em ISO string.
 * @returns {Promise<{id: number}>} O objeto JSON da medição criada (deve conter o ID).
 */
async function criarMedicao(pacienteId, dispositivoId, dataHora) { // 👈 CORREÇÃO 1 & 2: Função async com argumentos

    const payloadMedicao = {
        "descricao": "Medição de pressão arterial",
        "dataHora": dataHora, 
        "pacienteId": pacienteId, 
        "dispositivoId": dispositivoId 
    };

    const response = await fetch(`${API_BASE_URL}/medicao`, { // 👈 CORREÇÃO 1: fetch dentro da função, usando URL base
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(payloadMedicao)
    });

    if (!response.ok) {
        throw new Error("Erro ao criar medição: " + await response.text());
    }
    
    const contentType = response.headers.get("content-type");
    if (contentType && contentType.includes("application/json")) {
        // Se houver JSON, leia o JSON.
        return await response.json(); 
    }
}

/**
 * Cria um item de detalhe na tabela 'medicaolista'.
 * @param {number} medicaoId - O ID da medição principal.
 * @param {number} resultado - O valor numérico da leitura.
 * @param {string} tipoMedicao - O tipo da medição (ex: "BPM").
 * @param {number} sensorId - O ID do sensor.
 * @param {string} timestamp - Data e hora formatada em ISO string.
 * @returns {Promise<object>} O objeto JSON do item da lista criado.
 */
async function criarMedicaoLista(medicaoId, resultado, tipoMedicao, sensorId, timestamp) {
    const payloadLista = {
        "resultado": resultado.toString(), // Garanta que é string, se seu backend espera
        "tipoMedicao": tipoMedicao,       // 👈 CORREÇÃO: Camel Case
        "dataHora": timestamp, 
        "medicaoId": medicaoId,           // 👈 CORREÇÃO: Camel Case
        "sensorId": sensorId              // 👈 CORREÇÃO: Camel Case
    };

    const response = await fetch(`${API_BASE_URL}/medicao-lista`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payloadLista)
    });

    if (!response.ok) {
        throw new Error(`Erro ao criar item da medição_lista (${tipoMedicao}): ` + await response.text());
    }

    return await response.json();
}


// ----------------------------------------------------------------------
// FLUXO PRINCIPAL
// ----------------------------------------------------------------------

/**
 * Orquestra o envio da medição principal e de todos os seus detalhes.
 * @param {number} deviceId - O ID do dispositivo (se necessário no futuro).
 * @param {object} dados - Objeto contendo os dados de medição (heartRate, saturation, temperature).
 */
export async function enviarMedicaoCompleta(deviceId, dadosVatais) {
    // 👈 CORREÇÃO 3: Calcular o timestamp uma vez
    const agora = new Date().toISOString(); 
    
    
    try {
        // 2. Criar a medição principal
        const medicao = await criarMedicao(PACIENTE_ID, DISPOSITIVO_ID, agora);
        
        // NOVO: Verifique se o objeto 'medicao' foi retornado corretamente
        console.log("DEBUG: Objeto 'medicao' retornado:", medicao); 
        
        const medicaoId = medicao.id;

        // NOVO: Verifique se o ID foi extraído
        console.log("DEBUG: ID extraído:", medicaoId); 

        console.log("✔ Medição criada com ID:", medicaoId);
        // 3. Criar os detalhes da lista (Execução Sequencial 'await' em série)
        
        await criarMedicaoLista(
            
            medicaoId,
            dadosVatais.heartRate || 0, // 👈 Ajustado para usar dadosVatais
            "BPM",
            1, // sensor de frequência cardíaca
            agora
        );

        await criarMedicaoLista(
            medicaoId,
            dadosVatais.saturation || 0, // 👈 Ajustado para usar dadosVatais
            "SATURACAO",
            2, // sensor de oxigenação
            agora
        );

        await criarMedicaoLista(
            medicaoId,
            dadosVatais.temperature || 0, // 👈 Ajustado para usar dadosVatais
            "TEMPERATURA",
            3, // sensor de temperatura
            agora
        );

        console.log("✅ Medição completa salva com sucesso!");

    } catch (error) {
        // 👈 CORREÇÃO 4: Usar 'error' no catch
        console.error("❌ Erro ao enviar a medição completa:", error);
    }
}


// ----------------------------------------------------------------------
// FUNÇÃO GET (Não alterada)
// ----------------------------------------------------------------------
// ... (código getMedicaoPorId permanece inalterado)

// ----------------------------------------------------------------------
// FUNÇÃO GET (Não alterada)
// ----------------------------------------------------------------------

/**
 * Função GET para buscar medições por ID.
 * @param {number} id - O ID da medição a ser buscada.
 * @returns {Promise<object | null>} Os dados da medição ou null em caso de erro.
 */
export async function getMedicaoPorId(id) {
    try {
        const response = await fetch(`${API_BASE_URL}/medicao/${id}`);

        if (!response.ok) {
            console.error("Erro ao buscar a medição:", await response.text());
            return null;
        }

        return await response.json();

    } catch (error) {
        console.error("Erro na requisição GET:", error);
        return null;
    }
}