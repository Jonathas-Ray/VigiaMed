// Seletores principais
const cadastroForm = document.getElementById('registerForm');
const nomeInput = document.getElementById('nome');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirm-password');
const termosInput = document.getElementById('termos');
const cadastroBtn = document.getElementById('registerBtn');

const nomeError = document.getElementById('nomeError');
const emailError = document.getElementById('emailError');
const senhaError = document.getElementById('senhaError');
const confirmSenhaError = document.getElementById('confirmSenhaError');

// Função para validar email
function validateEmail(email) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

// Limpar erro
function clearError(input, errorElement) {
  input.classList.remove('is-invalid');
  errorElement.textContent = '';
}

// Mostrar erro
function showError(input, errorElement, message) {
  input.classList.add('is-invalid');
  errorElement.textContent = message;
}

// Validação de nome em tempo real
nomeInput.addEventListener('input', function () {
  if (this.value.trim() === '') {
    clearError(this, nomeError);
  } else if (this.value.trim().length < 3) {
    showError(this, nomeError, 'Nome muito curto');
  } else {
    clearError(this, nomeError);
  }
});

// Validação de email em tempo real
emailInput.addEventListener('input', function () {
  if (this.value.trim() === '') {
    clearError(this, emailError);
  } else if (!validateEmail(this.value)) {
    showError(this, emailError, 'Email inválido');
  } else {
    clearError(this, emailError);
  }
});

// Validação de senha em tempo real
passwordInput.addEventListener('input', function () {
  if (this.value.trim() === '') {
    clearError(this, senhaError);
  } else if (this.value.length < 8) {
    showError(this, senhaError, 'Mínimo 8 caracteres');
  } else {
    clearError(this, senhaError);
  }
});

// Validação de confirmação de senha em tempo real
confirmPasswordInput.addEventListener('input', function () {
  if (this.value.trim() === '') {
    clearError(this, confirmSenhaError);
  } else if (this.value !== passwordInput.value) {
    showError(this, confirmSenhaError, 'As senhas não coincidem');
  } else {
    clearError(this, confirmSenhaError);
  }
});

// Submissão do formulário
cadastroForm.addEventListener('submit', function (e) {
  e.preventDefault();

  let isValid = true;
  let errorMessages = [];

  // Validar nome
  if (nomeInput.value.trim() === '') {
    showError(nomeInput, nomeError, 'Por favor, insira seu nome');
    errorMessages.push('Por favor, insira seu nome');
    isValid = false;
  }

  // Validar email
  if (emailInput.value.trim() === '') {
    showError(emailInput, emailError, 'Por favor, insira seu email');
    errorMessages.push('Por favor, insira seu email');
    isValid = false;
  } else if (!validateEmail(emailInput.value)) {
    showError(emailInput, emailError, 'Por favor, insira um email válido');
    errorMessages.push('Por favor, insira um email válido');
    isValid = false;
  }

  // Validar senha
  if (passwordInput.value.trim() === '') {
    showError(passwordInput, senhaError, 'Por favor, insira uma senha');
    errorMessages.push('Por favor, insira uma senha');
    isValid = false;
  } else if (passwordInput.value.length < 8) {
    showError(passwordInput, senhaError, 'A senha deve ter pelo menos 8 caracteres');
    errorMessages.push('A senha deve ter pelo menos 8 caracteres');
    isValid = false;
  }

  // Validar confirmação de senha
  if (confirmPasswordInput.value.trim() === '') {
    showError(confirmPasswordInput, confirmSenhaError, 'Por favor, confirme sua senha');
    errorMessages.push('Por favor, confirme sua senha');
    isValid = false;
  } else if (confirmPasswordInput.value !== passwordInput.value) {
    showError(confirmPasswordInput, confirmSenhaError, 'As senhas não coincidem');
    errorMessages.push('As senhas não coincidem');
    isValid = false;
  }

  // Validar aceite dos termos
  if (!termosInput.checked) {
    errorMessages.push('Você deve aceitar os termos e condições');
    isValid = false;
  }

  // Mostrar popup de erro
  if (!isValid) {
    alert('⚠️ Erro de validação:\n\n' + errorMessages.join('\n'));
    return;
  }

  // Se tudo estiver válido
  cadastroBtn.disabled = true;
  cadastroBtn.textContent = 'Cadastrando...';

  setTimeout(() => {
    alert('✅ Cadastro realizado com sucesso!\n\nEmail: ' + emailInput.value);
    cadastroBtn.disabled = false;
    cadastroBtn.textContent = 'Cadastrar';
    cadastroForm.reset();
  }, 1000);
});