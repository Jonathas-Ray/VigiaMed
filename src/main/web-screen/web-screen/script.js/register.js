import { auth, db, googleProvider } from '../firebase-config.js';
import { createUserWithEmailAndPassword, signInWithPopup } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";
import { doc, setDoc } from "https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js";


const cadastroForm = document.getElementById('registerForm');
const nomeInput = document.getElementById('nome');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirm-password');
const termosInput = document.getElementById('termos');
const cadastroBtn = document.getElementById('registerBtn');
const btnGoogle = document.getElementById('google');
const nomeError = document.getElementById('nomeError');
const emailError = document.getElementById('emailError');
const senhaError = document.getElementById('senhaError');
const confirmSenhaError = document.getElementById('confirmSenhaError');

function validateEmail(email) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

function clearError(input, errorElement) {
  input.classList.remove('is-invalid');
  errorElement.textContent = '';
}


function showError(input, errorElement, message) {
  input.classList.add('is-invalid');
  errorElement.textContent = message;
}

function showSwalError(title, text) {
  Swal.fire({
    icon: 'error',
    title: title,
    text: text,
    background: '#343a40',
    color: '#f8f9fa'
  });
}

nomeInput.addEventListener('input', () => {
  if (nomeInput.value.trim().length < 3 && nomeInput.value.trim().length > 0) {
    showError(nomeInput, nomeError, 'Nome muito curto');
  } else {
    clearError(nomeInput, nomeError);
  }
});

emailInput.addEventListener('input', () => {
  if (!validateEmail(emailInput.value) && emailInput.value.trim().length > 0) {
    showError(emailInput, emailError, 'Email inválido');
  } else {
    clearError(emailInput, emailError);
  }
});

passwordInput.addEventListener('input', () => {
  if (passwordInput.value.length < 8 && passwordInput.value.length > 0) {
    showError(passwordInput, senhaError, 'Mínimo 8 caracteres');
  } else {
    clearError(passwordInput, senhaError);
  }
});

confirmPasswordInput.addEventListener('input', () => {
  if (confirmPasswordInput.value !== passwordInput.value && confirmPasswordInput.value.length > 0) {
    showError(confirmPasswordInput, confirmSenhaError, 'As senhas não coincidem');
  } else {
    clearError(confirmPasswordInput, confirmSenhaError);
  }
});

cadastroForm.addEventListener('submit', (e) => {
  e.preventDefault();
  let isValid = true;

  if (nomeInput.value.trim() === '' || nomeInput.value.trim().length < 3) {
    showError(nomeInput, nomeError, 'Por favor, insira seu nome completo');
    isValid = false;
  }

  if (emailInput.value.trim() === '' || !validateEmail(emailInput.value)) {
    showError(emailInput, emailError, 'Por favor, insira um email válido');
    isValid = false;
  }

  if (passwordInput.value.length < 8) {
    showError(passwordInput, senhaError, 'A senha deve ter pelo menos 8 caracteres');
    isValid = false;
  }

  if (confirmPasswordInput.value !== passwordInput.value) {
    showError(confirmPasswordInput, confirmSenhaError, 'As senhas não coincidem');
    isValid = false;
  }

  if (!termosInput.checked) {
    isValid = false;
    showSwalError('Termos e Condições', 'Você deve aceitar os termos e condições para continuar.');
  }

  if (!isValid) {
    return; 
  }

  cadastroBtn.disabled = true;
  cadastroBtn.textContent = 'Cadastrando...';

  const nome = nomeInput.value;
  const email = emailInput.value;
  const password = passwordInput.value;

  createUserWithEmailAndPassword(auth, email, password)
    .then((userCredential) => {
      const user = userCredential.user;

      return setDoc(doc(db, "users", user.uid), {
        nome: nome,
        email: email,
        uid: user.uid
      });
    })
    .then(() => {
      Swal.fire({
        icon: 'success',
        title: 'Cadastro realizado!',
        text: 'Redirecionando para o login...',
        timer: 2000,
        showConfirmButton: false,
        background: '#343a40',
        color: '#f8f9fa'
      }).then(() => {
        window.location.href = "../html/login.html";
      });
    })
    .catch((error) => {
      console.error("Erro no cadastro:", error.code);
      if (error.code === 'auth/email-already-in-use') {
        showSwalError('Falha no Cadastro', 'Este e-mail já está em uso.');
        showError(emailInput, emailError, 'Este e-mail já está em uso.');
      } else {
        showSwalError('Falha no Cadastro', error.message);
      }
    })
    .finally(() => {
      cadastroBtn.disabled = false;
      cadastroBtn.textContent = 'Cadastrar';
    });
});

btnGoogle.addEventListener('click', () => {
  signInWithPopup(auth, googleProvider)
    .then((result) => {
      const user = result.user;

      return setDoc(doc(db, "users", user.uid), {
        nome: user.displayName,
        email: user.email,
        uid: user.uid
      }, { merge: true }); 
    })
    .then(() => {
      Swal.fire({
        icon: 'success',
        title: 'Login com Google bem-sucedido!',
        text: 'Redirecionando...',
        timer: 2000,
        showConfirmButton: false,
        background: '#343a40',
        color: '#f8f9fa'
      }).then(() => {
        window.location.href = "../html/login.html";
      });
    })
    .catch((error) => {
      console.error("Erro no login com Google:", error);
      showSwalError('Falha no Login', 'Não foi possível fazer login com o Google.');
    });
});