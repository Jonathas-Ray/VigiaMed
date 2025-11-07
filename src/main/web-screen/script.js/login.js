// Coloque este arquivo em: script.js/login.js

// [MUDANÇA] Importa de ../ (um nível acima)
import { auth, db, googleProvider } from '../firebase-config.js'; 
import { signInWithEmailAndPassword, signInWithPopup } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { doc, setDoc } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";

// --- Seleção dos Elementos ---
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
// Pega o botão "Entrar" (que é do tipo 'submit')
const btnLogin = document.querySelector('button[type="submit"]');
// Pega o botão do Google
const btnGoogle = document.getElementById('google');

// --- 1. LOGIN COM E-MAIL E SENHA ---
btnLogin.addEventListener('click', (e) => {
    e.preventDefault(); // Impede o formulário de recarregar a página

    const email = emailInput.value;
    const password = passwordInput.value;

    if (!email || !password) {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Por favor, preencha e-mail e senha.',
            background: '#343a40', // Cor de fundo escura
            color: '#f8f9fa'       // Cor do texto
        });
        return;
    }

    signInWithEmailAndPassword(auth, email, password)
        .then((userCredential) => {
            // Sucesso!
            Swal.fire({
                icon: 'success',
                title: 'Login realizado!',
                text: 'Redirecionando...',
                timer: 2000,
                showConfirmButton: false,
                background: '#343a40',
                color: '#f8f9fa'
            }).then(() => {
                window.location.href = 'mainScreen.html'; // Redireciona
            });
        })
        .catch((error) => {
            // Erro!
            console.error("Erro no login:", error.code);
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

// --- 2. LOGIN COM GOOGLE ---
btnGoogle.addEventListener('click', () => {
    signInWithPopup(auth, googleProvider)
        .then((result) => {
            // O usuário logou com o Google
            const user = result.user;

            // Salva/Atualiza os dados dele no Firestore (igual fazemos no cadastro)
            // 'merge: true' garante que não sobrescrevemos dados se o user já existir
            setDoc(doc(db, "users", user.uid), {
                nome: user.displayName,
                email: user.email,
                uid: user.uid
            }, { merge: true })
            .then(() => {
                // Sucesso ao logar E salvar dados
                Swal.fire({
                    icon: 'success',
                    title: `Bem-vindo, ${user.displayName}!`,
                    text: 'Redirecionando...',
                    timer: 2000,
                    showConfirmButton: false,
                    background: '#343a40',
                    color: '#f8f9fa'
                }).then(() => {
                    window.location.href = '../'; // Redireciona
                });
            });
            
        })
        .catch((error) => {
            // Erro no login com Google
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