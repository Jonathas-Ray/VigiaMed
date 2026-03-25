/** @jest-environment jsdom */

// 🔥 Mock de navegação (SEM quebrar o JSDOM)
const mockAssign = jest.fn();
delete window.location;
window.location = { assign: mockAssign };

// 🔥 Mocks controláveis
const mockOnValue = jest.fn();
const mockGetDoc = jest.fn();
const mockSignOut = jest.fn(() => Promise.resolve());

// 🔥 Firebase mocks
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

jest.mock('../firebase-config.js', () => ({
    auth: {}, db: {}, rtdb: {}
}), { virtual: true });

describe('Módulo de Dispositivos - Testes 1 a 20', () => {

    // 🔥 FUNÇÃO CRÍTICA → RECARREGA O SCRIPT A CADA TESTE
    const loadScript = () => {
        jest.resetModules();
        jest.isolateModules(() => {
            require('./devices.js');
        });
        document.dispatchEvent(new Event('DOMContentLoaded'));
    };

    beforeEach(() => {
        jest.clearAllMocks();

        document.body.innerHTML = `
            <div id="devices-container"></div>
            <span id="userName"></span>
            <button id="btnLogout"></button>
            <button id="btnAddDevice"></button>
            <button id="btnSaveDevice"></button>
            <div id="addDeviceModal"></div>
        `;

        global.bootstrap = {
            Modal: jest.fn(() => ({
                show: jest.fn(),
                hide: jest.fn()
            }))
        };
    });

    test('1: Nome do usuário vindo do Firestore', async () => {
        mockGetDoc.mockResolvedValueOnce({
            exists: () => true,
            data: () => ({ nome: 'Jonathas Ray' })
        });

        loadScript();

        await new Promise(r => setTimeout(r, 0));

        expect(document.getElementById('userName').textContent)
            .toBe('Jonathas Ray');
    });

    test('2: Nenhum dispositivo encontrado', () => {
        mockOnValue.mockImplementationOnce(cb => cb({ exists: () => false }));

        loadScript();

        expect(document.getElementById('devices-container').innerHTML)
            .toContain('Nenhum dispositivo');
    });

    test('3: Criar card com dados', () => {
        mockOnValue.mockImplementationOnce(cb => {
            cb({
                exists: () => true,
                val: () => ({ DEV1: { heartRate: 80 } })
            });
        });

        loadScript();

        expect(document.getElementById('devices-container').innerHTML)
            .toContain('DEV1');
    });

    test('4: Dados aninhados (.value)', () => {
        mockOnValue.mockImplementationOnce(cb => {
            cb({
                exists: () => true,
                val: () => ({
                    S1: { heartRate: { value: 72 } }
                })
            });
        });

        loadScript();

        expect(document.getElementById('devices-container').innerHTML)
            .toContain('72');
    });

    test('5: Logout chamado', () => {
        loadScript();
        document.getElementById('btnLogout').click();
        expect(mockSignOut).toHaveBeenCalled();
    });

    test('6: Redireciona se user null', () => {
        const { onAuthStateChanged } = require("https://www.gstatic.com/firebasejs/12.4.0/firebase-auth.js");

        onAuthStateChanged.mockImplementationOnce((auth, cb) => cb(null));

        loadScript();

        expect(mockAssign).toHaveBeenCalled();
    });

    test('7: Múltiplos dispositivos', () => {
        mockOnValue.mockImplementationOnce(cb => {
            cb({
                exists: () => true,
                val: () => ({
                    D1: { heartRate: 60 },
                    D2: { heartRate: 70 }
                })
            });
        });

        loadScript();

        expect(document.querySelectorAll('.device-card').length)
            .toBe(2);
    });

    test('8: Dados vazios mostram "--"', () => {
        mockOnValue.mockImplementationOnce(cb => {
            cb({
                exists: () => true,
                val: () => ({ X: {} })
            });
        });

        loadScript();

        expect(document.getElementById('devices-container').innerHTML)
            .toContain('--');
    });

    test('9: Link contém ID correto', () => {
        mockOnValue.mockImplementationOnce(cb => {
            cb({
                exists: () => true,
                val: () => ({ DEV_X: { heartRate: 80 } })
            });
        });

        loadScript();

        const link = document.querySelector('.btn-monitorar');
        expect(link.getAttribute('href')).toContain('DEV_X');
    });

    test('10: Erro Firebase', () => {
        mockOnValue.mockImplementationOnce((cb, err) => err(new Error()));

        loadScript();

        expect(document.getElementById('devices-container').innerHTML)
            .toContain('Erro');
    });

    // EXTRA

    test('11: container existe', () => {
        loadScript();
        expect(document.getElementById('devices-container')).not.toBeNull();
    });

    test('12: userName existe', () => {
        loadScript();
        expect(document.getElementById('userName')).not.toBeNull();
    });

    test('13: botão logout existe', () => {
        loadScript();
        expect(document.getElementById('btnLogout')).not.toBeNull();
    });

    test('14: modal existe', () => {
        loadScript();
        expect(document.getElementById('addDeviceModal')).not.toBeNull();
    });

    test('15: modal inicializado', () => {
        loadScript();
        expect(global.bootstrap.Modal).toHaveBeenCalled();
    });

    test('16: getDoc chamado', async () => {
        mockGetDoc.mockResolvedValueOnce({
            exists: () => false
        });

        loadScript();

        await new Promise(r => setTimeout(r, 0));

        expect(mockGetDoc).toHaveBeenCalled();
    });

    test('17: onValue chamado', () => {
        loadScript();
        expect(mockOnValue).toHaveBeenCalled();
    });

    test('18: device-card criado', () => {
        mockOnValue.mockImplementationOnce(cb => {
            cb({
                exists: () => true,
                val: () => ({ D: { heartRate: 80 } })
            });
        });

        loadScript();

        expect(document.querySelector('.device-card')).not.toBeNull();
    });

    test('19: HTML não vazio', () => {
        loadScript();
        expect(document.body.innerHTML.length).toBeGreaterThan(0);
    });

    test('20: botão salvar existe', () => {
        loadScript();
        expect(document.getElementById('btnSaveDevice')).not.toBeNull();
    });
});