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

    onAuthStateChanged(auth, async (user) => {

        if (user) {

            fetchUserInfo(user.uid);
            loadPatients();

            // NOVO: detectar dispositivo vindo da página devices
            const params = new URLSearchParams(window.location.search);
            const deviceFromUrl = params.get("id");

            if (deviceFromUrl) {
                await monitorarDispositivoDireto(deviceFromUrl);
            }

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
                    text: `O dispositivo ${deviceId} não existe no sistema.`,
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

        } catch (error) {
            console.error("Erro ao adicionar paciente:", error);
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
                <div class="vital-mini-title">Saturação</div>
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

            if (heartRateEl) heartRateEl.textContent = bpm;
            if (saturationEl) saturationEl.textContent = spo2;
            if (temperatureEl) temperatureEl.textContent = temp;

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

        if (bpm !== '--') {

            enviarMedicaoCompleta(deviceId, {
                heartRate: parseFloat(bpm),
                saturation: parseFloat(spo2),
                temperature: parseFloat(temp)
            });

        }

    };

    sendData();

    const intervalId = setInterval(sendData, API_CALL_INTERVAL_MS);

    patientSchedulers.set(deviceId, intervalId);

}

function iniciarMonitoramento(deviceId, patientName) {

    const vitalsRef = ref(rtdb, 'vitals/' + deviceId);

    const unsubscribe = onValue(vitalsRef, (snapshot) => {

        if (!snapshot.exists()) return;

        const bpm = snapshot.val().heartRate?.value;

        if (!bpm) return;

        if (bpm < 60) {
            Swal.fire({
                icon: 'warning',
                title: `Bradicardia - ${patientName}`,
                text: `Batimentos: ${bpm} bpm`
            });
        }

        if (bpm > 100) {
            Swal.fire({
                icon: 'error',
                title: `Taquicardia - ${patientName}`,
                text: `Batimentos: ${bpm} bpm`
            });
        }

    });

    patientMonitors.set(deviceId, unsubscribe);

}

async function monitorarDispositivoDireto(deviceId) {

    const vitalsRef = ref(rtdb, 'vitals/' + deviceId);

    const snapshot = await new Promise((resolve, reject) => {
        onValue(vitalsRef, resolve, reject, { onlyOnce: true });
    });

    if (!snapshot.exists()) {
        console.error("Dispositivo não encontrado:", deviceId);
        return;
    }

    const patientData = {
        name: "Dispositivo Monitorado",
        deviceId: deviceId
    };

    createPatientCard(patientData);
    listenToVitals(deviceId, patientData.name);
    startApiScheduler(deviceId);
    iniciarMonitoramento(deviceId, patientData.name);

    emptyState.style.display = 'none';
    patientsContainer.style.display = 'grid';

}