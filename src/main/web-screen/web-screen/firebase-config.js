// firebase-config.js

// 1. Mantenha todas as importações do arquivo ANTIGO
import { initializeApp } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-app.js"; 
import { getAuth, GoogleAuthProvider } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { getDatabase } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js";
import { getFirestore } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";

// 2. Cole o NOVO objeto firebaseConfig aqui
// (Este é o objeto do seu novo projeto "vigiamed")
const firebaseConfig = {
  apiKey: "AIzaSyDfAb5ixYgDdOgGO8THkwV_kpLWVCOVyR0",
  authDomain: "vigiamed.firebaseapp.com",
  projectId: "vigiamed",
  storageBucket: "vigiamed.firebasestorage.app",
  messagingSenderId: "846877057053",
  appId: "1:846877057053:web:355a06bd083845340666f4",
  measurementId: "G-9YZ49DYS80",
  databaseURL: "https://vigiamed-default-rtdb.firebaseio.com"
};

// 3. Mantenha toda a inicialização e exports do arquivo ANTIGO
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const db = getFirestore(app);
const rtdb = getDatabase(app);
const googleProvider = new GoogleAuthProvider();

export { auth, db, rtdb, googleProvider };