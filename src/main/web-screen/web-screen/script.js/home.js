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

const DEVICE_ID = "DCB4D905BF3C"; 
const API_BASE_URL = "http://localhost:8080";

let ultimoAlertaMostrado = null;
let ultimoResultado = null;

document.addEventListener('DOMContentLoaded', () => {
    onAuthStateChanged(auth, (user) => {
        if (user) {
            fetchUserInfo(user.uid);
            listenToVitals(DEVICE_ID);
            iniciarMonitoramento(); 
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


function iniciarMonitoramento() {
    setInterval(verificarBatimentos, 2000);
    
    verificarBatimentos();
}

async function verificarBatimentos() {
    try {
        const response = await fetch(`${API_BASE_URL}/api/medicao-lista/monitorar`);
        
        if (!response.ok) {
            console.error('Erro ao verificar batimentos:', response.status);
            return;
        }

        const resultado = await response.json();
        
        if (resultado.resultado === null) {
            console.log(resultado.mensagem);
            return;
        }

        const bpm = resultado.resultado;
        const mensagem = resultado.mensagem;

        const valorMudou = ultimoResultado !== bpm;
        
        if (bpm < 60) {
            if (valorMudou || ultimoAlertaMostrado !== 'baixo') {
                mostrarAlerta('baixo', mensagem, bpm);
                ultimoAlertaMostrado = 'baixo';
            }
        } 
        if (bpm > 100) {
            if (valorMudou || ultimoAlertaMostrado !== 'alto') {
                mostrarAlerta('alto', mensagem, bpm);
                ultimoAlertaMostrado = 'alto';
            }
        }else {
            if (ultimoAlertaMostrado !== null) {
                fecharAlerta();
                ultimoAlertaMostrado = null;
            }
        }

        ultimoResultado = bpm;

    } catch (error) {
        console.error('Erro ao monitorar batimentos:', error);
    }
}

let alertaAtual = null;

function mostrarAlerta(tipo, mensagem, bpm) {
    const config = tipo === 'baixo' ? {
        icon: 'warning',
        title: 'Bradicardia Detectada',
        confirmButtonColor: '#ffc107',
        customClass: {
            popup: 'swal-alert-fixed swal-warning'
        }
    } : {
        icon: 'error',
        title: 'Taquicardia Detectada',
        confirmButtonColor: '#dc3545',
        customClass: {
            popup: 'swal-alert-fixed swal-danger'
        }
    };

    const htmlContent = `
        <p style="font-size: 1.1rem; margin-bottom: 10px; color: var(--text-primary);">${mensagem}</p>
        <p style="font-size: 0.9rem; color: var(--text-secondary);">
            Batimentos cardíacos ${tipo === 'baixo' ? 'abaixo' : 'acima'} do esperado. 
            Verifique o paciente imediatamente.
        </p>
    `;

    if (alertaAtual && alertaAtual.isVisible()) {
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

    alertaAtual = Swal.fire({
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
        allowEnterKey: false,
        didOpen: (popup) => {
            popup.addEventListener('click', (e) => {
                e.stopPropagation();
                e.preventDefault();
            }, true);
            
            const container = popup.parentElement;
            if (container) {
                container.addEventListener('click', (e) => {
                    e.stopPropagation();
                    e.preventDefault();
                }, true);
            }
        }
    });
}

function fecharAlerta() {
    if (alertaAtual && alertaAtual.isVisible()) {
        Swal.close();
        alertaAtual = null;
    }
}