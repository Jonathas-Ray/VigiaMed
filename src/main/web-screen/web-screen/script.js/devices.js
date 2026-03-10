import { auth, db, rtdb } from '../firebase-config.js';

import { onAuthStateChanged, signOut }
from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";

import { doc, getDoc }
from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";

import { ref, onValue, remove, set }
from "https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js";


const devicesContainer = document.getElementById('devices-container');
const userNameEl = document.getElementById('userName');
const btnLogout = document.getElementById('btnLogout');

const btnAddDevice = document.getElementById('btnAddDevice');
const btnSaveDevice = document.getElementById('btnSaveDevice');
const deviceIdInput = document.getElementById('deviceIdInput');


document.addEventListener('DOMContentLoaded', () => {

onAuthStateChanged(auth, (user) => {

if (user) {

fetchUserInfo(user.uid);
listenToDevices();

}
else {

window.location.href = 'login.html';

}

});


btnLogout.addEventListener('click', () => {

signOut(auth).then(() => {
window.location.href = 'login.html';
});

});


btnAddDevice.addEventListener('click', () => {

const modal = new bootstrap.Modal(document.getElementById('addDeviceModal'));
modal.show();

});


btnSaveDevice.addEventListener('click', addDevice);

});


async function fetchUserInfo(uid) {

try {

const docSnap = await getDoc(doc(db, "users", uid));

if (docSnap.exists()) {
userNameEl.textContent = docSnap.data().nome;
}

}
catch (e) {

console.error("Erro ao buscar usuário:", e);

}

}



function listenToDevices() {

const vitalsRef = ref(rtdb, 'vitals');

onValue(vitalsRef, (snapshot) => {

devicesContainer.innerHTML = '';

if (snapshot.exists()) {

const data = snapshot.val();

Object.entries(data).forEach(([deviceId, sensorData]) => {

createDeviceCard(deviceId, sensorData);

});

}
else {

devicesContainer.innerHTML = `
<div class="col-12 text-center text-secondary mt-5">
<i class="bi bi-hdd-network fs-1 mb-3 d-block"></i>
<p>Nenhum dispositivo encontrado.</p>
</div>
`;

}

}, (error) => {

console.error("Erro de permissão ou conexão:", error);

devicesContainer.innerHTML =
`<p class="text-danger text-center">Erro ao conectar com o banco de dados.</p>`;

});

}



function createDeviceCard(id, data) {

const bpm = data.heartRate?.value || data.heartRate || '--';
const spo2 = data.spo2?.value || data.saturation || '--';

const cardHTML = `

<div class="col-md-6 col-lg-4 col-xl-3">

<div class="device-card h-100 d-flex flex-column justify-content-between">

<div class="d-flex justify-content-between align-items-start mb-3">

<div class="d-flex align-items-center gap-2">

<div class="bg-dark rounded-circle p-2 d-flex align-items-center justify-content-center" style="width:40px;height:40px;">
<i class="bi bi-cpu text-success"></i>
</div>

<div>
<span class="badge bg-success bg-opacity-10 text-success mb-1">Online</span>
<h6 class="text-white m-0 device-id fw-bold">${id}</h6>
</div>

</div>

<button class="btn btn-sm btn-outline-danger" onclick="deleteDevice('${id}')">
<i class="bi bi-trash"></i>
</button>

</div>

<div class="row g-2 mb-4">

<div class="col-6">
<div class="bg-dark rounded p-2 text-center border border-secondary">
<small class="text-secondary d-block" style="font-size:0.7rem;">BPM</small>
<span class="fw-bold text-white fs-5">${bpm}</span>
</div>
</div>

<div class="col-6">
<div class="bg-dark rounded p-2 text-center border border-secondary">
<small class="text-secondary d-block" style="font-size:0.7rem;">SpO2</small>
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

devicesContainer.innerHTML += cardHTML;

}



function deleteDevice(deviceId) {

const confirmDelete = confirm(`Tem certeza que deseja deletar o dispositivo ${deviceId}?`);

if (!confirmDelete) return;

const deviceRef = ref(rtdb, `vitals/${deviceId}`);

remove(deviceRef)
.then(() => {

console.log("Dispositivo removido.");

})
.catch((error) => {

console.error("Erro ao remover dispositivo:", error);
alert("Erro ao remover dispositivo.");

});

}

window.deleteDevice = deleteDevice;



function addDevice() {

const deviceId = deviceIdInput.value.trim();

if (!deviceId) {
alert("Digite um ID para o dispositivo.");
return;
}

const deviceRef = ref(rtdb, `vitals/${deviceId}`);

set(deviceRef, {
heartRate: 0,
spo2: 0
})
.then(() => {

deviceIdInput.value = "";

const modal = bootstrap.Modal.getInstance(document.getElementById('addDeviceModal'));
modal.hide();

})
.catch((error) => {

console.error("Erro ao criar dispositivo:", error);
alert("Erro ao criar dispositivo.");

});

}