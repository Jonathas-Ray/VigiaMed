// Validação do formulário de login
const loginForm = document.getElementById('loginForm');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const loginBtn = document.getElementById('loginBtn');
const emailError = document.getElementById('emailError');
const passwordError = document.getElementById('passwordError');

// Função para validar email
function validateEmail(email) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

// Limpar erros
function clearError(input, errorElement) {
  input.classList.remove('is-invalid');
  errorElement.textContent = '';
}

// Mostrar erro
function showError(input, errorElement, message) {
  input.classList.add('is-invalid');
  errorElement.textContent = message;
}

// Validação Email em tempo real
emailInput.addEventListener('input', function () {
  if (this.value.trim() === '') {
    clearError(this, emailError);
  } else if (!validateEmail(this.value)) {
    showError(this, emailError, 'Email inválido');
  } else {
    clearError(this, emailError);
  }
});

// Validação Senha em tempo real
passwordInput.addEventListener('input', function () {
  if (this.value.trim() === '') {
    clearError(this, passwordError);
  } else if (this.value.length < 8) {
    showError(this, passwordError, 'Mínimo 8 caracteres');
  } else {
    clearError(this, passwordError);
  }
});

// Submissão do formulário
loginForm.addEventListener('submit', function(e) {
    e.preventDefault();
    
    let isValid = true;
    let errorMessages = [];

    // Validar email
    if (emailInput.value.trim() === '') {
        showError(emailInput, emailError, 'Por favor, insira seu email');
        errorMessages.push('Por favor, insira seu email');
        isValid = false;
    } else if (!validateEmail(emailInput.value)) {
        showError(emailInput, emailError, 'Por favor, insira um email válido');
        errorMessages.push('Por favor, insira um email válido');
        isValid = false;
    } else {
        clearError(emailInput, emailError);
    }

    // Validar senha
    if (passwordInput.value.trim() === '') {
        showError(passwordInput, passwordError, 'Por favor, insira sua senha');
        errorMessages.push('Por favor, insira sua senha');
        isValid = false;
    } else if (passwordInput.value.length < 8) {
        showError(passwordInput, passwordError, 'A senha deve ter pelo menos 8 caracteres');
        errorMessages.push('A senha deve ter pelo menos 8 caracteres');
        isValid = false;
    } else {
        clearError(passwordInput, passwordError);
    }

    // Mostrar popup com erros se houver
    if (!isValid) {
        alert('⚠️ Erro de validação:\n\n' + errorMessages.join('\n'));
        return;
    }

    // Se tudo estiver válido, processar login
    loginBtn.disabled = true;
    loginBtn.textContent = 'Entrando...';

    // Simular processo de login
    setTimeout(() => {
        const rememberMe = document.getElementById('rememberMe').checked;
        alert('✅ Login realizado com sucesso!\n\nEmail: ' + emailInput.value + '\nLembrar: ' + (rememberMe ? 'Sim' : 'Não'));
        
        loginBtn.disabled = false;
        loginBtn.textContent = 'Entrar';
    }, 1000);
});

// Botões de login social (simulação)
document.getElementById('google').addEventListener('click', function () {
  alert('Login com Google - Funcionalidade a ser implementada');
});

document.getElementById('apple').addEventListener('click', function () {
  alert('Login com Apple - Funcionalidade a ser implementada');
});

document.getElementById('facebook').addEventListener('click', function () {
  alert('Login com Facebook - Funcionalidade a ser implementada');
});