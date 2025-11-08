// Importar Firebase
import { initializeApp } from 'https://www.gstatic.com/firebasejs/10.7.1/firebase-app.js';
import { 
    getAuth, 
    signInWithEmailAndPassword,
    GoogleAuthProvider,
    signInWithPopup,
    sendPasswordResetEmail,
    setPersistence,
    browserLocalPersistence,
    browserSessionPersistence
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

// Submissão do formulário com Firebase
loginForm.addEventListener('submit', async function(e) {
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
        alert('Erro de validação:\n\n' + errorMessages.join('\n'));
        return;
    }

    // Processar login com Firebase
    loginBtn.disabled = true;
    loginBtn.textContent = 'Entrando...';

    try {
        // Verificar se deve lembrar o usuário
        const rememberMe = document.getElementById('rememberMe')?.checked || false;
        
        // Configurar persistência da sessão
        await setPersistence(auth, rememberMe ? browserLocalPersistence : browserSessionPersistence);
        
        // Fazer login no Firebase
        const userCredential = await signInWithEmailAndPassword(auth, emailInput.value, passwordInput.value);
        
        // Login bem-sucedido
        alert('Login realizado com sucesso!\n\nBem-vindo, ' + (userCredential.user.displayName || userCredential.user.email));
        
        // Redirecionar para o dashboard
        window.location.href = 'dashboard.html';

    } catch (error) {
        console.error('Erro no login:', error);
        
        // Tratamento de erros do Firebase
        let errorMessage = 'Erro ao fazer login. Tente novamente.';
        
        switch (error.code) {
            case 'auth/user-not-found':
                errorMessage = 'Usuário não encontrado.\n\nVerifique seu email ou cadastre-se.';
                showError(emailInput, emailError, 'Usuário não encontrado');
                break;
            case 'auth/wrong-password':
                errorMessage = 'Senha incorreta.\n\nTente novamente ou recupere sua senha.';
                showError(passwordInput, passwordError, 'Senha incorreta');
                break;
            case 'auth/invalid-email':
                errorMessage = 'Email inválido.\n\nVerifique o formato do email.';
                showError(emailInput, emailError, 'Email inválido');
                break;
            case 'auth/user-disabled':
                errorMessage = 'Esta conta foi desativada.\n\nEntre em contato com o suporte.';
                break;
            case 'auth/invalid-credential':
                errorMessage = 'Email ou senha inválidos.\n\nVerifique suas credenciais.';
                showError(emailInput, emailError, 'Credenciais inválidas');
                showError(passwordInput, passwordError, 'Credenciais inválidas');
                break;
            case 'auth/too-many-requests':
                errorMessage = 'Muitas tentativas de login.\n\nAguarde alguns minutos e tente novamente.';
                break;
            case 'auth/network-request-failed':
                errorMessage = 'Erro de conexão.\n\nVerifique sua internet e tente novamente.';
                break;
            default:
                errorMessage = 'Erro ao fazer login:\n\n' + error.message;
        }
        
        alert(errorMessage);
    } finally {
        // Reabilitar botão
        loginBtn.disabled = false;
        loginBtn.textContent = 'Entrar';
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

// Recuperar senha (se houver link/botão para isso)
const forgotPasswordLink = document.getElementById('forgotPassword');
if (forgotPasswordLink) {
    forgotPasswordLink.addEventListener('click', async function(e) {
        e.preventDefault();
        
        const email = emailInput.value.trim();
        
        if (!email) {
            alert('Digite seu email no campo acima para recuperar a senha.');
            emailInput.focus();
            return;
        }
        
        if (!validateEmail(email)) {
            alert('Digite um email válido para recuperar a senha.');
            emailInput.focus();
            return;
        }
        
        try {
            await sendPasswordResetEmail(auth, email);
            alert('Email de recuperação enviado!\n\nVerifique sua caixa de entrada e spam.');
        } catch (error) {
            console.error('Erro ao enviar email:', error);
            
            let errorMessage = 'Erro ao enviar email de recuperação';
            
            switch (error.code) {
                case 'auth/user-not-found':
                    errorMessage = 'Email não encontrado.\n\nVerifique se digitou corretamente.';
                    break;
                case 'auth/invalid-email':
                    errorMessage = 'Email inválido.\n\nVerifique o formato do email.';
                    break;
                case 'auth/too-many-requests':
                    errorMessage = 'Muitas solicitações.\n\nAguarde alguns minutos.';
                    break;
                default:
                    errorMessage = 'Erro: ' + error.message;
            }
            
            alert(errorMessage);
        }
    });
}

// Verificar se já está logado ao carregar a página
auth.onAuthStateChanged((user) => {
    if (user) {
        // Se já estiver logado, redirecionar para dashboard
        console.log('Usuário já está logado, redirecionando...');
        window.location.href = 'dashboard.html';
    }
});