// Importe as funções que você precisa
import { initializeApp } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-app.js";
import { getAuth } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { getDatabase } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js";

// Sua configuração (do seu HTML original)
const firebaseConfig = {
  apiKey: "AIzaSyBXfwrisYnahyHLtlL_hQzgRtj6NlfN-9M",
  authDomain: "autenticacao-vigiamed.firebaseapp.com",
  projectId: "autenticacao-vigiamed",
  storageBucket: "autenticacao-vigiamed.firebasestorage.app",
  messagingSenderId: "637556186469",
  appId: "1:637556186469:web:8a082146d4e479d63b158c"
};

// Inicializa o Firebase
const app = initializeApp(firebaseConfig);

// Exporta os serviços para o app.js usar
export const auth = getAuth(app);
export const db = getDatabase(app);