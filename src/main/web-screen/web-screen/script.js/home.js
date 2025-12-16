import { auth, db, rtdb } from '../firebase-config.js';
import { onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import {
    doc,
    getDoc,
    setDoc,
    collection,
    getDocs,
    deleteDoc
} from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";
import { ref, onValue } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js";
import { enviarMedicaoCompleta } from './api.js'

const userNameEl = document.getElementById('userName');
const btnLogout = document.getElementById('btnLogout');
const btnAddPatient = document.getElementById('btnAddPatient');
const btnSavePatient = document.getElementById('btnSavePatient');
const patientsContainer = document.getElementById('patientsContainer');
const emptyState = document.getElementById('emptyState');

const API_BASE_URL = "http://localhost:8080";
const API_CALL_INTERVAL_MS = 10 * 1000;

const activeListeners = new Map();
const patientAlerts = new Map();
const patientSchedulers = new Map();
const patientMonitors = new Map();

document.addEventListener('DOMContentLoaded', () => {
    onAuthStateChanged(auth, (user) => {
        if (user) {
            fetchUserInfo(user.uid);
            loadPatients();
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

    btnAddPatient.addEventListener('click', () => {
        const modal = new bootstrap.Modal(document.getElementById('modalAddPatient'));
        modal.show();
    });

    btnSavePatient.addEventListener('click', async () => {
        const patientName = document.getElementById('patientName').value.trim();
        const deviceId = document.getElementById('deviceId').value.trim().toUpperCase();

        if (!patientName || !deviceId) {
            Swal.fire({
                icon: 'error',
                title: 'Campos obrigatórios',
                text: 'Preencha o nome do paciente e o ID do dispositivo.',
                confirmButtonColor: '#00d4aa'
            });
            return;
        }

        try {
            const vitalsRef = ref(rtdb, 'vitals/' + deviceId);
            const snapshot = await new Promise((resolve, reject) => {
                onValue(vitalsRef, resolve, reject, { onlyOnce: true });
            });

            if (!snapshot.exists()) {
                Swal.fire({
                    icon: 'error',
                    title: 'Dispositivo não encontrado',
                    text: `O dispositivo ${deviceId} não existe no sistema. Verifique o ID e tente novamente.`,
                    confirmButtonColor: '#dc3545'
                });
                return;
            }

            await setDoc(doc(db, "patients", deviceId), {
                name: patientName,
                deviceId: deviceId,
                createdAt: new Date().toISOString()
            });

            document.getElementById('patientName').value = '';
            document.getElementById('deviceId').value = '';

            const modal = bootstrap.Modal.getInstance(document.getElementById('modalAddPatient'));
            modal.hide();

            loadPatients();

            const Toast = Swal.mixin({
                toast: true,
                position: "top-end",
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true,
                didOpen: (toast) => {
                    toast.onmouseenter = Swal.stopTimer;
                    toast.onmouseleave = Swal.resumeTimer;
                }
            });

            Toast.fire({
                icon: 'success',
                title: 'Paciente adicionado!',
                text: `O paciente foi vinculado ao dispositivo ${deviceId}`
            });

        } catch (error) {
            console.error("Erro ao adicionar paciente:", error);
            Swal.fire({
                icon: 'error',
                title: 'Erro',
                text: 'Não foi possível adicionar o paciente. Tente novamente.',
                confirmButtonColor: '#dc3545'
            });
        }
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

async function loadPatients() {
    try {
        const patientsRef = collection(db, "patients");
        const querySnapshot = await getDocs(patientsRef);

        activeListeners.forEach(unsubscribe => unsubscribe());
        activeListeners.clear();

        patientSchedulers.forEach(intervalId => clearInterval(intervalId));
        patientSchedulers.clear();

        patientMonitors.forEach(intervalId => clearInterval(intervalId));
        patientMonitors.clear();

        patientsContainer.innerHTML = '';

        if (querySnapshot.empty) {
            emptyState.style.display = 'flex';
            patientsContainer.style.display = 'none';
        } else {
            emptyState.style.display = 'none';
            patientsContainer.style.display = 'grid';

            querySnapshot.forEach((doc) => {
                const patientData = doc.data();
                createPatientCard(patientData);
                listenToVitals(patientData.deviceId, patientData.name);
                startApiScheduler(patientData.deviceId);
                iniciarMonitoramento(patientData.deviceId, patientData.name);
            });
        }
    } catch (error) {
        console.error("Erro ao carregar pacientes:", error);
    }
}

function createPatientCard(patientData) {
    const cardHTML = `
        <div class="patient-group" id="patient-${patientData.deviceId}">
            <div class="patient-header">
                <div>
                    <div class="patient-name">${patientData.name}</div>
                    <div class="patient-id">ID: ${patientData.deviceId}</div>
                </div>
                <button class="btn-remove-patient" onclick="removePatient('${patientData.deviceId}', '${patientData.name}')">
                    <i class="bi bi-trash-fill"></i>
                </button>
            </div>
            
            <div class="vital-mini-card">
                <div class="vital-mini-title">Pulsação Cardíaca</div>
                <div class="vital-mini-value">
                    <span id="heartRate-${patientData.deviceId}">--</span> bpm
                </div>
            </div>

            <div class="vital-mini-card">
                <div class="vital-mini-title">Saturação de Oxigênio</div>
                <div class="vital-mini-value">
                    <span id="saturation-${patientData.deviceId}">--</span> SpO2%
                </div>
            </div>

            <div class="vital-mini-card">
                <div class="vital-mini-title">Temperatura</div>
                <div class="vital-mini-value">
                    <span id="temperature-${patientData.deviceId}">--</span> °C
                </div>
            </div>
        </div>
    `;

    patientsContainer.insertAdjacentHTML('beforeend', cardHTML);
}

window.removePatient = async function (deviceId, patientName) {
    const result = await Swal.fire({
        title: 'Remover paciente?',
        text: `Deseja remover ${patientName}?`,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#dc3545',
        cancelButtonColor: '#6c757d',
        confirmButtonText: 'Sim, remover',
        cancelButtonText: 'Cancelar'
    });

    const Toast = Swal.mixin({
        toast: true,
        position: "top-end",
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.onmouseenter = Swal.stopTimer;
            toast.onmouseleave = Swal.resumeTimer;
        }
    });

    if (result.isConfirmed) {
        try {
            await deleteDoc(doc(db, "patients", deviceId));

            if (activeListeners.has(deviceId)) {
                activeListeners.get(deviceId)();
                activeListeners.delete(deviceId);
            }

            if (patientSchedulers.has(deviceId)) {
                clearInterval(patientSchedulers.get(deviceId));
                patientSchedulers.delete(deviceId);
            }

            if (patientMonitors.has(deviceId)) {
                clearInterval(patientMonitors.get(deviceId));
                patientMonitors.delete(deviceId);
            }

            if (patientAlerts.has(deviceId)) {
                const alert = patientAlerts.get(deviceId);
                if (alert && alert.alertInstance && alert.alertInstance.isVisible()) {
                    Swal.close();
                }
                patientAlerts.delete(deviceId);
            }

            loadPatients();

            Toast.fire({
                icon: "success",
                title: "Signed in successfully"
            });
        } catch (error) {
            console.error("Erro ao remover paciente:", error);
            Toast.fire({
                icon: 'error',
                title: 'Erro',
                text: 'Não foi possível remover o paciente.'
            });
        }
    }
}

function updateValue(element, newValue) {
    if (!element) return;
    element.textContent = newValue;
    element.classList.remove('update-flash');
    void element.offsetWidth;
    element.classList.add('update-flash');
}

function listenToVitals(deviceId, patientName) {
    const vitalsRef = ref(rtdb, 'vitals/' + deviceId);

    const unsubscribe = onValue(vitalsRef, (snapshot) => {
        if (snapshot.exists()) {
            const data = snapshot.val();

            const bpm = data.heartRate?.value || '--';
            const spo2 = data.spo2?.value || '--';
            const temp = data.Temperature?.value || '--';

            const heartRateEl = document.getElementById(`heartRate-${deviceId}`);
            const saturationEl = document.getElementById(`saturation-${deviceId}`);
            const temperatureEl = document.getElementById(`temperature-${deviceId}`);

            updateValue(heartRateEl, bpm);
            updateValue(saturationEl, spo2);
            updateValue(temperatureEl, temp);
        } else {
            const heartRateEl = document.getElementById(`heartRate-${deviceId}`);
            const saturationEl = document.getElementById(`saturation-${deviceId}`);
            const temperatureEl = document.getElementById(`temperature-${deviceId}`);

            if (heartRateEl) heartRateEl.textContent = '--';
            if (saturationEl) saturationEl.textContent = '--';
            if (temperatureEl) temperatureEl.textContent = '--';
        }
    });

    activeListeners.set(deviceId, unsubscribe);
}

function startApiScheduler(deviceId) {
    const sendData = () => {
        const heartRateEl = document.getElementById(`heartRate-${deviceId}`);
        const saturationEl = document.getElementById(`saturation-${deviceId}`);
        const temperatureEl = document.getElementById(`temperature-${deviceId}`);

        if (!heartRateEl || !saturationEl || !temperatureEl) return;

        const bpm = heartRateEl.textContent;
        const spo2 = saturationEl.textContent;
        const temp = temperatureEl.textContent;

        if (bpm !== '--' && spo2 !== '--' && temp !== '--') {
            const dadosParaAPI = {
                heartRate: parseFloat(bpm),
                saturation: parseFloat(spo2),
                temperature: parseFloat(temp)
            };

            enviarMedicaoCompleta(deviceId, dadosParaAPI)
                .catch(err => console.error(`Falha ao salvar medição via API (${deviceId}):`, err));
        }
    };

    sendData();
    const intervalId = setInterval(sendData, API_CALL_INTERVAL_MS);
    patientSchedulers.set(deviceId, intervalId);
}

function iniciarMonitoramento(deviceId, patientName) {
    const verificar = async () => {
        try {
            const response = await fetch(`${API_BASE_URL}/api/medicao-lista/monitorar`);

            if (!response.ok) return;

            const resultado = await response.json();

            if (resultado.resultado === null) return;

            const bpm = resultado.resultado;
            const mensagem = resultado.mensagem;

            if (!patientAlerts.has(deviceId)) {
                patientAlerts.set(deviceId, { ultimoResultado: null, ultimoAlerta: null });
            }

            const alertState = patientAlerts.get(deviceId);
            const valorMudou = alertState.ultimoResultado !== bpm;

            if (bpm < 60) {
                if (valorMudou || alertState.ultimoAlerta !== 'baixo') {
                    mostrarAlerta(deviceId, patientName, 'baixo', mensagem, bpm);
                    alertState.ultimoAlerta = 'baixo';
                }
            } else if (bpm > 100) {
                if (valorMudou || alertState.ultimoAlerta !== 'alto') {
                    mostrarAlerta(deviceId, patientName, 'alto', mensagem, bpm);
                    alertState.ultimoAlerta = 'alto';
                }
            } else {
                if (alertState.ultimoAlerta !== null) {
                    fecharAlerta(deviceId);
                    alertState.ultimoAlerta = null;
                }
            }

            alertState.ultimoResultado = bpm;

        } catch (error) {
            console.error(`Erro ao monitorar batimentos (${deviceId}):`, error);
        }
    };

    verificar();
    const intervalId = setInterval(verificar, 2000);
    patientMonitors.set(deviceId, intervalId);
}

function mostrarAlerta(deviceId, patientName, tipo, mensagem, bpm) {
    const config = tipo === 'baixo' ? {
        icon: 'warning',
        title: `Bradicardia - ${patientName}`,
        confirmButtonColor: '#ffc107',
        customClass: {
            popup: 'swal-alert-fixed swal-warning'
        }
    } : {
        icon: 'error',
        title: `Taquicardia - ${patientName}`,
        confirmButtonColor: '#dc3545',
        customClass: {
            popup: 'swal-alert-fixed swal-danger'
        }
    };

    const htmlContent = `
        <p style="font-size: 1.1rem; margin-bottom: 10px; color: var(--text-primary);">${mensagem}</p>
        <p style="font-size: 0.9rem; color: var(--text-secondary);">
            ID: ${deviceId}<br>
            Batimentos cardíacos ${tipo === 'baixo' ? 'abaixo' : 'acima'} do esperado.
        </p>
    `;

    const alertState = patientAlerts.get(deviceId);

    if (alertState.alertInstance && alertState.alertInstance.isVisible()) {
        const popup = document.querySelector('.swal-alert-fixed');
        if (popup) {
            const titleEl = popup.querySelector('.swal2-title');
            if (titleEl) titleEl.textContent = config.title;

            const contentEl = popup.querySelector('.swal2-html-container');
            if (contentEl) contentEl.innerHTML = htmlContent;

            popup.className = 'swal2-popup swal2-toast swal2-show ' + config.customClass.popup;
        }
        return;
    }

    alertState.alertInstance = Swal.fire({
        ...config,
        html: htmlContent,
        position: 'bottom-end',
        toast: true,
        showConfirmButton: false,
        showCloseButton: false,
        timer: null,
        timerProgressBar: false,
        backdrop: false,
        allowOutsideClick: false,
        allowEscapeKey: false,
        allowEnterKey: false
    });
}

function fecharAlerta(deviceId) {
    const alertState = patientAlerts.get(deviceId);
    if (alertState && alertState.alertInstance && alertState.alertInstance.isVisible()) {
        Swal.close();
        alertState.alertInstance = null;
    }
}