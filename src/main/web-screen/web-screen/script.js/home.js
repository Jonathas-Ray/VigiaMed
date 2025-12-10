// script.js/home.js

import { auth, db, rtdb } from '../firebase-config.js';
import { onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { doc, getDoc } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";
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

// ==================== SISTEMA DE NOTIFICAÇÕES ====================

function createNotificationContainer() {
    const container = document.createElement('div');
    container.id = 'notificationContainer';
    container.className = 'notification-container';
    document.body.appendChild(container);
    return container;
}

function showNotification(type, title, message, duration = 3000) {
    const container = document.getElementById('notificationContainer') || createNotificationContainer();
    
    const notification = document.createElement('div');
    notification.className = `custom-notification ${type}`;
    
    const iconMap = {
        success: 'bi-check-circle-fill',
        error: 'bi-x-circle-fill',
        warning: 'bi-exclamation-triangle-fill',
        info: 'bi-info-circle-fill'
    };
    
    notification.innerHTML = `
        <div class="notification-icon">
            <i class="bi ${iconMap[type]}"></i>
        </div>
        <div class="notification-content">
            <div class="notification-title">${title}</div>
            ${message ? `<div class="notification-message">${message}</div>` : ''}
        </div>
        <button class="notification-close">
            <i class="bi bi-x"></i>
        </button>
    `;
    
    const closeBtn = notification.querySelector('.notification-close');
    closeBtn.addEventListener('click', () => notification.remove());
    
    container.appendChild(notification);
    setTimeout(() => notification.classList.add('show'), 10);
    
    if (duration > 0) {
        setTimeout(() => {
            notification.classList.remove('show');
            setTimeout(() => notification.remove(), 300);
        }, duration);
    }
    
    return notification;
}

function showConfirmDialog(title, message, confirmText = 'Confirmar', cancelText = 'Cancelar') {
    return new Promise((resolve) => {
        const overlay = document.createElement('div');
        overlay.className = 'custom-modal-overlay';
        
        const modal = document.createElement('div');
        modal.className = 'custom-modal';
        modal.innerHTML = `
            <div class="custom-modal-header">
                <i class="bi bi-exclamation-triangle-fill" style="color: #ffc107;"></i>
                <h3>${title}</h3>
            </div>
            <div class="custom-modal-body">
                <p>${message}</p>
            </div>
            <div class="custom-modal-footer">
                <button class="btn-modal-cancel">${cancelText}</button>
                <button class="btn-modal-confirm">${confirmText}</button>
            </div>
        `;
        
        overlay.appendChild(modal);
        document.body.appendChild(overlay);
        
        setTimeout(() => overlay.classList.add('show'), 10);
        
        const closeModal = (result) => {
            overlay.classList.remove('show');
            setTimeout(() => {
                overlay.remove();
                resolve(result);
            }, 300);
        };
        
        const cancelBtn = modal.querySelector('.btn-modal-cancel');
        const confirmBtn = modal.querySelector('.btn-modal-confirm');
        
        cancelBtn.addEventListener('click', (e) => {
            e.stopPropagation();
            closeModal(false);
        });
        
        confirmBtn.addEventListener('click', (e) => {
            e.stopPropagation();
            closeModal(true);
        });
        
        overlay.addEventListener('click', (e) => {
            if (e.target === overlay) closeModal(false);
        });
    });
}

// ==================== INICIALIZAÇÃO ====================

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
});

// ==================== FUNÇÕES DE PACIENTES ====================

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
                <button class="btn-remove-patient" data-device-id="${patientData.deviceId}" data-patient-name="${patientData.name}">
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
    
    const removeBtn = document.querySelector(`[data-device-id="${patientData.deviceId}"]`);
    if (removeBtn) {
        removeBtn.addEventListener('click', async (e) => {
            e.preventDefault();
            e.stopPropagation();
            await removePatient(patientData.deviceId, patientData.name);
        });
    }
}

async function removePatient(deviceId, patientName) {
    try {
        const confirmed = await showConfirmDialog(
            'Remover paciente?',
            `Deseja remover ${patientName}?`,
            'Sim, remover',
            'Cancelar'
        );

        if (!confirmed) return;

        // Remover card da tela com animação
        const patientCard = document.getElementById(`patient-${deviceId}`);
        if (patientCard) {
            patientCard.style.opacity = '0';
            patientCard.style.transform = 'scale(0.8)';
            setTimeout(() => patientCard.remove(), 300);
        }

        // Deletar do Firestore
        await deleteDoc(doc(db, "patients", deviceId));
        
        // Remover listener
        if (activeListeners.has(deviceId)) {
            activeListeners.get(deviceId)();
            activeListeners.delete(deviceId);
        }
        
        // Remover scheduler de API
        if (patientSchedulers.has(deviceId)) {
            clearInterval(patientSchedulers.get(deviceId));
            patientSchedulers.delete(deviceId);
        }

        // Remover scheduler de monitoramento
        if (patientSchedulers.has(`monitor-${deviceId}`)) {
            clearInterval(patientSchedulers.get(`monitor-${deviceId}`));
            patientSchedulers.delete(`monitor-${deviceId}`);
        }

        // Fechar e remover alerta
        if (patientAlerts.has(deviceId)) {
            const alertState = patientAlerts.get(deviceId);
            if (alertState.notification) {
                alertState.notification.remove();
            }
            patientAlerts.delete(deviceId);
        }

        // Verificar se ainda há pacientes
        setTimeout(() => {
            const remainingPatients = document.querySelectorAll('.patient-group');
            if (remainingPatients.length === 0) {
                emptyState.style.display = 'flex';
                patientsContainer.style.display = 'none';
            }
        }, 350);
        
        showNotification('success', 'Paciente removido!', '', 2000);

    } catch (error) {
        console.error("Erro ao remover paciente:", error);
        showNotification('error', 'Erro', 'Não foi possível remover o paciente.');
        loadPatients();
    }
}

// ==================== FUNÇÕES DE MONITORAMENTO ====================

function updateValue(element, newValue) {
    if (!element) return;
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

            const heartRateEl = document.getElementById(`heartRate-${deviceId}`);
            const saturationEl = document.getElementById(`saturation-${deviceId}`);
            const temperatureEl = document.getElementById(`temperature-${deviceId}`);

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


function iniciarMonitoramento() {
    setInterval(verificarBatimentos, 2000);
    
    const notification = document.createElement('div');
    notification.className = `custom-notification alert-persistent ${tipo === 'baixo' ? 'warning' : 'error'}`;
    
    notification.innerHTML = `
        <div class="notification-icon">
            <i class="bi ${tipo === 'baixo' ? 'bi-exclamation-triangle-fill' : 'bi-heart-pulse-fill'}"></i>
        </div>
        <div class="notification-content">
            <div class="notification-title">${tipo === 'baixo' ? '⚠️ Bradicardia' : '🚨 Taquicardia'} - ${patientName}</div>
            <div class="notification-message">${mensagem}<br><small>ID: ${deviceId} | Batimentos ${tipo === 'baixo' ? 'abaixo' : 'acima'} do esperado</small></div>
        </div>
        <div class="notification-pulse"></div>
    `;
    
    container.appendChild(notification);
    setTimeout(() => notification.classList.add('show'), 10);
    
    alertState.notification = notification;
}

function fecharAlerta(deviceId) {
    const alertState = patientAlerts.get(deviceId);
    if (alertState && alertState.notification) {
        alertState.notification.classList.remove('show');
        setTimeout(() => {
            alertState.notification.remove();
            alertState.notification = null;
        }, 300);
    }
}