import org.example.applications.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.example.entities.*;
import org.example.models.*;
import org.example.repositories.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class ApplicationTests {

    private Date dataGlobal;

    private DispositivoRepositoryImpl repoDisp;
    private LogRepositoryImpl repoLog;
    private MedicaoListaRepositoryImpl repoMedLista;
    private MedicaoRepositoryImpl repoMed;
    private PacienteRepositoryImpl repoPac;
    private SensorRepositoryImpl repoSen;
    private StatusDispositivoImpl repoStatus;
    private TabelaListRepositoryImpl repoTab;
    private UnidadeRepositoryImpl repoUni;
    private UsuarioRepositoryImpl repoUsu;

    private DispositivoApplication appDisp;
    private LogApplication appLog;
    private MedicaoListaApplication appMedLista;
    private MedicaoApplication appMed;
    private PacienteApplication appPac;
    private SensorApplication appSen;
    private StatusDispositivoApplication appStatus;
    private TabelaListApplication appTab;
    private UnidadeApplication appUni;
    private UsuarioApplication appUsu;

    @Before
    public void setUp() {
        dataGlobal = new Date();

        repoDisp = new DispositivoRepositoryImpl();
        repoLog = new LogRepositoryImpl();
        repoMedLista = new MedicaoListaRepositoryImpl();
        repoMed = new MedicaoRepositoryImpl();
        repoPac = new PacienteRepositoryImpl();
        repoSen = new SensorRepositoryImpl();
        repoStatus = new StatusDispositivoImpl();
        repoTab = new TabelaListRepositoryImpl();
        repoUni = new UnidadeRepositoryImpl();
        repoUsu = new UsuarioRepositoryImpl();

        appDisp = new DispositivoApplication(repoDisp);
        appLog = new LogApplication(repoLog);
        appMedLista = new MedicaoListaApplication(repoMedLista);
        appMed = new MedicaoApplication(repoMed);
        appPac = new PacienteApplication(repoPac);
        appSen = new SensorApplication(repoSen);
        appStatus = new StatusDispositivoApplication(repoStatus);
        appTab = new TabelaListApplication(repoTab);
        appUni = new UnidadeApplication(repoUni);
        appUsu = new UsuarioApplication(repoUsu);
    }

    @After
    public void tearDown() {
        dataGlobal = null;
        repoDisp = null; repoLog = null; repoMedLista = null; repoMed = null;
        repoPac = null; repoSen = null; repoStatus = null; repoTab = null;
        repoUni = null; repoUsu = null;
        appDisp = null; appLog = null; appMedLista = null; appMed = null;
        appPac = null; appSen = null; appStatus = null; appTab = null;
        appUni = null; appUsu = null;
    }

    @Test
    public void testDispositivoApplicationFluxo() {
        Dispositivo e = new Dispositivo(0, "MAX30102", "SN123", dataGlobal, 10, null, 1, null, new ArrayList<>());
        appDisp.adicionar(e);

        Dispositivo res = appDisp.buscarPorId(1);
        assertThat(res.getId(), is(1));
        assertThat(res.getModelo(), is("MAX30102"));
        assertThat(res.getNumeroSerie(), is("SN123"));
        assertThat(res.getUnidadeId(), is(10));
        assertThat(res.getStatusDispositivoId(), is(1));
    }

    @Test
    public void testUsuarioApplicationFluxo() {
        Usuario e = new Usuario(0, "Dr. Augusto", "MEDICO", "augusto@vmed.com", "123", 5, null, new ArrayList<>());
        appUsu.adicionar(e);

        Usuario res = appUsu.buscarPorId(1);
        assertThat(res.getNome(), is("Dr. Augusto"));
        assertThat(res.getEmail(), is("augusto@vmed.com"));
        assertThat(res.getTipo(), is("MEDICO"));
        assertThat(res.getUnidadeId(), is(5));
    }

    @Test
    public void testMedicaoListaEValidacaoRealTime() {
        MedicaoLista e = new MedicaoLista(0, 110.5, "BPM", "14:00", 1, null, 50, null);
        appMedLista.adicionar(e);

        MedicaoLista res = appMedLista.buscarPorId(1);
        assertThat(res.getResultado(), is(110.5));
        assertThat(res.getDataHora(), is("14:00"));

        MedicaoListaApplication.ResultadoValidacao validacao = appMedLista.verificarUltimoResultado();
        assertThat(validacao.isAcimaDaNormal(), is(true));
        assertThat(validacao.getMensagem(), containsString("acima do normal"));
    }

    @Test
    public void testPacienteApplicationFluxo() {
        Paciente e = new Paciente(0, "Maria Silva", "Ala Norte - 202", new ArrayList<>());
        appPac.adicionar(e);

        Paciente res = appPac.buscarPorId(1);
        assertThat(res.getNome(), is("Maria Silva"));
        assertThat(res.getReferencia(), is("Ala Norte - 202"));
    }

    @Test
    public void testLogApplicationFluxo() {
        Log e = new Log(0, "DELETE", "Remoção de item", dataGlobal, 20, null, 1, null);
        appLog.adicionar(e);

        Log res = appLog.buscarPorId(1);
        assertThat(res.getAcao(), is("DELETE"));
        assertThat(res.getDescricao(), is("Remoção de item"));
        assertThat(res.getTabelaListId(), is(20));
    }

    @Test
    public void testMedicaoApplicationFluxo() {
        Medicao e = new Medicao(0, "Coleta Diaria", "2026-03-10", 100, null, 10, null, new ArrayList<>());
        Medicao res = appMed.adicionar(e); // Retorna entidade com ID gerado

        assertThat(res.getId(), is(1));
        assertThat(res.getDescricao(), is("Coleta Diaria"));
        assertThat(res.getPacienteId(), is(100));
    }

    @Test
    public void testUnidadeApplicationFluxo() {
        Unidade e = new Unidade(0, "Sede Central", "Rua X, 10", "1234-5678", "adm@sede.com", new ArrayList<>(), new ArrayList<>());
        appUni.adicionar(e);

        Unidade res = appUni.buscarPorId(1);
        assertThat(res.getNome(), is("Sede Central"));
        assertThat(res.getEndereco(), is("Rua X, 10"));
        assertThat(res.getEmail(), is("adm@sede.com"));
    }

    @Test
    public void testTabelaListApplicationFluxo() {
        TabelaList e = new TabelaList(0, "TB_AUDITORIA", new ArrayList<>());
        appTab.adicionar(e);

        TabelaList res = appTab.buscarPorId(1);
        assertThat(res.getNome(), is("TB_AUDITORIA"));
    }

    @Test
    public void testSensorApplicationRobustez() {
        Sensor e = new Sensor(0, "MAX30205", "Celsius", new ArrayList<>());
        appSen.adicionar(e);

        Sensor res = appSen.buscarPorId(1);
        assertThat(res.getNome(), is("MAX30205"));
        assertThat(res.getUnidadeMedida(), is("Celsius"));

        res.setNome("SENSOR-CORRIGIDO");
        appSen.atualizar(1, res);
        assertThat(appSen.buscarPorId(1).getNome(), is("SENSOR-CORRIGIDO"));
    }

    @Test
    public void testStatusDispositivoApplicationRobustez() {
        StatusDispositivo e = new StatusDispositivo(0, "ATIVO");
        appStatus.adicionar(e);

        StatusDispositivo res = appStatus.buscarPorId(1);
        assertThat(res.getEstado(), is("ATIVO"));

        res.setEstado("MANUTENCAO");
        appStatus.atualizar(1, res);
        assertThat(appStatus.buscarPorId(1).getEstado(), is("MANUTENCAO"));

        appStatus.excluir(1);
        assertThat(repoStatus.buscarTodos().size(), is(0));
    }

    /*
     * @Denver Oliveira
     * @30/03/2026
     */

    /**
     * Verifica o comportamento da validação quando o resultado está abaixo do normal.
     *
     * Cenário: Medição com valor 50 bpm.
     * Resultado esperado: Mensagem indicando "abaixo do normal", acimaDaNormal = false,
     * resultado = 50.0.
     */
    @Test
    public void testVerificarUltimoResultado_AbaixoNormal() {
        MedicaoLista e = new MedicaoLista(0, 50.0, "BPM", "14:00", 1, null, 50, null);
        appMedLista.adicionar(e);

        MedicaoListaApplication.ResultadoValidacao validacao = appMedLista.verificarUltimoResultado();

        assertThat(validacao.isAcimaDaNormal(), is(false));
        assertThat(validacao.getMensagem(), containsString("abaixo do normal"));
        assertThat(validacao.getResultado(), is(50.0));
    }

    /**
     * Verifica o comportamento da validação quando o resultado está dentro da faixa normal.
     *
     * Cenário: Medição com valor 80 bpm.
     * Resultado esperado: Mensagem indicando "normais", acimaDaNormal = false,
     * resultado = 80.0.
     */
    @Test
    public void testVerificarUltimoResultado_Normal() {
        MedicaoLista e = new MedicaoLista(0, 80.0, "BPM", "14:00", 1, null, 50, null);
        appMedLista.adicionar(e);

        MedicaoListaApplication.ResultadoValidacao validacao = appMedLista.verificarUltimoResultado();

        assertThat(validacao.isAcimaDaNormal(), is(false));
        assertThat(validacao.getMensagem(), containsString("normais"));
        assertThat(validacao.getResultado(), is(80.0));
    }

    /**
     * Verifica o comportamento da validação no limite inferior da faixa normal.
     *
     * Cenário: Medição com valor 60 bpm (limite inferior).
     * Resultado esperado: Mensagem indicando "normais", acimaDaNormal = false,
     * resultado = 60.0.
     */
    @Test
    public void testVerificarUltimoResultado_LimiteInferior60() {
        MedicaoLista e = new MedicaoLista(0, 60.0, "BPM", "14:00", 1, null, 50, null);
        appMedLista.adicionar(e);

        MedicaoListaApplication.ResultadoValidacao validacao = appMedLista.verificarUltimoResultado();

        assertThat(validacao.isAcimaDaNormal(), is(false));
        assertThat(validacao.getMensagem(), containsString("normais"));
        assertThat(validacao.getResultado(), is(60.0));
    }

    /**
     * Verifica o comportamento da validação no limite superior da faixa normal.
     *
     * Cenário: Medição com valor 100 bpm (limite superior).
     * Resultado esperado: Mensagem indicando "normais", acimaDaNormal = false,
     * resultado = 100.0.
     */
    @Test
    public void testVerificarUltimoResultado_LimiteSuperior100() {
        MedicaoLista e = new MedicaoLista(0, 100.0, "BPM", "14:00", 1, null, 50, null);
        appMedLista.adicionar(e);

        MedicaoListaApplication.ResultadoValidacao validacao = appMedLista.verificarUltimoResultado();

        assertThat(validacao.isAcimaDaNormal(), is(false));
        assertThat(validacao.getMensagem(), containsString("normais"));
        assertThat(validacao.getResultado(), is(100.0));
    }

    /**
     * Verifica o comportamento da validação quando não há medições cadastradas.
     *
     * Cenário: Lista de medições vazia.
     * Resultado esperado: Mensagem "Nenhuma medição encontrada", acimaDaNormal = false,
     * resultado = null.
     */
    @Test
    public void testVerificarUltimoResultado_ListaVazia() {
        MedicaoListaApplication.ResultadoValidacao validacao = appMedLista.verificarUltimoResultado();

        assertThat(validacao.isAcimaDaNormal(), is(false));
        assertThat(validacao.getMensagem(), is("Nenhuma medição encontrada"));
        assertThat(validacao.getResultado(), is(nullValue()));
    }

    // ==================== TESTES PARA ID INEXISTENTE ====================

    /**
     * Verifica o comportamento ao buscar um dispositivo com ID inexistente.
     *
     * Cenário: ID 999 não cadastrado.
     * Resultado esperado: NullPointerException (pois o método atual não trata null).
     */
    @Test(expected = NullPointerException.class)
    public void testDispositivoBuscarPorIdInexistente() {
        appDisp.buscarPorId(999);
    }

    /**
     * Verifica o comportamento ao buscar um log com ID inexistente.
     *
     * Cenário: ID 999 não cadastrado.
     * Resultado esperado: NullPointerException (pois o método atual não trata null).
     */
    @Test(expected = NullPointerException.class)
    public void testLogBuscarPorIdInexistente() {
        appLog.buscarPorId(999);
    }

    /**
     * Verifica o comportamento ao buscar uma medição com ID inexistente.
     *
     * Cenário: ID 999 não cadastrado.
     * Resultado esperado: NullPointerException (pois o método atual não trata null).
     */
    @Test(expected = NullPointerException.class)
    public void testMedicaoBuscarPorIdInexistente() {
        appMed.buscarPorId(999);
    }

    /**
     * Verifica o comportamento ao buscar um item da lista de medições com ID inexistente.
     *
     * Cenário: ID 999 não cadastrado.
     * Resultado esperado: NullPointerException (pois o método atual não trata null).
     */
    @Test(expected = NullPointerException.class)
    public void testMedicaoListaBuscarPorIdInexistente() {
        appMedLista.buscarPorId(999);
    }

    /**
     * Verifica o comportamento ao buscar um paciente com ID inexistente.
     *
     * Cenário: ID 999 não cadastrado.
     * Resultado esperado: NullPointerException (pois o método atual não trata null).
     */
    @Test(expected = NullPointerException.class)
    public void testPacienteBuscarPorIdInexistente() {
        appPac.buscarPorId(999);
    }

    /**
     * Verifica o comportamento ao buscar um sensor com ID inexistente.
     *
     * Cenário: ID 999 não cadastrado.
     * Resultado esperado: NullPointerException (pois o método atual não trata null).
     */
    @Test(expected = NullPointerException.class)
    public void testSensorBuscarPorIdInexistente() {
        appSen.buscarPorId(999);
    }

    /**
     * Verifica o comportamento ao buscar um status de dispositivo com ID inexistente.
     *
     * Cenário: ID 999 não cadastrado.
     * Resultado esperado: NullPointerException (pois o método atual não trata null).
     */
    @Test(expected = NullPointerException.class)
    public void testStatusDispositivoBuscarPorIdInexistente() {
        appStatus.buscarPorId(999);
    }

    /**
     * Verifica o comportamento ao buscar uma tabela com ID inexistente.
     *
     * Cenário: ID 999 não cadastrado.
     * Resultado esperado: NullPointerException (pois o método atual não trata null).
     */
    @Test(expected = NullPointerException.class)
    public void testTabelaListBuscarPorIdInexistente() {
        appTab.buscarPorId(999);
    }

    /**
     * Verifica o comportamento ao buscar uma unidade com ID inexistente.
     *
     * Cenário: ID 999 não cadastrado.
     * Resultado esperado: NullPointerException (pois o método atual não trata null).
     */
    @Test(expected = NullPointerException.class)
    public void testUnidadeBuscarPorIdInexistente() {
        appUni.buscarPorId(999);
    }

    /**
     * Verifica o comportamento ao buscar um usuário com ID inexistente.
     *
     * Cenário: ID 999 não cadastrado.
     * Resultado esperado: NullPointerException (pois o método atual não trata null).
     */
    @Test(expected = NullPointerException.class)
    public void testUsuarioBuscarPorIdInexistente() {
        appUsu.buscarPorId(999);
    }

    // TESTES PARA EXCLUSAO E ATUALIZACAO

    /**
     * Verifica a exclusão de um dispositivo com ID existente.
     *
     * Cenário: Dispositivo criado e depois excluído.
     * Resultado esperado: Ao buscar pelo ID, NullPointerException é lançada.
     */
    @Test
    public void testDispositivoExcluirComIdExistente() {
        Dispositivo e = new Dispositivo(0, "MAX30102", "SN123", dataGlobal, 10, null, 1, null, new ArrayList<>());
        appDisp.adicionar(e);

        appDisp.excluir(1);

        try {
            appDisp.buscarPorId(1);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException ex) {

        }
    }

    /**
     * Verifica a atualização de um dispositivo com ID existente.
     *
     * Cenário: Dispositivo criado e depois atualizado com novos valores.
     * Resultado esperado: Todos os campos são atualizados corretamente.
     */
    @Test
    public void testDispositivoAtualizarComIdExistente() {
        Dispositivo e = new Dispositivo(0, "MAX30102", "SN123", dataGlobal, 10, null, 1, null, new ArrayList<>());
        appDisp.adicionar(e);

        Dispositivo atualizado = new Dispositivo(1, "MAX30105", "SN456", dataGlobal, 20, null, 2, null, new ArrayList<>());
        appDisp.atualizar(1, atualizado);

        Dispositivo res = appDisp.buscarPorId(1);
        assertThat(res.getModelo(), is("MAX30105"));
        assertThat(res.getNumeroSerie(), is("SN456"));
        assertThat(res.getUnidadeId(), is(20));
    }

    /**
     * Verifica a exclusão de um usuário com ID existente.
     *
     * Cenário: Usuário criado e depois excluído.
     * Resultado esperado: Ao buscar pelo ID, NullPointerException é lançada.
     */
    @Test
    public void testUsuarioExcluirComIdExistente() {
        Usuario e = new Usuario(0, "Dr. Augusto", "MEDICO", "augusto@vmed.com", "123", 5, null, new ArrayList<>());
        appUsu.adicionar(e);

        appUsu.excluir(1);

        try {
            appUsu.buscarPorId(1);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException ex) {

        }
    }

    /**
     * Verifica a exclusão de um paciente com ID existente.
     *
     * Cenário: Paciente criado e depois excluído.
     * Resultado esperado: Ao buscar pelo ID, NullPointerException é lançada.
     */
    @Test
    public void testPacienteExcluirComIdExistente() {
        Paciente e = new Paciente(0, "Maria Silva", "Ala Norte - 202", new ArrayList<>());
        appPac.adicionar(e);

        appPac.excluir(1);

        try {
            appPac.buscarPorId(1);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException ex) {

        }
    }

    /**
     * Verifica a exclusão de um log com ID existente.
     *
     * Cenário: Log criado e depois excluído.
     * Resultado esperado: Ao buscar pelo ID, NullPointerException é lançada.
     */
    @Test
    public void testLogExcluirComIdExistente() {
        Log e = new Log(0, "DELETE", "Remoção de item", dataGlobal, 20, null, 1, null);
        appLog.adicionar(e);

        appLog.excluir(1);

        try {
            appLog.buscarPorId(1);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException ex) {
            // Esperado
        }
    }

    /**
     * Verifica a exclusão de uma medição com ID existente.
     *
     * Cenário: Medição criada e depois excluída.
     * Resultado esperado: Ao buscar pelo ID, NullPointerException é lançada.
     */
    @Test
    public void testMedicaoExcluirComIdExistente() {
        Medicao e = new Medicao(0, "Coleta Diaria", "2026-03-10", 100, null, 10, null, new ArrayList<>());
        appMed.adicionar(e);

        appMed.excluir(1);

        try {
            appMed.buscarPorId(1);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException ex) {
            // Esperado
        }
    }

    /**
     * Verifica a exclusão de uma unidade com ID existente.
     *
     * Cenário: Unidade criada e depois excluída.
     * Resultado esperado: Ao buscar pelo ID, NullPointerException é lançada.
     */
    @Test
    public void testUnidadeExcluirComIdExistente() {
        Unidade e = new Unidade(0, "Sede Central", "Rua X, 10", "1234-5678", "adm@sede.com", new ArrayList<>(), new ArrayList<>());
        appUni.adicionar(e);

        appUni.excluir(1);

        try {
            appUni.buscarPorId(1);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException ex) {
            // Esperado
        }
    }

    /**
     * Verifica a exclusão de um sensor com ID existente.
     *
     * Cenário: Sensor criado e depois excluído.
     * Resultado esperado: Ao buscar pelo ID, NullPointerException é lançada.
     */
    @Test
    public void testSensorExcluirComIdExistente() {
        Sensor e = new Sensor(0, "MAX30205", "Celsius", new ArrayList<>());
        appSen.adicionar(e);

        appSen.excluir(1);

        try {
            appSen.buscarPorId(1);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException ex) {
            // Esperado - sucesso
            assertNotNull(ex);
        }
    }

    // TESTES PARA MULTIPLOS REGISTROS

    /**
     * Verifica o cadastro e busca de múltiplos dispositivos.
     *
     * Cenário: Dois dispositivos são adicionados.
     * Resultado esperado: Lista contém 2 elementos e os IDs são gerados sequencialmente.
     */
    @Test
    public void testDispositivoMultiplosRegistros() {
        Dispositivo e1 = new Dispositivo(0, "MAX30102", "SN001", dataGlobal, 10, null, 1, null, new ArrayList<>());
        Dispositivo e2 = new Dispositivo(0, "MAX30105", "SN002", dataGlobal, 20, null, 1, null, new ArrayList<>());

        appDisp.adicionar(e1);
        appDisp.adicionar(e2);

        assertThat(appDisp.buscarTodos().size(), is(2));

        Dispositivo res1 = appDisp.buscarPorId(1);
        Dispositivo res2 = appDisp.buscarPorId(2);

        assertThat(res1.getModelo(), is("MAX30102"));
        assertThat(res2.getModelo(), is("MAX30105"));
    }

    /**
     * Verifica o cadastro e busca de múltiplos usuários.
     *
     * Cenário: Dois usuários são adicionados.
     * Resultado esperado: Lista contém 2 elementos e os IDs são gerados sequencialmente.
     */
    @Test
    public void testUsuarioMultiplosRegistros() {
        Usuario e1 = new Usuario(0, "Dr. Augusto", "MEDICO", "augusto@vmed.com", "123", 5, null, new ArrayList<>());
        Usuario e2 = new Usuario(0, "Enfermeira Ana", "ENFERMEIRA", "ana@vmed.com", "456", 5, null, new ArrayList<>());

        appUsu.adicionar(e1);
        appUsu.adicionar(e2);

        assertThat(appUsu.buscarTodos().size(), is(2));

        Usuario res1 = appUsu.buscarPorId(1);
        Usuario res2 = appUsu.buscarPorId(2);

        assertThat(res1.getNome(), is("Dr. Augusto"));
        assertThat(res2.getNome(), is("Enfermeira Ana"));
    }

    /**
     * Verifica o cadastro e busca de múltiplos pacientes.
     *
     * Cenário: Dois pacientes são adicionados.
     * Resultado esperado: Lista contém 2 elementos e os IDs são gerados sequencialmente.
     */
    @Test
    public void testPacienteMultiplosRegistros() {
        Paciente e1 = new Paciente(0, "Maria Silva", "Ala Norte - 202", new ArrayList<>());
        Paciente e2 = new Paciente(0, "João Santos", "Ala Sul - 105", new ArrayList<>());

        appPac.adicionar(e1);
        appPac.adicionar(e2);

        assertThat(appPac.buscarTodos().size(), is(2));

        Paciente res1 = appPac.buscarPorId(1);
        Paciente res2 = appPac.buscarPorId(2);

        assertThat(res1.getNome(), is("Maria Silva"));
        assertThat(res2.getNome(), is("João Santos"));
    }

    // ==================== TESTE PARA MEDICAOLISTA COM VALORES LIMITE ====================

    /**
     * Verifica a validação de uma medição com valor abaixo do normal.
     *
     * Cenário: Medição com resultado 45 bpm.
     * Resultado esperado: Validação indica "abaixo do normal" e acimaDaNormal = false.
     */
    @Test
    public void testMedicaoListaComResultadoAbaixoDoNormal() {
        MedicaoLista e = new MedicaoLista(0, 45.0, "BPM", "14:00", 1, null, 50, null);
        appMedLista.adicionar(e);

        MedicaoListaApplication.ResultadoValidacao validacao = appMedLista.verificarUltimoResultado();

        assertThat(validacao.isAcimaDaNormal(), is(false));
        assertThat(validacao.getMensagem(), containsString("abaixo do normal"));
    }

    /**
     * Verifica a validação de uma medição com valor acima do normal.
     *
     * Cenário: Medição com resultado 150 bpm.
     * Resultado esperado: Validação indica "acima do normal" e acimaDaNormal = true.
     */
    @Test
    public void testMedicaoListaComResultadoAcimaDoNormal() {
        MedicaoLista e = new MedicaoLista(0, 150.0, "BPM", "14:00", 1, null, 50, null);
        appMedLista.adicionar(e);

        MedicaoListaApplication.ResultadoValidacao validacao = appMedLista.verificarUltimoResultado();

        assertThat(validacao.isAcimaDaNormal(), is(true));
        assertThat(validacao.getMensagem(), containsString("acima do normal"));
    }

    /**
     * Verifica que a validação sempre considera a última medição cadastrada.
     *
     * Cenário: Três medições são adicionadas com valores diferentes.
     * Resultado esperado: A validação retorna os dados da última medição (120 bpm).
     */
    @Test
    public void testMedicaoListaComMultiplasMedicoes_PegaUltima() {
        appMedLista.adicionar(new MedicaoLista(0, 50.0, "BPM", "10:00", 1, null, 50, null));
        appMedLista.adicionar(new MedicaoLista(0, 80.0, "BPM", "11:00", 1, null, 50, null));
        appMedLista.adicionar(new MedicaoLista(0, 120.0, "BPM", "12:00", 1, null, 50, null));

        MedicaoListaApplication.ResultadoValidacao validacao = appMedLista.verificarUltimoResultado();

        assertThat(validacao.getResultado(), is(120.0));
        assertThat(validacao.isAcimaDaNormal(), is(true));
    }
}