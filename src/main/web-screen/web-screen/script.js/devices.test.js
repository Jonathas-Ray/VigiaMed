/** @jest-environment jsdom */

// 1. Mock de Navegação Estável (Evita o erro "Cannot redefine property: location")
const mockLocation = new URL('http://localhost/devices.html');
mockLocation.assign = jest.fn();
delete window.location;
window.location = mockLocation;

// 2. Variáveis de Mock (Para usar nos expects)
const mockOnValue = jest.fn();
const mockGetDoc = jest.fn();
const mockSignOut = jest.fn(() => Promise.resolve());

// 3. Mocks dos Módulos do Firebase (Strings exatas da CDN)
jest.mock("https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js", () => ({
    onAuthStateChanged: jest.fn((auth, callback) => callback({ uid: 'user123' })),
    signOut: () => mockSignOut(),
    getAuth: jest.fn()
}), { virtual: true });

jest.mock("https://www.gstatic.com/firebasejs/12.4.0/firebase-firestore.js", () => ({
    doc: jest.fn(),
    getDoc: () => mockGetDoc(),
    getFirestore: jest.fn()
}), { virtual: true });

jest.mock("https://www.gstatic.com/firebasejs/12.4.0/firebase-database.js", () => ({
    ref: jest.fn(),
    onValue: (ref, callback, errorCallback) => mockOnValue(callback, errorCallback),
    getDatabase: jest.fn()
}), { virtual: true });

// Mock do seu config local
jest.mock('../firebase-config.js', () => ({
    auth: {}, db: {}, rtdb: {}
}), { virtual: true });

describe('Módulo de Dispositivos - Testes 11 a 20', () => {
    
    beforeEach(() => {
        jest.clearAllMocks();
        
        // Prepara o HTML necessário
        document.body.innerHTML = `
            <div id="devices-container"></div>
            <span id="userName"></span>
            <button id="btnLogout"></button>
        `;
        
        // Isola e carrega o script
        jest.isolateModules(() => {
            require('./devices.js');
        });
        
        // Dispara o evento que inicia a lógica no seu script
        document.dispatchEvent(new Event('DOMContentLoaded'));
    });

    test('Teste 11: Deve buscar e exibir o nome do usuário do Firestore', async () => {
        mockGetDoc.mockResolvedValueOnce({
            exists: () => true,
            data: () => ({ nome: 'Jonathas Ray' })
        });

        // Aguarda as microtasks do async/await
        await new Promise(resolve => setTimeout(resolve, 0));
        expect(document.getElementById('userName').textContent).toBe('Jonathas Ray');
    });

    test('Teste 12: Deve mostrar mensagem amigável quando não houver dispositivos', () => {
        mockOnValue.mockImplementationOnce((callback) => {
            callback({ exists: () => false });
        });

        const container = document.getElementById('devices-container');
        expect(container.innerHTML).toContain('Nenhum dispositivo encontrado');
    });

    test('Teste 13: Deve renderizar um card de dispositivo com dados do RTDB', () => {
        const fakeData = {
            "ESP32_VIGIAMED": { heartRate: 75, saturation: 99 }
        };

        mockOnValue.mockImplementationOnce((callback) => {
            callback({ exists: () => true, val: () => fakeData });
        });

        const container = document.getElementById('devices-container');
        expect(container.innerHTML).toContain('ESP32_VIGIAMED');
        expect(container.innerHTML).toContain('75');
    });

    test('Teste 14: Deve lidar com a estrutura de dados .value (Firebase aninhado)', () => {
        const nestedData = {
            "Sensor_01": { heartRate: { value: 82 }, spo2: { value: 96 } }
        };
        mockOnValue.mockImplementationOnce((callback) => {
            callback({ exists: () => true, val: () => nestedData });
        });
        expect(document.getElementById('devices-container').innerHTML).toContain('82');
    });

    test('Teste 15: Deve disparar o SignOut do Firebase ao clicar em Logout', () => {
        document.getElementById('btnLogout').click();
        expect(mockSignOut).toHaveBeenCalled();
    });

    test('Teste 16: Deve redirecionar para login.html se o usuário deslogar', () => {
        const { onAuthStateChanged } = require("https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js");
        onAuthStateChanged.mockImplementationOnce((auth, callback) => callback(null));
        
        jest.isolateModules(() => { require('./devices.js'); });
        document.dispatchEvent(new Event('DOMContentLoaded'));
        
        expect(window.location.href).toContain('login.html');
    });

    test('Teste 17: Deve gerar múltiplos cards se houver vários dispositivos no banco', () => {
        const multiData = {
            "D1": { heartRate: 60 },
            "D2": { heartRate: 70 },
            "D3": { heartRate: 80 }
        };
        mockOnValue.mockImplementationOnce((callback) => {
            callback({ exists: () => true, val: () => multiData });
        });
        const cards = document.querySelectorAll('.device-card');
        expect(cards.length).toBe(3);
    });

    test('Teste 18: Deve exibir "--" quando os dados de BPM/SpO2 estiverem ausentes', () => {
        mockOnValue.mockImplementationOnce((callback) => {
            callback({ exists: () => true, val: () => ({ "Dispositivo_Offline": {} }) });
        });
        expect(document.getElementById('devices-container').innerHTML).toContain('--');
    });

    test('Teste 19: O botão monitorar deve apontar para home.html com o ID correto', () => {
        mockOnValue.mockImplementationOnce((callback) => {
            callback({ exists: () => true, val: () => ({ "ESP32_TESTE": { heartRate: 80 } }) });
        });
        const link = document.querySelector('a.btn-monitorar');
        expect(link.getAttribute('href')).toContain('home.html?id=ESP32_TESTE');
    });

    test('Teste 20: Deve exibir mensagem de erro se a conexão com o RTDB falhar', () => {
        mockOnValue.mockImplementationOnce((callback, errorCallback) => {
            errorCallback(new Error("Falha na conexão"));
        });
        expect(document.getElementById('devices-container').innerHTML).toContain('Erro ao conectar');
    });
});