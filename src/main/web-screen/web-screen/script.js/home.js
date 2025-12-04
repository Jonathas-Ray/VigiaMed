// script.js/home.js

import { auth, db, rtdb } from '../firebase-config.js';
import { onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { doc, getDoc } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";
import { ref, onValue } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js";

const userNameEl = document.getElementById('userName');
const btnLogout = document.getElementById('btnLogout');
const heartRateEl = document.getElementById('heartRate');
const saturationEl = document.getElementById('saturation');
const temperatureEl = document.getElementById('temperature');
const deviceIdEl = document.getElementById('deviceID');

const urlParams = new URLSearchParams(window.location.search);
const urlID = urlParams.get('id');

const DEVICE_ID = urlID || "DCB4D905BF3C"

document.addEventListener('DOMContentLoaded', () => {
    onAuthStateChanged(auth, (user) => {
        if (user) {
            fetchUserInfo(user.uid);
            listenToVitals(DEVICE_ID);
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

// --- [NOVA FUNÇÃO] Atualiza texto e Anima ---
function updateValue(element, newValue) {
    // 1. Se o valor for o mesmo que já está na tela, não faz nada (opcional)
    // Se quiser que pisque mesmo se o valor repetir, remova este if.
    if (element.textContent === newValue) return;

    // 2. Atualiza o texto
    element.textContent = newValue;

    // 3. Reinicia a animação CSS
    element.classList.remove('update-flash'); // Remove a classe
    void element.offsetWidth; // Truque mágico: força o navegador a "notar" que removemos
    element.classList.add('update-flash'); // Adiciona de volta para tocar a animação
}

function listenToVitals(deviceId) {
    const vitalsRef = ref(rtdb, 'vitals/' + deviceId);
    
    deviceIdEl.textContent = `ID: ${deviceId}`;

    onValue(vitalsRef, (snapshot) => {
        if (snapshot.exists()) {
            const data = snapshot.val();
            
            // Usamos a nova função updateValue para cada campo
            
            const bpm = data.heartRate?.value || '--';
            updateValue(heartRateEl, bpm);

            const spo2 = data.spo2?.value || '--';
            updateValue(saturationEl, spo2);

            const temp = data.Temperature?.value || '--'; 
            updateValue(temperatureEl, temp);
            
        } else {
            heartRateEl.textContent = '--';
            saturationEl.textContent = '--';
            temperatureEl.textContent = '--';
        }
    });
}