/** @jest-environment jsdom */

// 1. Mocks de Navegação e Ações
const mockAssign = jest.fn();
const mockSignOut = jest.fn(() => Promise.resolve()); // <-- DEFINIDO AQUI
delete window.location;
window.location = { href: '', assign: mockAssign };

// 2. Variáveis para controlar os Mocks do banco
let firebaseCallback;
let firebaseErrorCallback;

// 3. Mocks do Firebase
jest.mock("https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js", () => ({
    onAuthStateChanged: jest.fn((auth, callback) => callback({ uid: 'user123' })),
    signOut: mockSignOut, // <-- USANDO A VARIÁVEL AQUI
    getAuth: jest.fn()
}), { virtual: true });

jest.mock("https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js", () => ({
    doc: jest.fn(),
    getDoc: jest.fn(() => Promise.resolve({ exists: () => true, data: () => ({ nome: 'Usuario Teste' }) })),
    getFirestore: jest.fn()
}), { virtual: true });

jest.mock("https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js", () => ({
    ref: jest.fn(),
    onValue: jest.fn((ref, callback, error) => {
        firebaseCallback = callback;
        firebaseErrorCallback = error;
    }),
    getDatabase: jest.fn()
}), { virtual: true });

jest.mock('../firebase-config.js', () => ({ auth: {}, db: {}, rtdb: {} }), { virtual: true });

describe('Módulo de Dispositivos - Testes 1 a 10', () => {
    beforeEach(() => {
        jest.clearAllMocks();
        mockSignOut.mockClear(); // Garante que o contador de chamadas comece em zero
        
        document.body.innerHTML = `
            <div id="devices-container"></div>
            <span id="userName"></span>
            <button id="btnLogout"></button>
            <button id="btnAddDevice"></button>
            <button id="btnSaveDevice"></button>
            <div id="addDeviceModal"></div>
        `;
        global.bootstrap = { Modal: jest.fn(() => ({ show: jest.fn() })) };
        
        jest.isolateModules(() => { require('./devices.js'); });
        document.dispatchEvent(new Event('DOMContentLoaded'));
    });

    test('1: Exibir nome do usuário', async () => {
        await new Promise(process.nextTick);
        expect(document.getElementById('userName').textContent).toBe('Usuario Teste');
    });

    test('2: Nenhum dispositivo encontrado', () => {
        firebaseCallback({ exists: () => false });
        expect(document.getElementById('devices-container').innerHTML).toContain('Nenhum dispositivo');
    });

    test('3: Criar card com dados', () => {
        firebaseCallback({ 
            exists: () => true, 
            val: () => ({ "DEV1": { heartRate: 80, saturation: 95 } }) 
        });
        expect(document.getElementById('devices-container').innerHTML).toContain('DEV1');
    });

    test('4: Dados aninhados (.value)', () => {
        firebaseCallback({ 
            exists: () => true, 
            val: () => ({ "DEV2": { heartRate: { value: 72 } } }) 
        });
        expect(document.getElementById('devices-container').innerHTML).toContain('72');
    });

    test('5: Logout chamado', () => {
        // Clica no botão de logout
        document.getElementById('btnLogout').click();
        
        // Agora o mockSignOut será reconhecido corretamente
        expect(mockSignOut).toHaveBeenCalled();
    });

    test('6: Redireciona se user null', () => {
        const { onAuthStateChanged } = require("https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js");
        
        onAuthStateChanged.mockImplementationOnce((auth, callback) => callback(null));
        
        jest.isolateModules(() => { require('./devices.js'); });
        document.dispatchEvent(new Event('DOMContentLoaded'));
        
        expect(mockAssign).toHaveBeenCalled();
    });

    test('7: Múltiplos dispositivos', () => {
        firebaseCallback({ 
            exists: () => true, 
            val: () => ({ "D1": { heartRate: 60 }, "D2": { heartRate: 70 } }) 
        });
        expect(document.querySelectorAll('.device-card').length).toBe(2);
    });

    test('8: Dados vazios mostram "--"', () => {
        firebaseCallback({ exists: () => true, val: () => ({ "S1": {} }) });
        expect(document.getElementById('devices-container').innerHTML).toContain('--');
    });

    test('9: Link contém ID correto', () => {
        firebaseCallback({ exists: () => true, val: () => ({ "DEV_X": { heartRate: 80 } }) });
        const link = document.querySelector('a.btn-monitorar');
        expect(link.getAttribute('href')).toContain('DEV_X');
    });

    test('10: Erro Firebase', () => {
        firebaseErrorCallback(new Error());
        expect(document.getElementById('devices-container').innerHTML).toContain('Erro');
    });
});