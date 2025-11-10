// Importar Firebase
import { initializeApp } from 'https://www.gstatic.com/firebasejs/10.7.1/firebase-app.js';
import { 
    getAuth, 
    createUserWithEmailAndPassword,
    updateProfile,
    GoogleAuthProvider,
    signInWithPopup
} from 'https://www.gstatic.com/firebasejs/10.7.1/firebase-auth.js';

// IMPORTANTE: Substitua com suas credenciais do Firebase
const firebaseConfig = {
    apiKey: "SUA_API_KEY_AQUI",
    authDomain: "seu-projeto.firebaseapp.com",
    projectId: "seu-projeto-id",
    storageBucket: "seu-projeto.appspot.com",
    messagingSenderId: "123456789",
    appId: "seu-app-id"
};

// Inicializar Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const googleProvider = new GoogleAuthProvider();

// Elementos do formulário
const registerForm = document.getElementById('registerForm');
const nomeInput = document.getElementById('nome');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirm-password');
const termosCheckbox = document.getElementById('termos');
const registerBtn = document.getElementById('registerBtn');

// Elementos de erro
const nomeError = document.getElementById('nomeError');
const emailError = document.getElementById('emailError');
const senhaError = document.getElementById('senhaError');
const confirmSenhaError = document.getElementById('confirmSenhaError');

// Ícones de erro
const nomeIcon = document.getElementById('nomeIcon');
const emailIcon = document.getElementById('emailIcon');
const senhaIcon = document.getElementById('senhaIcon');
const confirmSenhaIcon = document.getElementById('confirmSenhaIcon');

// Funções de validação
function showError(input, errorElement, iconElement, message) {
    input.classList.add('is-invalid');
    if (errorElement) errorElement.textContent = message;
    if (iconElement) iconElement.classList.remove('d-none');
}

function clearError(input, errorElement, iconElement) {
    input.classList.remove('is-invalid');
    if (errorElement) errorElement.textContent = '';
    if (iconElement) iconElement.classList.add('d-none');
}

function validateNome(nome) {
    if (!nome.trim()) {
        showError(nomeInput, nomeError, nomeIcon, 'Nome completo é obrigatório');
        return false;
    }
    if (nome.trim().length < 3) {
        showError(nomeInput, nomeError, nomeIcon, 'Nome deve ter pelo menos 3 caracteres');
        return false;
    }
    clearError(nomeInput, nomeError, nomeIcon);
    return true;
}

function validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!email.trim()) {
        showError(emailInput, emailError, emailIcon, 'Email é obrigatório');
        return false;
    }
    if (!emailRegex.test(email)) {
        showError(emailInput, emailError, emailIcon, 'Email inválido');
        return false;
    }
    clearError(emailInput, emailError, emailIcon);
    return true;
}

function validatePassword(password) {
    if (!password) {
        showError(passwordInput, senhaError, senhaIcon, 'Senha é obrigatória');
        return false;
    }
    if (password.length < 6) {
        showError(passwordInput, senhaError, senhaIcon, 'Senha deve ter pelo menos 6 caracteres');
        return false;
    }
    clearError(passwordInput, senhaError, senhaIcon);
    return true;
}

function validateConfirmPassword(password, confirmPassword) {
    if (!confirmPassword) {
        showError(confirmPasswordInput, confirmSenhaError, confirmSenhaIcon, 'Confirmação de senha é obrigatória');
        return false;
    }
    if (password !== confirmPassword) {
        showError(confirmPasswordInput, confirmSenhaError, confirmSenhaIcon, 'As senhas não coincidem');
        return false;
    }
    clearError(confirmPasswordInput, confirmSenhaError, confirmSenhaIcon);
    return true;
}

function validateTermos() {
    if (!termosCheckbox.checked) {
        alert('Você deve aceitar os termos e condições para continuar');
        return false;
    }
    return true;
}

// Validação em tempo real
nomeInput.addEventListener('blur', () => validateNome(nomeInput.value));
nomeInput.addEventListener('input', () => {
    if (nomeInput.classList.contains('is-invalid')) {
        validateNome(nomeInput.value);
    }
});

emailInput.addEventListener('blur', () => validateEmail(emailInput.value));
emailInput.addEventListener('input', () => {
    if (emailInput.classList.contains('is-invalid')) {
        validateEmail(emailInput.value);
    }
});

passwordInput.addEventListener('blur', () => validatePassword(passwordInput.value));
passwordInput.addEventListener('input', () => {
    if (passwordInput.classList.contains('is-invalid')) {
        validatePassword(passwordInput.value);
    }
});

confirmPasswordInput.addEventListener('blur', () => {
    validateConfirmPassword(passwordInput.value, confirmPasswordInput.value);
});
confirmPasswordInput.addEventListener('input', () => {
    if (confirmPasswordInput.classList.contains('is-invalid')) {
        validateConfirmPassword(passwordInput.value, confirmPasswordInput.value);
    }
});

// Submissão do formulário com Firebase
registerForm.addEventListener('submit', async function(e) {
    e.preventDefault();

    const nome = nomeInput.value.trim();
    const email = emailInput.value.trim();
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;

    // Validar todos os campos
    const isNomeValid = validateNome(nome);
    const isEmailValid = validateEmail(email);
    const isPasswordValid = validatePassword(password);
    const isConfirmPasswordValid = validateConfirmPassword(password, confirmPassword);
    const isTermosValid = validateTermos();

    if (!isNomeValid || !isEmailValid || !isPasswordValid || !isConfirmPasswordValid || !isTermosValid) {
        return;
    }

    // Desabilitar botão durante o processo
    registerBtn.disabled = true;
    registerBtn.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span>Cadastrando...';

    try {
        // Criar usuário no Firebase
        const userCredential = await createUserWithEmailAndPassword(auth, email, password);
        
        // Atualizar perfil com o nome
        await updateProfile(userCredential.user, {
            displayName: nome
        });

        // Mostrar mensagem de sucesso
        alert('Cadastro realizado com sucesso!\n\nBem-vindo, ' + nome + '!\n\nRedirecionando para o dashboard...');
        
        // Redirecionar para a página principal ou dashboard
        window.location.href = 'dashboard.html';

    } catch (error) {
        console.error('Erro no cadastro:', error);
        
        // Tratamento de erros do Firebase
        let errorMessage = 'Erro ao criar conta. Tente novamente.';
        
        switch (error.code) {
            case 'auth/email-already-in-use':
                errorMessage = 'Este email já está cadastrado.\n\nFaça login ou use outro email.';
                showError(emailInput, emailError, emailIcon, 'Email já cadastrado');
                break;
            case 'auth/invalid-email':
                errorMessage = 'Email inválido.\n\nVerifique o formato do email.';
                showError(emailInput, emailError, emailIcon, 'Email inválido');
                break;
            case 'auth/weak-password':
                errorMessage = 'Senha muito fraca.\n\nUse pelo menos 6 caracteres.';
                showError(passwordInput, senhaError, senhaIcon, 'Senha muito fraca');
                break;
            case 'auth/operation-not-allowed':
                errorMessage = 'Cadastro por email/senha não está habilitado.\n\nEntre em contato com o suporte.';
                break;
            case 'auth/network-request-failed':
                errorMessage = 'Erro de conexão.\n\nVerifique sua internet e tente novamente.';
                break;
            default:
                errorMessage = 'Erro ao criar conta:\n\n' + error.message;
        }
        
        alert(errorMessage);
    } finally {
        // Reabilitar botão
        registerBtn.disabled = false;
        registerBtn.innerHTML = 'Cadastrar';
    }
});

// Login com Google
document.getElementById('google').addEventListener('click', async function () {
    try {
        const result = await signInWithPopup(auth, googleProvider);
        alert('Login com Google realizado com sucesso!\n\nBem-vindo, ' + result.user.displayName);
        window.location.href = 'dashboard.html';
    } catch (error) {
        console.error('Erro no login com Google:', error);
        
        let errorMessage = 'Erro ao fazer login com Google';
        
        if (error.code === 'auth/popup-closed-by-user') {
            errorMessage = 'Login cancelado pelo usuário';
        } else if (error.code === 'auth/popup-blocked') {
            errorMessage = 'Pop-up bloqueado. Permita pop-ups e tente novamente.';
        } else if (error.code === 'auth/account-exists-with-different-credential') {
            errorMessage = 'Já existe uma conta com este email.\n\nFaça login usando o método original.';
        }
        
        alert(errorMessage);
    }
});

// Apple Login (Requer configuração adicional no Firebase)
document.getElementById('apple').addEventListener('click', function () {
    alert('Login com Apple\n\nFuncionalidade requer configuração adicional no Firebase Console.\n\nPara implementar:\n1. Acesse Firebase Console\n2. Vá em Authentication > Sign-in method\n3. Ative o provedor Apple\n4. Configure Service ID e Team ID');
});

// Facebook Login (Requer configuração adicional no Firebase)
document.getElementById('facebook').addEventListener('click', function () {
    alert('Login com Facebook\n\nFuncionalidade requer configuração adicional no Firebase Console.\n\nPara implementar:\n1. Acesse Firebase Console\n2. Vá em Authentication > Sign-in method\n3. Ative o provedor Facebook\n4. Configure App ID e App Secret do Facebook');
});

// Verificar se já está logado ao carregar a página
auth.onAuthStateChanged((user) => {
    if (user) {
        // Se já estiver logado, redirecionar para dashboard
        console.log('Usuário já está logado, redirecionando...');
        window.location.href = 'dashboard.html';
    }
});