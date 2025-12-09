
const API_BASE_URL = "http://localhost:8080/api";
const PACIENTE_ID = 1;
const DISPOSITIVO_ID = 1;

/**
 * Cria o registro principal na tabela 'medicao' (Cabeçalho).
 * @param {number} pacienteId 
 * @param {number} dispositivoId 
 * @param {string} dataHora 
 * @returns {Promise<{id: number}>} 
 */
async function criarMedicao(pacienteId, dispositivoId, dataHora) { 

    const payloadMedicao = {
        "descricao": "Medição de pressão arterial",
        "dataHora": dataHora, 
        "pacienteId": pacienteId, 
        "dispositivoId": dispositivoId 
    };

    const response = await fetch(`${API_BASE_URL}/medicao`, {
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
        return await response.json(); 
    }
}

/**
 * Cria um item de detalhe na tabela 'medicaolista'.
 * @param {number} medicaoId 
 * @param {number} resultado 
 * @param {string} tipoMedicao 
 * @param {number} sensorId 
 * @param {string} timestamp 
 * @returns {Promise<object>} 
 */
async function criarMedicaoLista(medicaoId, resultado, tipoMedicao, sensorId, timestamp) {
    const payloadLista = {
        "resultado": resultado.toString(),
        "tipoMedicao": tipoMedicao,
        "dataHora": timestamp, 
        "medicaoId": medicaoId,
        "sensorId": sensorId
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
/**
 * Orquestra o envio da medição principal e de todos os seus detalhes.
 * @param {number} deviceId - O ID do dispositivo (se necessário no futuro).
 * @param {object} dados - Objeto contendo os dados de medição (heartRate, saturation, temperature).
 */
export async function enviarMedicaoCompleta(deviceId, dadosVatais) {
    const agora = new Date().toISOString(); 
    
    
    try {
        const medicao = await criarMedicao(PACIENTE_ID, DISPOSITIVO_ID, agora);
        
        console.log("DEBUG: Objeto 'medicao' retornado:", medicao); 
        
        const medicaoId = medicao.id;

        console.log("DEBUG: ID extraído:", medicaoId); 

        console.log("✔ Medição criada com ID:", medicaoId);
        
        await criarMedicaoLista(
            
            medicaoId,
            dadosVatais.heartRate || 0, 
            "BPM",
            1,
            agora
        );

        await criarMedicaoLista(
            medicaoId,
            dadosVatais.saturation || 0, 
            "SATURACAO",
            2,
            agora
        );

        await criarMedicaoLista(
            medicaoId,
            dadosVatais.temperature || 0, 
            "TEMPERATURA",
            3, 
            agora
        );

        console.log("✅ Medição completa salva com sucesso!");

    } catch (error) {
        console.error("❌ Erro ao enviar a medição completa:", error);
    }
}

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