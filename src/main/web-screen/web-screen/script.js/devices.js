// script.js/device.js

import { auth, db, rtdb } from '../firebase-config.js';
import { onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { doc, getDoc } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";
import { ref, onValue } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js";

const devicesContainer = document.getElementById('devices-container');
const userNameEl = document.getElementById('userName');
const btnLogout = document.getElementById('btnLogout');

document.addEventListener('DOMContentLoaded', () => {
    // 1. Checagem de Auth padrão
    onAuthStateChanged(auth, (user) => {
        if (user) {
            fetchUserInfo(user.uid);
            listenToDevices();
        } else {
            window.location.href = 'login.html';
        }
    });

    btnLogout.addEventListener('click', () => {
        signOut(auth).then(() => window.location.href = 'login.html');
    });
});

async function fetchUserInfo(uid) {
    try {
        const docSnap = await getDoc(doc(db, "users", uid));
        if (docSnap.exists()) userNameEl.textContent = docSnap.data().nome;
    } catch (e) { console.error(e); }
}

// --- FUNÇÃO PRINCIPAL: Listar Dispositivos ---
function listenToDevices() {
    // Pega a raiz 'vitals' para ver TODOS os filhos
    const vitalsRef = ref(rtdb, 'vitals');

    onValue(vitalsRef, (snapshot) => {
        devicesContainer.innerHTML = ''; // Limpa a tela

        if (snapshot.exists()) {
            const data = snapshot.val();
            // Transforma o objeto em array [ID, Dados]
            Object.entries(data).forEach(([deviceId, sensorData]) => {
                createCard(deviceId, sensorData);
            });
        } else {
            devicesContainer.innerHTML = `
                <div class="col-12 text-center text-secondary">
                    <i class="bi bi-x-circle fs-1 d-block mb-3"></i>
                    Nenhum dispositivo encontrado no banco de dados.
                </div>
            `;
        }
    });
}

function createCard(id, data) {
    // Lógica para pegar valor (suporta estrutura antiga e nova)
    const bpm = data.heartRate?.value || data.heartRate || '--';
    const spo2 = data.spo2?.value || data.saturation || '--'; // Tenta spo2, senão saturation

    // Cria o HTML do Card mantendo a estética
    const html = `
        <div class="col-md-6 col-lg-4 col-xl-3">
            <div class="device-card h-100 d-flex flex-column justify-content-between">
                
                <div class="d-flex justify-content-between align-items-start mb-3">
                    <div class="d-flex align-items-center gap-2">
                        <div class="bg-dark rounded-circle p-2 d-flex align-items-center justify-content-center" style="width:40px; height:40px;">
                            <i class="bi bi-hdd-network text-success"></i>
                        </div>
                        <div>
                            <span class="badge bg-success bg-opacity-10 text-success mb-1">Online</span>
                            <h6 class="text-white m-0 device-id fw-bold">${id}</h6>
                        </div>
                    </div>
                </div>

                <div class="row g-2 mb-4">
                    <div class="col-6">
                        <div class="bg-dark rounded p-2 text-center border border-secondary">
                            <small class="text-secondary d-block" style="font-size: 0.7rem;">BPM</small>
                            <span class="fw-bold text-white fs-5">${bpm}</span>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="bg-dark rounded p-2 text-center border border-secondary">
                            <small class="text-secondary d-block" style="font-size: 0.7rem;">SpO2</small>
                            <span class="fw-bold text-white fs-5">${spo2}%</span>
                        </div>
                    </div>
                </div>

                <a href="home.html?id=${id}" class="btn btn-monitorar w-100 py-2">
                    <i class="bi bi-graph-up me-2"></i> Monitorar
                </a>

            </div>
        </div>
    `;

    devicesContainer.innerHTML += html;
}