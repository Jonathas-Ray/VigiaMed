// script.js/home.js

// Importações
import { auth, db, rtdb } from '../firebase-config.js';
import { onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { doc, getDoc } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";
import { ref, onValue } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js";

// --- Seleção dos Elementos ---
const userNameEl = document.getElementById('userName');
const btnLogout = document.getElementById('btnLogout');
const heartRateEl = document.getElementById('heartRate');
const saturationEl = document.getElementById('saturation'); // Na tela é saturation, no banco é spo2
const temperatureEl = document.getElementById('temperature');
const deviceIdEl = document.getElementById('deviceID');

// [MUDANÇA 1] Atualize para o NOVO ID do dispositivo da imagem
const DEVICE_ID = "DCB4D905BF3C";

// --- Função Principal ---
document.addEventListener('DOMContentLoaded', () => {

    onAuthStateChanged(auth, (user) => {
        if (user) {
            fetchUserInfo(user.uid);
            listenToVitals(DEVICE_ID);
        } else {
            window.location.href = 'login.html';
        }
    });

    btnLogout.addEventListener('click', () => {
        signOut(auth).then(() => {
            window.location.href = 'login.html';
        }).catch((error) => {
            console.error("Erro ao sair:", error);
        });
    });
});

// --- Busca Nome do Usuário ---
async function fetchUserInfo(uid) {
    const userDocRef = doc(db, "users", uid);
    try {
        const docSnap = await getDoc(userDocRef);
        if (docSnap.exists()) {
            userNameEl.textContent = docSnap.data().nome;
        } else {
            userNameEl.textContent = "Usuário";
        }
    } catch (error) {
        console.error("Erro ao buscar usuário:", error);
    }
}

// --- [MUDANÇA 2] Ouve os dados com a NOVA ESTRUTURA ---
function listenToVitals(deviceId) {
    const vitalsRef = ref(rtdb, 'vitals/' + deviceId);

    deviceIdEl.textContent = `ID: ${deviceId}`;

    onValue(vitalsRef, (snapshot) => {
        if (snapshot.exists()) {
            const data = snapshot.val();

            // --- LÓGICA ATUALIZADA PARA LER "value" ---

            // 1. Batimentos (heartRate -> value)
            // O '?.' verifica se o dado existe antes de tentar ler o .value para não dar erro
            const bpm = data.heartRate?.value || '--';
            heartRateEl.textContent = bpm;

            // 2. Saturação (MUDOU DE 'saturation' PARA 'spo2' -> value)
            const spo2 = data.spo2?.value || '--';
            saturationEl.textContent = spo2;

            // 3. Temperatura
            // (Nota: Na imagem não aparece temperatura, então deixamos preparado)
            const temp = data.Temperature?.value || '--';
            temperatureEl.textContent = temp;

        } else {
            console.log("Nenhum dado encontrado para este ID.");
            heartRateEl.textContent = '--';
            saturationEl.textContent = '--';
            temperatureEl.textContent = '--';
        }
    });
}