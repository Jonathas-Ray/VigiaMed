import { auth, db, googleProvider } from '../firebase-config.js'; 
import { signInWithEmailAndPassword, signInWithPopup } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { doc, setDoc } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";

const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const btnLogin = document.querySelector('button[type="submit"]');
const btnGoogle = document.getElementById('google');

btnLogin.addEventListener('click', (e) => {
    e.preventDefault(); 

    const email = emailInput.value;
    const password = passwordInput.value;

    if (!email || !password) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Por favor, preencha e-mail e senha.',
            background: '#343a40', 
            color: '#f8f9fa'       
        });
        return;
    }

    signInWithEmailAndPassword(auth, email, password)
        .then((userCredential) => {
            Swal.fire({
                icon: 'success',
                title: 'Login realizado!',
                text: 'Redirecionando...',
                timer: 2000,
                showConfirmButton: false,
                background: '#343a40',
                color: '#f8f9fa'
            }).then(() => {
                window.location.href = 'home.html'; 
            });
        })
        .catch((error) => {
            let mensagemErro = 'Ocorreu um erro ao fazer login.';
            if (error.code === 'auth/invalid-credential') {
                mensagemErro = 'E-mail ou senha incorretos.';
            }
            Swal.fire({
                icon: 'error',
                title: 'Falha no Login',
                text: mensagemErro,
                background: '#343a40',
                color: '#f8f9fa'
            });
        });
});

btnGoogle.addEventListener('click', () => {
    signInWithPopup(auth, googleProvider)
        .then((result) => {
            const user = result.user;

            setDoc(doc(db, "users", user.uid), {
                nome: user.displayName,
                email: user.email,
                uid: user.uid
            }, { merge: true })
            .then(() => {
                Swal.fire({
                    icon: 'success',
                    title: `Bem-vindo, ${user.displayName}!`,
                    text: 'Redirecionando...',
                    timer: 2000,
                    showConfirmButton: false,
                    background: '#343a40',
                    color: '#f8f9fa'
                }).then(() => {
                    window.location.href = '../html/home.html'; 
                });
            });
            
        })
        .catch((error) => {
            console.error("Erro no login com Google:", error);
            Swal.fire({
                icon: 'error',
                title: 'Falha no Login',
                text: 'Não foi possível fazer login com o Google.',
                background: '#343a40',
                color: '#f8f9fa'
            });
        });
});