import org.example.repositories.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.example.models.*;

import java.util.Date;
import java.util.List;

public class LocalRepositoryTests {

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

    @Before
    public void setUp() {
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
    }

    @After
    public void tearDown() {
        repoDisp = null; repoLog = null; repoMedLista = null; repoMed = null;
        repoPac = null; repoSen = null; repoStatus = null; repoTab = null;
        repoUni = null; repoUsu = null;
    }

    @Test
    public void testCRUDDispositivo() {
        DispositivoModel model = new DispositivoModel("MAX30102", "SN1", new Date(), 1, null, 1, null, null);

        repoDisp.adicionar(model);
        assertThat(model.getId(), is(1));
        assertThat(repoDisp.buscarTodos().size(), is(1));

        model.setModelo("MAX-CORRIGIDO");
        repoDisp.atualizar(1, model);
        assertThat(repoDisp.buscarPorId(1).getModelo(), is("MAX-CORRIGIDO"));

        repoDisp.excluir(1);
        assertNull(repoDisp.buscarPorId(1));
    }

    @Test
    public void testCRUDUsuario() {
        UsuarioModel model = new UsuarioModel("Ana", "MEDICA", "ana@v.com", "123", 1, null, null);

        repoUsu.adicionar(model);
        assertThat(model.getId(), is(1));

        UsuarioModel buscado = repoUsu.buscarPorId(1);
        assertThat(buscado.getNome(), is("Ana"));

        model.setNome("Ana Silva");
        repoUsu.atualizar(1, model);
        assertThat(repoUsu.buscarPorId(1).getNome(), is("Ana Silva"));
    }

    @Test
    public void testCRUDPaciente() {
        PacienteModel model = new PacienteModel("Joao", "Quarto 1", null);

        repoPac.adicionar(model);
        assertThat(repoPac.buscarPorId(1).getNome(), is("Joao"));

        repoPac.excluir(1);
        assertThat(repoPac.buscarTodos().size(), is(0));
    }

    @Test
    public void testCRUDLog() {
        LogModel model = new LogModel("LOGIN", "Sucesso", new Date(), 1, null, 1, null);

        repoLog.adicionar(model);
        assertThat(repoLog.buscarPorId(1).getAcao(), is("LOGIN"));

        model.setAcao("LOGOUT");
        repoLog.atualizar(1, model);
        assertThat(repoLog.buscarPorId(1).getAcao(), is("LOGOUT"));
    }

    @Test
    public void testCRUDMedicaoELista() {
        MedicaoModel med = new MedicaoModel("Teste", "2026", 1, null, 1, null, null);
        repoMed.adicionar(med);
        assertThat(med.getId(), is(1));

        MedicaoListaModel lista = new MedicaoListaModel(98.5, "SpO2", "14:00", 1, null, 1, null);
        repoMedLista.adicionar(lista);
        assertThat(repoMedLista.buscarPorId(1).getResultado(), is(98.5));
    }

    @Test
    public void testCRUDSensor() {
        SensorModel model = new SensorModel("MAX30105", "BPM", null);

        repoSen.adicionar(model);
        assertThat(repoSen.buscarPorId(1).getNome(), is("MAX30105"));

        repoSen.excluir(1);
        assertNull(repoSen.buscarPorId(1));
    }

    @Test
    public void testCRUDStatusDispositivo() {
        StatusDispositivoModel model = new StatusDispositivoModel("INATIVO");

        repoStatus.adicionar(model);
        assertThat(repoStatus.buscarPorId(1).getEstado(), is("INATIVO"));

        model.setEstado("ATIVO");
        repoStatus.atualizar(1, model);
        assertThat(repoStatus.buscarPorId(1).getEstado(), is("ATIVO"));
    }

    @Test
    public void testCRUDTabelaList() {
        TabelaListModel model = new TabelaListModel("TB_TESTE", null);

        repoTab.adicionar(model);
        assertThat(repoTab.buscarPorId(1).getNome(), is("TB_TESTE"));
    }

    @Test
    public void testCRUDUnidade() {
        UnidadeModel model = new UnidadeModel(0, "Sede", "Rua X", "123", "s@s.com", null, null);

        repoUni.adicionar(model);
        assertThat(model.getId(), is(1));

        model.setNome("Sede Sul");
        repoUni.atualizar(1, model);
        assertThat(repoUni.buscarPorId(1).getNome(), is("Sede Sul"));
    }

    @Test
    public void testGeracaoIdSequencial() {
        repoDisp.adicionar(new DispositivoModel());
        repoDisp.adicionar(new DispositivoModel());
        repoDisp.adicionar(new DispositivoModel());

        List<DispositivoModel> lista = repoDisp.buscarTodos();
        assertThat(lista.get(0).getId(), is(1));
        assertThat(lista.get(1).getId(), is(2));
        assertThat(lista.get(2).getId(), is(3));
    }
}