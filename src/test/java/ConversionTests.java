import org.example.entities.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.example.models.*;
import java.util.ArrayList;
import java.util.Date;

public class ConversionTests {

    private Date data;
    private Dispositivo e1; private Log e2; private Medicao e3; private MedicaoLista e4;
    private Paciente e5; private Sensor e6; private StatusDispositivo e7;
    private TabelaList e8; private Unidade e9; private Usuario e10;

    @Before
    public void setUp() { data = new Date(); }

    @After
    public void tearDown() {
        data = null; e1 = null; e2 = null; e3 = null; e4 = null;
        e5 = null; e6 = null; e7 = null; e8 = null; e9 = null; e10 = null;
    }

    @Test
    public void testRoundTripDispositivo() {
        e1 = new Dispositivo();
        e1.setId(1); e1.setModelo("ESP32"); e1.setNumeroSerie("SN1");
        e1.setDataAquisicao(data); e1.setUnidadeId(10); e1.setStatusDispositivoId(2);

        Dispositivo result = Dispositivo.fromModel(e1.toModel());
        assertThat(result.getModelo(), is("ESP32"));
        assertThat(result.getUnidadeId(), is(10));
        assertThat(result.getNumeroSerie(), is("SN1"));
    }

    @Test
    public void testRoundTripLog() {
        e2 = new Log(1, "ADD", "Desc", data, 10, null, 1, null);
        Log res = Log.fromModel(e2.toModel());
        assertThat(res.getAcao(), is("ADD"));
        assertThat(res.getDescricao(), is("Desc"));
        assertThat(res.getTabelaListId(), is(10));
    }

    @Test
    public void testRoundTripMedicao() {
        e3 = new Medicao(1, "Desc", "10:00", 5, null, 2, null, new ArrayList<>());
        Medicao res = Medicao.fromModel(e3.toModel());
        assertThat(res.getDescricao(), is("Desc"));
        assertThat(res.getPacienteId(), is(5));
        assertThat(res.getDispositivoId(), is(2));
    }

    @Test
    public void testRoundTripMedicaoLista() {
        e4 = new MedicaoLista(1, 98.0, "SpO2", "11:00", 1, null, 5, null);
        MedicaoLista res = MedicaoLista.fromModel(e4.toModel());
        assertThat(res.getResultado(), is(98.0));
        assertThat(res.getDataHora(), is("11:00"));
        assertThat(res.getSensorId(), is(5));
    }

    @Test
    public void testRoundTripPaciente() {
        e5 = new Paciente(1, "Joao", "Ala A", new ArrayList<>());
        Paciente res = Paciente.fromModel(e5.toModel());
        assertThat(res.getNome(), is("Joao"));
        assertThat(res.getReferencia(), is("Ala A"));
    }

    @Test
    public void testRoundTripSensor() {
        e6 = new Sensor(1, "Sensor1", "%", new ArrayList<>());
        Sensor res = Sensor.fromModel(e6.toModel());
        assertThat(res.getNome(), is("Sensor1"));
        assertThat(res.getUnidadeMedida(), is("%"));
    }

    @Test
    public void testRoundTripStatusDispositivo() {
        e7 = new StatusDispositivo(1, "ATIVO");
        StatusDispositivo res = StatusDispositivo.fromModel(e7.toModel());
        assertThat(res.getEstado(), is("ATIVO"));
        assertThat(res.getId(), is(1));
    }

    @Test
    public void testRoundTripTabelaList() {
        e8 = new TabelaList(1, "TAB", new ArrayList<>());
        TabelaList res = TabelaList.fromModel(e8.toModel());
        assertThat(res.getNome(), is("TAB"));
        assertThat(res.getId(), is(1));
    }

    @Test
    public void testRoundTripUnidade() {
        e9 = new Unidade(1, "Unid", "End", "123", "e@e.com", new ArrayList<>(), new ArrayList<>());
        Unidade res = Unidade.fromModel(e9.toModel());
        assertThat(res.getNome(), is("Unid"));
        assertThat(res.getEmail(), is("e@e.com"));
    }

    @Test
    public void testRoundTripUsuario() {
        e10 = new Usuario(1, "Ana", "MED", "a@a.com", "pw", 1, null, new ArrayList<>());
        Usuario res = Usuario.fromModel(e10.toModel());
        assertThat(res.getNome(), is("Ana"));
        assertThat(res.getEmail(), is("a@a.com"));
        assertThat(res.getUnidadeId(), is(1));
    }
}