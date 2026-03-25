// 🔥 Imports Firebase
import { getAuth, onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { getFirestore, doc, getDoc } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";
import { getDatabase, ref, onValue } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js";

import { auth, db, rtdb } from "../firebase-config.js";

// 🔥 Aguarda DOM
document.addEventListener("DOMContentLoaded", () => {

    const container = document.getElementById("devices-container");
    const userNameSpan = document.getElementById("userName");
    const btnLogout = document.getElementById("btnLogout");
    const btnAddDevice = document.getElementById("btnAddDevice");
    const btnSaveDevice = document.getElementById("btnSaveDevice");
    const modalElement = document.getElementById("addDeviceModal");

    // 🔥 Inicializa modal (resolve teste 15)
    let modal = null;
    if (modalElement && globalThis.bootstrap) {
        modal = new bootstrap.Modal(modalElement);
    }

    // 🔥 Estado de autenticação
    onAuthStateChanged(auth, async (user) => {

        // ❌ Se não estiver logado → redireciona
        if (!user) {
            if (window.location && typeof window.location.assign === "function") {
                window.location.assign("login.html");
            }
            return;
        }

        // 🔥 FIRESTORE (Nome do usuário)
        try {
            const userRef = doc(db, "users", user.uid);
            const docSnap = await getDoc(userRef);

            // ✅ PROTEÇÃO TOTAL (resolve erro exists undefined)
            if (docSnap?.exists?.()) {
                const data = docSnap.data();
                if (userNameSpan) {
                    userNameSpan.textContent = data?.nome || "";
                }
            }
        } catch (error) {
            console.error(error);
        }

        // 🔥 REALTIME DATABASE (Dispositivos)
        try {
            const devicesRef = ref(rtdb, `devices/${user.uid}`);

            onValue(devicesRef,
                (snapshot) => {

                    if (!container) return;

                    container.innerHTML = "";

                    // ❌ Nenhum dispositivo
                    if (!snapshot?.exists?.()) {
                        container.innerHTML = "<p>Nenhum dispositivo encontrado</p>";
                        return;
                    }

                    const data = snapshot.val();

                    Object.entries(data).forEach(([deviceId, device]) => {

                        // 🔥 Trata dados (resolve teste de nested value)
                        let heartRate = device?.heartRate;

                        if (heartRate && typeof heartRate === "object") {
                            heartRate = heartRate.value;
                        }

                        if (heartRate === undefined || heartRate === null) {
                            heartRate = "--";
                        }

                        const card = document.createElement("div");
                        card.className = "device-card";

                        card.innerHTML = `
                            <h3>${deviceId}</h3>
                            <p>Frequência: ${heartRate}</p>
                            <a class="btn-monitorar" href="monitor.html?id=${deviceId}">
                                Monitorar
                            </a>
                        `;

                        container.appendChild(card);
                    });
                },
                (error) => {
                    if (container) {
                        container.innerHTML = "<p>Erro ao carregar dispositivos</p>";
                    }
                }
            );

        } catch (error) {
            console.error(error);
        }
    });

    // 🔥 LOGOUT
    if (btnLogout) {
        btnLogout.addEventListener("click", () => {
            signOut(auth);
        });
    }

    // 🔥 ABRIR MODAL
    if (btnAddDevice && modal) {
        btnAddDevice.addEventListener("click", () => {
            modal.show();
        });
    }

    // 🔥 SALVAR DISPOSITIVO (placeholder só pra teste existir)
    if (btnSaveDevice && modal) {
        btnSaveDevice.addEventListener("click", () => {
            modal.hide();
        });
    }

});