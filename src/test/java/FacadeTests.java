import org.example.facades.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.example.applications.*;
import org.example.entities.*;
import org.example.repositories.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class FacadeTests {

    private Date dataGlobal;

    private DispositivoRepositoryImpl repoDisp; private LogRepositoryImpl repoLog;
    private MedicaoListaRepositoryImpl repoMedLista; private MedicaoRepositoryImpl repoMed;
    private PacienteRepositoryImpl repoPac; private SensorRepositoryImpl repoSen;
    private StatusDispositivoImpl repoStatus; private TabelaListRepositoryImpl repoTab;
    private UnidadeRepositoryImpl repoUni; private UsuarioRepositoryImpl repoUsu;

    private DispositivoApplication appDisp; private LogApplication appLog;
    private MedicaoListaApplication appMedLista; private MedicaoApplication appMed;
    private PacienteApplication appPac; private SensorApplication appSen;
    private StatusDispositivoApplication appStatus; private TabelaListApplication appTab;
    private UnidadeApplication appUni; private UsuarioApplication appUsu;

    private DispositivoFacade facDisp; private LogFacade facLog;
    private MedicaoListaFacade facMedLista; private MedicaoFacade facMed;
    private PacienteFacade facPac; private SensorFacade facSen;
    private StatusDispositivoFacade facStatus; private TabelaListFacade facTab;
    private UnidadeFacade facUni; private UsuarioFacade facUsu;

    @Before
    public void setUp() {
        dataGlobal = new Date();
        repoDisp = new DispositivoRepositoryImpl(); repoLog = new LogRepositoryImpl();
        repoMedLista = new MedicaoListaRepositoryImpl(); repoMed = new MedicaoRepositoryImpl();
        repoPac = new PacienteRepositoryImpl(); repoSen = new SensorRepositoryImpl();
        repoStatus = new StatusDispositivoImpl(); repoTab = new TabelaListRepositoryImpl();
        repoUni = new UnidadeRepositoryImpl(); repoUsu = new UsuarioRepositoryImpl();

        appDisp = new DispositivoApplication(repoDisp); appLog = new LogApplication(repoLog);
        appMedLista = new MedicaoListaApplication(repoMedLista); appMed = new MedicaoApplication(repoMed);
        appPac = new PacienteApplication(repoPac); appSen = new SensorApplication(repoSen);
        appStatus = new StatusDispositivoApplication(repoStatus); appTab = new TabelaListApplication(repoTab);
        appUni = new UnidadeApplication(repoUni); appUsu = new UsuarioApplication(repoUsu);

        facDisp = new DispositivoFacade(appDisp); facLog = new LogFacade(appLog);
        facMedLista = new MedicaoListaFacade(appMedLista); facMed = new MedicaoFacade(appMed);
        facPac = new PacienteFacade(appPac); facSen = new SensorFacade(appSen);
        facStatus = new StatusDispositivoFacade(appStatus); facTab = new TabelaListFacade(appTab);
        facUni = new UnidadeFacade(appUni); facUsu = new UsuarioFacade(appUsu);
    }

    @After
    public void tearDown() {
        dataGlobal = null;
        repoDisp = null; repoLog = null; repoMedLista = null; repoMed = null;
        repoPac = null; repoSen = null; repoStatus = null; repoTab = null; repoUni = null; repoUsu = null;
        appDisp = null; appLog = null; appMedLista = null; appMed = null;
        appPac = null; appSen = null; appStatus = null; appTab = null; appUni = null; appUsu = null;
        facDisp = null; facLog = null; facMedLista = null; facMed = null;
        facPac = null; facSen = null; facStatus = null; facTab = null; facUni = null; facUsu = null;
    }

    @Test
    public void testDispositivoFacadeExaustivo() {
        Dispositivo e = new Dispositivo(0, "MAX30102-Node", "SN-998877", dataGlobal, 10, null, 5, null, new ArrayList<>());
        facDisp.adicionar(e);

        Dispositivo res = facDisp.buscarPorId(1);
        assertThat(res.getId(), is(1));
        assertThat(res.getModelo(), is("MAX30102-Node"));
        assertThat(res.getNumeroSerie(), is("SN-998877"));
        assertThat(res.getDataAquisicao(), is(dataGlobal));
        assertThat(res.getUnidadeId(), is(10));
        assertThat(res.getStatusDispositivoId(), is(5));
    }

    @Test
    public void testUsuarioFacadeExaustivo() {
        Usuario e = new Usuario(0, "Dr. Augusto Silva", "ADMIN", "augusto@vigiamed.com", "senhaForte123", 10, null, new ArrayList<>());
        facUsu.adicionar(e);

        Usuario res = facUsu.buscarPorId(1);
        assertThat(res.getNome(), is("Dr. Augusto Silva"));
        assertThat(res.getTipo(), is("ADMIN"));
        assertThat(res.getEmail(), is("augusto@vigiamed.com"));
        assertThat(res.getSenha(), is("senhaForte123"));
        assertThat(res.getUnidadeId(), is(10));
    }

    @Test
    public void testUnidadeFacadeExaustivo() {
        Unidade e = new Unidade(0, "Unidade Central Feira", "Av. Getulio Vargas, 500", "7533221144", "contato@unidade.com", new ArrayList<>(), new ArrayList<>());
        facUni.adicionar(e);

        Unidade res = facUni.buscarPorId(1);
        assertThat(res.getNome(), is("Unidade Central Feira"));
        assertThat(res.getEndereco(), is("Av. Getulio Vargas, 500"));
        assertThat(res.getTelefone(), is("7533221144"));
        assertThat(res.getEmail(), is("contato@unidade.com"));
    }

    @Test
    public void testMedicaoListaFacadeExaustivo() {
        MedicaoLista e = new MedicaoLista(0, 98.6, "SpO2", "2026-03-10 14:00", 1, null, 50, null);
        facMedLista.adicionar(e);

        MedicaoLista res = facMedLista.buscarPorId(1);
        assertThat(res.getResultado(), is(98.6));
        assertThat(res.getTipoMedicao(), is("SpO2"));
        assertThat(res.getDataHora(), is("2026-03-10 14:00"));
        assertThat(res.getMedicaoId(), is(1));
        assertThat(res.getSensorId(), is(50));
    }

    @Test
    public void testLogFacadeExaustivo() {
        Log e = new Log(0, "LOGIN", "Acesso via Web", dataGlobal, 20, null, 1, null);
        facLog.adicionar(e);

        Log res = facLog.buscarPorId(1);
        assertThat(res.getAcao(), is("LOGIN"));
        assertThat(res.getDescricao(), is("Acesso via Web"));
        assertThat(res.getData(), is(dataGlobal));
        assertThat(res.getTabelaListId(), is(20));
        assertThat(res.getUsuarioId(), is(1));
    }

    @Test
    public void testMedicaoFacadeExaustivo() {
        Medicao e = new Medicao(0, "Coleta de Sinais Vitais Matinal", "10/03/2026", 100, null, 1, null, new ArrayList<>());
        Medicao res = facMed.adicionar(e);

        assertThat(res.getId(), is(1));
        assertThat(res.getDescricao(), is("Coleta de Sinais Vitais Matinal"));
        assertThat(res.getDataHora(), is("10/03/2026"));
        assertThat(res.getPacienteId(), is(100));
        assertThat(res.getDispositivoId(), is(1));
    }

    @Test
    public void testPacienteFacadeExaustivo() {
        Paciente e = new Paciente(0, "Maria das Dores", "Ala Norte - Leito 05", new ArrayList<>());
        facPac.adicionar(e);

        Paciente res = facPac.buscarPorId(1);
        assertThat(res.getNome(), is("Maria das Dores"));
        assertThat(res.getReferencia(), is("Ala Norte - Leito 05"));
    }

    @Test
    public void testSensorFacadeExaustivo() {
        Sensor e = new Sensor(0, "MAX30102", "Batimentos/Saturação", new ArrayList<>());
        facSen.adicionar(e);

        Sensor res = facSen.buscarPorId(1);
        assertThat(res.getNome(), is("MAX30102"));
        assertThat(res.getUnidadeMedida(), is("Batimentos/Saturação"));
    }

    @Test
    public void testStatusFacadeExaustivo() {
        StatusDispositivo e = new StatusDispositivo(0, "EM MANUTENCAO");
        facStatus.adicionar(e);

        assertThat(facStatus.buscarPorId(1).getEstado(), is("EM MANUTENCAO"));
    }

    @Test
    public void testTabelaFacadeExaustivo() {
        TabelaList e = new TabelaList(0, "TB_HISTORICO_MEDICO", new ArrayList<>());
        facTab.adicionar(e);

        assertThat(facTab.buscarPorId(1).getNome(), is("TB_HISTORICO_MEDICO"));
    }
}