// firebase-config.js

import { initializeApp } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-app.js"; 
import { getAuth, GoogleAuthProvider } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { getDatabase } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js";
import { getFirestore } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";

// Sua configuração
const firebaseConfig = {
  apiKey: "AIzaSyBXfwrisYnahyHLtlL_hQzgRtj6NlfN-9M",
  authDomain: "autenticacao-vigiamed.firebaseapp.com",
  projectId: "autenticacao-vigiamed",
  storageBucket: "autenticacao-vigiamed.firebasestorage.app",
  messagingSenderId: "637556186469",
  appId: "1:637556186469:web:8a082146d4e479d63b158c"
};

// Inicializa os serviços
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const db = getFirestore(app);
const rtdb = getDatabase(app);

// [NOVO] Cria o provedor de login do Google
const googleProvider = new GoogleAuthProvider();

// Exporta tudo que seus scripts precisam
export { auth, db, rtdb, googleProvider };