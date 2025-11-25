export async function enviarMedicaoCompleta(deviceId, dados) {
    const agora = new Date().toISOString();

    // AJUSTE AQUI !!! 
    // IDs reais do paciente e do dispositivo (preciso que a pulseira mande)
    const PACIENTE_ID = 1;      
    const DISPOSITIVO_ID = 1;   

    const payload = {
        descricao: "Medição automática via Firebase",
        dataHora: agora,
        pacienteId: PACIENTE_ID,
        dispositivoId: DISPOSITIVO_ID,

        medicoesLista: [
            {
                resultado: dados.heartRate || 0,
                tipoMedicao: "HEART_RATE",
                data_hora: agora,
                sensorId: 1   // ID DO SENSOR DE BATIMENTOS (preciso que a pulseira mande)
            },
            {
                resultado: dados.saturation || 0,
                tipoMedicao: "SATURATION",
                data_hora: agora,
                sensorId: 2   // ID DO SENSOR DE OXIGENAÇÃO (preciso que a pulseira mande)
            },
            {
                resultado: dados.temperature || 0,
                tipoMedicao: "TEMPERATURE",
                data_hora: agora,
                sensorId: 3   // ID DO SENSOR DE TEMPERATURA (preciso que a pulseira mande)
            }
        ]
    };

    try {
        const response = await fetch("http://localhost:8080/medicao", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            console.error("❌ Erro ao enviar:", await response.text());
            return;
        }

        console.log("✅ Medição salva com sucesso!");

    } catch (err) {
        console.error("❌ Erro na requisição:", err);
    }
}

export async function getMedicaoPorId(id) {
    try {
        const response = await fetch(`http://localhost:8080/medicao/${id}`);

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

