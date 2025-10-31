// 1. Importe SÓ o 'auth' do seu arquivo de configuração
import { auth } from './firebase-config.js'; 

// 2. Importe as FUNÇÕES de login/cadastro
import { 
  createUserWithEmailAndPassword, 
  signInWithEmailAndPassword 
} from "https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js";

// 3. Pegue seus elementos HTML (com os IDs corretos)
const emailInput = document.getElementById('floatingInput');
const senhaInput = document.getElementById('floatingPassword');
const btnCadastrar = document.getElementById('btn-cadastrar');
const btnLogin = document.getElementById('btn-login');
const feedbackDiv = document.getElementById('feedback-div');

/**
 * Função para mostrar feedback ao usuário usando Alertas do Bootstrap
 * @param {string} mensagem - O texto a ser exibido.
 * @param {string} tipo - 'success' (verde) ou 'danger' (vermelho).
 */
function mostrarFeedback(mensagem, tipo = 'danger') {
  feedbackDiv.textContent = mensagem; 
  feedbackDiv.className = `alert alert-${tipo}`; // Reseta classes e aplica a cor
  feedbackDiv.classList.remove('d-none'); // Mostra a div
}

// --- Função de Cadastro ---
btnCadastrar.addEventListener('click', (e) => {
  e.preventDefault(); // Impede o formulário de recarregar a página
  const email = emailInput.value;
  const senha = senhaInput.value;
  feedbackDiv.classList.add('d-none'); // Esconde feedback antigo

  // Validação simples (opcional)
  if (!email || !senha) {
    mostrarFeedback('Por favor, preencha e-mail e senha.');
    return;
  }

  createUserWithEmailAndPassword(auth, email, senha)
    .then((userCredential) => {
      // Cadastrado com sucesso
      mostrarFeedback('Cadastro realizado com sucesso!', 'success');
      // Limpa os campos
      emailInput.value = '';
      senhaInput.value = '';
    })
    .catch((error) => {
      // Trata erros de cadastro
      if (error.code === 'auth/email-already-in-use') {
        mostrarFeedback('Este e-mail já está em uso.', 'danger');
      } else if (error.code === 'auth/weak-password') {
        mostrarFeedback('A senha é muito fraca (mínimo 6 caracteres).', 'danger');
      } else {
        mostrarFeedback('Erro ao cadastrar: ' + error.message, 'danger');
      }
    });
});

// --- Função de Login ---
btnLogin.addEventListener('click', (e) => {
  e.preventDefault(); // Impede o formulário de recarregar a página
  const email = emailInput.value;
  const senha = senhaInput.value;
  feedbackDiv.classList.add('d-none'); // Esconde feedback antigo

  // Validação simples (opcional)
  if (!email || !senha) {
    mostrarFeedback('Por favor, preencha e-mail e senha.');
    return;
  }

  signInWithEmailAndPassword(auth, email, senha)
    .then((userCredential) => {
      // Login com sucesso
      const user = userCredential.user;
      mostrarFeedback('Login realizado com sucesso! Redirecionando...', 'success');
      
      // Descomente a linha abaixo para redirecionar após 2 segundos
      // setTimeout(() => {
      //   window.location.href = 'dashboard.html'; 
      // }, 2000);
    })
    .catch((error) => {
      // Trata erros de login
      if (error.code === 'auth/invalid-credential' || error.code === 'auth/user-not-found' || error.code === 'auth/wrong-password') {
        mostrarFeedback('E-mail ou senha incorretos.', 'danger');
      } else {
        mostrarFeedback('Erro ao fazer login: ' + error.message, 'danger');
      }
    });
});