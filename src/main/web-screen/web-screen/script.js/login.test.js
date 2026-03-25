/** @jest-environment jsdom */

// Criamos as funções mockadas fora para podermos referenciá-las facilmente
const mockSignIn = jest.fn(() => Promise.resolve({ user: { uid: '123' } }));

global.Swal = { 
    fire: jest.fn(() => Promise.resolve({ isConfirmed: true })) 
};

jest.mock("https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js", () => ({
    signInWithEmailAndPassword: mockSignIn, // Usamos a variável aqui
    signInWithPopup: jest.fn(() => Promise.resolve()),
    getAuth: jest.fn()
}), { virtual: true });

jest.mock("https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js", () => ({
    doc: jest.fn(),
    setDoc: jest.fn(() => Promise.resolve()),
    getFirestore: jest.fn()
}), { virtual: true });

jest.mock('../firebase-config.js', () => ({
    auth: {}, db: {}, googleProvider: {}
}), { virtual: true });

describe('Testes de Login', () => {
    beforeEach(() => {
        jest.clearAllMocks();
        document.body.innerHTML = `
            <input id="email" />
            <input id="password" />
            <button type="submit">Login</button>
            <button id="google">Google</button>
        `;
        
        jest.isolateModules(() => {
            require('./login.js');
        });
    });

    test('Deve mostrar alerta de erro se clicar no login com campos vazios', () => {
        const btnLogin = document.querySelector('button[type="submit"]');
        btnLogin.click();

        expect(global.Swal.fire).toHaveBeenCalledWith(
            expect.objectContaining({ icon: 'error', title: 'Oops...' })
        );
    });

    test('Deve chamar o Firebase se campos estiverem preenchidos', () => {
        // 1. Preenche os campos
        document.getElementById('email').value = 'teste@email.com';
        document.getElementById('password').value = '123456';
        
        // 2. Clica no botão
        const btnLogin = document.querySelector('button[type="submit"]');
        btnLogin.click();

        // 3. Verifica se a nossa variável mockSignIn foi chamada
        expect(mockSignIn).toHaveBeenCalled();
        
        // 4. (Opcional) Verifica se foi chamada com os dados corretos
        expect(mockSignIn).toHaveBeenCalledWith(expect.anything(), 'teste@email.com', '123456');
    });
});