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
}