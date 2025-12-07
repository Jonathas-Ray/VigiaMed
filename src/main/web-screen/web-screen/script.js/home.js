// script.js/home.js

import { auth, db, rtdb } from '../firebase-config.js';
import { onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { doc, getDoc } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";
import { ref, onValue } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js";
import { enviarMedicaoCompleta } from './api.js'


const userNameEl = document.getElementById('userName');
const btnLogout = document.getElementById('btnLogout');
const heartRateEl = document.getElementById('heartRate');
const saturationEl = document.getElementById('saturation');
const temperatureEl = document.getElementById('temperature');
const deviceIdEl = document.getElementById('deviceID');


const DEVICE_ID = "DCB4D905BF3C";
const API_CALL_INTERVAL_MS = 10 * 1000;


function startApiScheduler(deviceId) {
    
    // Função que será executada a cada 10 segundos
    const sendData = () => {
        // 1. LÊ OS VALORES ATUAIS do display (eles foram atualizados pelo onValue)
        const bpm = heartRateEl.textContent;
        const spo2 = saturationEl.textContent;
        const temp = temperatureEl.textContent;

        // 2. Checa se há dados válidos
        if (bpm !== '--' && spo2 !== '--' && temp !== '--') {
            
            const dadosParaAPI = {
                heartRate: parseFloat(bpm),
                saturation: parseFloat(spo2),
                temperature: parseFloat(temp)
            };
            
            const nextSendTime = API_CALL_INTERVAL_MS / 1000;

            console.log(`⏱️ Enviando dados para API. Próximo envio em ${nextSendTime}s.`);

            enviarMedicaoCompleta(deviceId, dadosParaAPI)
                .catch(err => console.error("Falha ao salvar medição via API:", err));
        } else {
            console.warn("⚠️ Dados incompletos ou inválidos. Envio para API ignorado.");
        }
    };
    
    // Inicia a execução imediatamente e repete a cada 10 segundos
    sendData(); // Roda a primeira vez imediatamente
    setInterval(sendData, API_CALL_INTERVAL_MS);
}

document.addEventListener('DOMContentLoaded', () => {
    onAuthStateChanged(auth, (user) => {
        if (user) {
            fetchUserInfo(user.uid);
            listenToVitals(DEVICE_ID);

            startApiScheduler(DEVICE_ID); 
        } else {
            window.location.href = '../html/login.html';
        }
    });

    btnLogout.addEventListener('click', () => {
        signOut(auth).then(() => {
            window.location.href = '../html/login.html';
        }).catch((error) => {
            console.error("Erro ao sair:", error);
        });
    });
});

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

function updateValue(element, newValue) {
    // Se quiser que pisque mesmo se o valor repetir, remova este if.
    // if (element.textContent === newValue) return;

    element.textContent = newValue;

    element.classList.remove('update-flash');
    void element.offsetWidth; 
    element.classList.add('update-flash');
}

function listenToVitals(deviceId) {
    const vitalsRef = ref(rtdb, 'vitals/' + deviceId);
    deviceIdEl.textContent = `ID: ${deviceId}`;

    onValue(vitalsRef, (snapshot) => {
        if (snapshot.exists()) {
            const data = snapshot.val();
                        
            const bpm = data.heartRate?.value || '--';
            const spo2 = data.spo2?.value || '--';
            const temp = data.Temperature?.value || '--'; 
            
            // Apenas atualiza o display com os dados mais recentes
            updateValue(heartRateEl, bpm);
            updateValue(saturationEl, spo2);
            updateValue(temperatureEl, temp);
        } else {
            heartRateEl.textContent = '--';
            saturationEl.textContent = '--';
            temperatureEl.textContent = '--';
        }
    });
}
