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

const DEVICE_ID = "04CDF8";

// --- Função Principal ---
document.addEventListener('DOMContentLoaded', () => {
    
    // 1. Verifica se o usuário está logado
    onAuthStateChanged(auth, (user) => {
        if (user) {
            // Se logado, busca info e consome o rtdb
            fetchUserInfo(user.uid);
            listenToVitals(DEVICE_ID);
        } else {
            // Se não logado, chuta de volta para o login
            window.location.href = '../login.html';
        }
    });

    // 2. Ação do botão de Logout
    btnLogout.addEventListener('click', () => {
        signOut(auth).then(() => {
            window.location.href = '../login.html';
        }).catch((error) => {
            console.error("Erro ao sair:", error);
        });
    });
});

// Busca o nome no fsdb
async function fetchUserInfo(uid) {
    // Busca na coleção 'users', no documento com o id 'uid'
    const userDocRef = doc(db, "users", uid);
    try {
        const docSnap = await getDoc(userDocRef);

        // se achar, atualiza o nome
        if (docSnap.exists()) {
            userNameEl.textContent = docSnap.data().nome;
        } else { //se não, deixa como "usuário"
            console.log("Usuário não encontrado no Firestore!");
            userNameEl.textContent = "Usuário";
        }
    } catch (error) { //deu ruim
        console.error("Erro ao buscar usuário:", error);
    }
}

// consome o rtdb
function listenToVitals(deviceId) {
    // Define o "caminho" no Realtime Database onde os dados estão
    const vitalsRef = ref(rtdb, 'vitals/' + deviceId);
    
    // ID na tela
    deviceIdEl.textContent = `ID: ${deviceId}`;

    // onValue() ouve mudanças EM TEMPO REAL
    onValue(vitalsRef, (snapshot) => {
        if (snapshot.exists()) {
            const data = snapshot.val();
            
            // Atualiza os valores na tela
            // '|| "--"' garante que mostre '--' se o dado for nulo
            heartRateEl.textContent = data.heartRate || '--';
            saturationEl.textContent = data.saturation || '--';
            temperatureEl.textContent = data.temperature || '--';
            
        } else {
            // Se o ID "04CDF8" não existir no RTDB
            console.log("Nenhum dado encontrado para este dispositivo.");
            heartRateEl.textContent = '--';
            saturationEl.textContent = '--';
            temperatureEl.textContent = '--';
        }
    });
}