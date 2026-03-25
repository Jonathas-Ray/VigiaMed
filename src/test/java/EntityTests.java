import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.example.entities.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class EntityTests {

    private Date dataTeste;

    private Unidade unidadeSede;
    private Usuario admin;
    private Paciente pacienteJoao;
    private StatusDispositivo statusAtivo;
    private Sensor sensorOximetro;
    private TabelaList tabelaLogs;

    private Dispositivo dispositivoNode;
    private Log registroLog;
    private Medicao medicaoColeta;
    private MedicaoLista itemLista;

    @Before
    public void setUp() {
        dataTeste = new Date();

        unidadeSede = new Unidade();
        unidadeSede.setId(10);
        unidadeSede.setNome("Hospital Central");
        unidadeSede.setEndereco("Rua da Saude, 123");

        admin = new Usuario();
        admin.setId(1);
        admin.setNome("Dr. Augusto");
        admin.setUnidadeId(unidadeSede.getId());
        admin.setEmail("augusto@vigiamed.com");

        pacienteJoao = new Paciente();
        pacienteJoao.setId(100);
        pacienteJoao.setNome("Joao Silva");
        pacienteJoao.setReferencia("Ala A - Leito 5");

        statusAtivo = new StatusDispositivo();
        statusAtivo.setId(5);
        statusAtivo.setEstado("ATIVO");

        sensorOximetro = new Sensor();
        sensorOximetro.setId(50);
        sensorOximetro.setNome("MAX30102");
        sensorOximetro.setUnidadeMedida("BPM/%");

        tabelaLogs = new TabelaList();
        tabelaLogs.setId(200);
        tabelaLogs.setNome("TB_AUDITORIA");
    }

    @After
    public void tearDown() {
        unidadeSede = null; admin = null; pacienteJoao = null;
        statusAtivo = null; sensorOximetro = null; tabelaLogs = null;
        dispositivoNode = null; registroLog = null;
        medicaoColeta = null; itemLista = null;
    }

    @Test
    public void testIntegridadeEntidadesBase() {
        assertThat(unidadeSede.getNome(), is("Hospital Central"));
        assertThat(admin.getUnidadeId(), is(10));
        assertThat(pacienteJoao.getNome(), is("Joao Silva"));
        assertThat(statusAtivo.getEstado(), is("ATIVO"));
        assertThat(sensorOximetro.getNome(), is("MAX30102"));
        assertThat(tabelaLogs.getNome(), is("TB_AUDITORIA"));
    }

    @Test
    public void testCriacaoLogComRelacionamentos() {
        registroLog = new Log();
        registroLog.setId(999);
        registroLog.setAcao("LOGIN");
        registroLog.setUsuarioId(admin.getId());
        registroLog.setTabelaListId(tabelaLogs.getId());
        registroLog.setData(dataTeste);

        assertThat(registroLog.getUsuarioId(), is(1));
        assertThat(registroLog.getTabelaListId(), is(200));
        assertThat(registroLog.getAcao(), is("LOGIN"));
    }

    @Test
    public void testCriacaoDispositivoEUnidade() {
        dispositivoNode = new Dispositivo();
        dispositivoNode.setId(1000);
        dispositivoNode.setModelo("ESP32-Vigia");
        dispositivoNode.setUnidadeId(unidadeSede.getId());
        dispositivoNode.setStatusDispositivoId(statusAtivo.getId());

        assertThat(dispositivoNode.getUnidadeId(), is(10));
        assertThat(dispositivoNode.getStatusDispositivoId(), is(5));
        assertThat(dispositivoNode.getModelo(), is("ESP32-Vigia"));
    }

    @Test
    public void testCriacaoMedicaoEPaciente() {
        medicaoColeta = new Medicao();
        medicaoColeta.setId(500);
        medicaoColeta.setPacienteId(pacienteJoao.getId());
        medicaoColeta.setDescricao("Coleta Sinais Vitais");

        assertThat(medicaoColeta.getPacienteId(), is(100));
        assertThat(medicaoColeta.getDescricao(), is("Coleta Sinais Vitais"));
    }

    @Test
    public void testCriacaoMedicaoListaESensor() {
        itemLista = new MedicaoLista();
        itemLista.setId(1);
        itemLista.setResultado(98.5);
        itemLista.setSensorId(sensorOximetro.getId());
        itemLista.setTipoMedicao("SPO2");

        assertThat(itemLista.getSensorId(), is(50));
        assertThat(itemLista.getResultado(), is(98.5));
        assertThat(itemLista.getTipoMedicao(), is("SPO2"));
    }
}