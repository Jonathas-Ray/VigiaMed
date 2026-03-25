import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import org.example.models.*;

public class ModelTests {

    private Date data;
    private DispositivoModel m1; private LogModel m2; private MedicaoModel m3;
    private MedicaoListaModel m4; private PacienteModel m5; private SensorModel m6;
    private StatusDispositivoModel m7; private TabelaListModel m8;
    private UnidadeModel m9; private UsuarioModel m10;

    @Before
    public void setUp() {
        data = new Date();
    }

    @After
    public void tearDown() {
        data = null; m1 = null; m2 = null; m3 = null; m4 = null;
        m5 = null; m6 = null; m7 = null; m8 = null; m9 = null; m10 = null;
    }

    @Test
    public void testDispositivoModel() {
        m1 = new DispositivoModel("MAX30102", "SN123", data, 1, null, 2, null, new ArrayList<>());
        m1.setId(10);
        assertThat(m1.getId(), is(10));
        assertThat(m1.getModelo(), is("MAX30102"));
        assertThat(m1.getNumeroSerie(), is("SN123"));
        assertThat(m1.getDataAquisicao(), is(data));
        assertThat(m1.getUnidadeId(), is(1));
        assertThat(m1.getStatusDispositivoId(), is(2));
    }

    @Test
    public void testLogModel() {
        m2 = new LogModel("UPDATE", "Desc", data, 1, null, 2, null);
        m2.setId(100);
        assertThat(m2.getId(), is(100));
        assertThat(m2.getAcao(), is("UPDATE"));
        assertThat(m2.getDescricao(), is("Desc"));
        assertThat(m2.getData(), is(data));
        assertThat(m2.getTabelaListId(), is(1));
        assertThat(m2.getUsuarioId(), is(2));
    }

    @Test
    public void testMedicaoModel() {
        m3 = new MedicaoModel("Medicao A", "2026-03-10", 1, null, 2, null, new ArrayList<>());
        m3.setId(300);
        assertThat(m3.getId(), is(300));
        assertThat(m3.getDescricao(), is("Medicao A"));
        assertThat(m3.getDataHora(), is("2026-03-10"));
        assertThat(m3.getPacienteId(), is(1));
        assertThat(m3.getDispositivoId(), is(2));
    }

    @Test
    public void testMedicaoListaModel() {
        m4 = new MedicaoListaModel(98.5, "SpO2", "14:00", 1, null, 5, null);
        m4.setId(1);
        assertThat(m4.getId(), is(1));
        assertThat(m4.getResultado(), is(98.5));
        assertThat(m4.getTipoMedicao(), is("SpO2"));
        assertThat(m4.getData_hora(), is("14:00"));
        assertThat(m4.getMedicaoId(), is(1));
        assertThat(m4.getSensorId(), is(5));
    }

    @Test
    public void testPacienteModel() {
        m5 = new PacienteModel("Maria Silva", "Quarto 10", new ArrayList<>());
        m5.setId(50);
        assertThat(m5.getId(), is(50));
        assertThat(m5.getNome(), is("Maria Silva"));
        assertThat(m5.getReferencia(), is("Quarto 10"));
    }

    @Test
    public void testSensorModel() {
        m6 = new SensorModel("Sensor O2", "BPM", new ArrayList<>());
        m6.setId(5);
        assertThat(m6.getId(), is(5));
        assertThat(m6.getNome(), is("Sensor O2"));
        assertThat(m6.getUnidadeMedida(), is("BPM"));
    }

    @Test
    public void testStatusDispositivoModel() {
        m7 = new StatusDispositivoModel("OPERACIONAL");
        m7.setId(2);
        assertThat(m7.getId(), is(2));
        assertThat(m7.getEstado(), is("OPERACIONAL"));
    }

    @Test
    public void testTabelaListModel() {
        m8 = new TabelaListModel("TB_PACIENTES", new ArrayList<>());
        m8.setId(20);
        assertThat(m8.getId(), is(20));
        assertThat(m8.getNome(), is("TB_PACIENTES"));
    }

    @Test
    public void testUnidadeModel() {
        m9 = new UnidadeModel(1, "Unidade Central", "Rua X", "123", "u@u.com", new ArrayList<>(), new ArrayList<>());
        assertThat(m9.getId(), is(1));
        assertThat(m9.getNome(), is("Unidade Central"));
        assertThat(m9.getEndereco(), is("Rua X"));
        assertThat(m9.getTelefone(), is("123"));
        assertThat(m9.getEmail(), is("u@u.com"));
    }

    @Test
    public void testUsuarioModel() {
        m10 = new UsuarioModel("Admin", "SISTEMA", "a@v.com", "pw", 1, null, new ArrayList<>());
        m10.setId(5);
        assertThat(m10.getId(), is(5));
        assertThat(m10.getNome(), is("Admin"));
        assertThat(m10.getTipo(), is("SISTEMA"));
        assertThat(m10.getEmail(), is("a@v.com"));
        assertThat(m10.getSenha(), is("pw"));
        assertThat(m10.getUnidadeId(), is(1));
    }
}