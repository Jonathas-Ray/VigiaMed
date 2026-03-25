/** @jest-environment jsdom */

// 1. Mock de Navegação (Resolve o erro "Not implemented: navigation")
delete window.location;
window.location = { href: '' };

// 2. Mocks das funções (Usando variáveis externas para o expect funcionar)
const mockCreateUser = jest.fn(() => Promise.resolve({ user: { uid: 'user123' } }));
const mockSignInGoogle = jest.fn(() => Promise.resolve({ user: { uid: 'google123' } }));
const mockSetDoc = jest.fn(() => Promise.resolve());

global.Swal = {
    fire: jest.fn(() => Promise.resolve({ isConfirmed: true }))
};

jest.mock("https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js", () => ({
    createUserWithEmailAndPassword: mockCreateUser,
    signInWithPopup: mockSignInGoogle, // Usando a variável aqui
    getAuth: jest.fn()
}), { virtual: true });

jest.mock("https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js", () => ({
    doc: jest.fn(),
    setDoc: mockSetDoc,
    getFirestore: jest.fn()
}), { virtual: true });

jest.mock('../firebase-config.js', () => ({
    auth: {}, db: {}, googleProvider: {}
}), { virtual: true });

describe('Testes de Cadastro (Register)', () => {
    beforeEach(() => {
        jest.clearAllMocks();
        // Simula o HTML do formulário de registro
        document.body.innerHTML = `
            <form id="registerForm">
                <input id="nome" /> <span id="nomeError"></span>
                <input id="email" /> <span id="emailError"></span>
                <input id="password" /> <span id="senhaError"></span>
                <input id="confirm-password" /> <span id="confirmSenhaError"></span>
                <input type="checkbox" id="termos" />
                <button id="registerBtn" type="submit">Cadastrar</button>
                <button id="google">Google</button>
            </form>
        `;

        jest.isolateModules(() => {
            require('./register.js');
        });
    });

    // TESTE 3: Validação de Nome Curto
    test('Deve mostrar erro se o nome tiver menos de 3 caracteres', () => {
        const nomeInput = document.getElementById('nome');
        nomeInput.value = 'Ab';
        nomeInput.dispatchEvent(new Event('input')); // Dispara o evento de digitação

        const nomeError = document.getElementById('nomeError');
        expect(nomeError.textContent).toBe('Nome muito curto');
    });

    // TESTE 4: Validação de Email Inválido
    test('Deve mostrar erro para formato de email inválido', () => {
        const emailInput = document.getElementById('email');
        emailInput.value = 'email-invalido';
        emailInput.dispatchEvent(new Event('input'));

        const emailError = document.getElementById('emailError');
        expect(emailError.textContent).toBe('Email inválido');
    });

    // TESTE 5: Senhas Diferentes
    test('Deve mostrar erro se as senhas não coincidirem', () => {
        document.getElementById('password').value = '12345678';
        const confirmInput = document.getElementById('confirm-password');
        confirmInput.value = '87654321';
        confirmInput.dispatchEvent(new Event('input'));

        const confirmError = document.getElementById('confirmSenhaError');
        expect(confirmError.textContent).toBe('As senhas não coincidem');
    });

    // TESTE 6: Termos não aceitos
    test('Deve mostrar alerta se os termos não forem aceitos no envio', () => {
        document.getElementById('nome').value = 'Usuario Teste';
        document.getElementById('email').value = 'teste@email.com';
        document.getElementById('password').value = '12345678';
        document.getElementById('confirm-password').value = '12345678';
        document.getElementById('termos').checked = false;

        document.getElementById('registerForm').dispatchEvent(new Event('submit'));

        expect(global.Swal.fire).toHaveBeenCalledWith(expect.objectContaining({
            title: 'Termos e Condições'
        }));
    });

    // TESTE 7: Cadastro com Sucesso (Firebase Auth)
    test('Deve chamar createUserWithEmailAndPassword com dados corretos', () => {
        document.getElementById('nome').value = 'Usuario Teste';
        document.getElementById('email').value = 'valido@email.com';
        document.getElementById('password').value = '12345678';
        document.getElementById('confirm-password').value = '12345678';
        document.getElementById('termos').checked = true;

        document.getElementById('registerForm').dispatchEvent(new Event('submit'));

        expect(mockCreateUser).toHaveBeenCalledWith(expect.anything(), 'valido@email.com', '12345678');
    });

    // TESTE 8: Persistência no Firestore
    test('Deve salvar os dados do usuário no Firestore após criar conta', async () => {
        // Preenche tudo corretamente
        document.getElementById('nome').value = 'Jonathas';
        document.getElementById('email').value = 'jon@teste.com';
        document.getElementById('password').value = '12345678';
        document.getElementById('confirm-password').value = '12345678';
        document.getElementById('termos').checked = true;

        document.getElementById('registerForm').dispatchEvent(new Event('submit'));

        // Aguarda as promessas do Firebase
        await Promise.resolve();

        expect(mockSetDoc).toHaveBeenCalled();
    });

    // TESTE 9: Login com Google no Registro
    test('Deve iniciar login com Google ao clicar no botão correspondente', () => {
        document.getElementById('google').click();
        expect(mockSignInGoogle).toHaveBeenCalled(); // Agora usamos a variável correta
    });

    // TESTE 10: Limpeza de Erros
    test('Deve limpar a mensagem de erro quando o input for corrigido', () => {
        const nomeInput = document.getElementById('nome');
        const nomeError = document.getElementById('nomeError');

        // Coloca erro
        nomeInput.value = 'A';
        nomeInput.dispatchEvent(new Event('input'));

        // Corrige
        nomeInput.value = 'Nome Valido';
        nomeInput.dispatchEvent(new Event('input'));

        expect(nomeError.textContent).toBe('');
    });
});