import org.example.controllers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.example.applications.*;
import org.example.entities.*;
import org.example.facades.*;
import org.example.repositories.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Date;

public class ControllerTests {

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    private Date dataGlobal;

    private DispositivoRepositoryImpl repo1; private DispositivoApplication app1; private DispositivoFacade fac1; private DispositivoController ctrl1;
    private LogRepositoryImpl repo2; private LogApplication app2; private LogFacade fac2; private LogController ctrl2;
    private MedicaoRepositoryImpl repo3; private MedicaoApplication app3; private MedicaoFacade fac3; private MedicaoController ctrl3;
    private MedicaoListaRepositoryImpl repo4; private MedicaoListaApplication app4; private MedicaoListaFacade fac4; private MedicaoListaController ctrl4;
    private PacienteRepositoryImpl repo5; private PacienteApplication app5; private PacienteFacade fac5; private PacienteController ctrl5;
    private SensorRepositoryImpl repo6; private SensorApplication app6; private SensorFacade fac6; private SensorController ctrl6;
    private StatusDispositivoImpl repo7; private StatusDispositivoApplication app7; private StatusDispositivoFacade fac7; private StatusDispositivoController ctrl7;
    private TabelaListRepositoryImpl repo8; private TabelaListApplication app8; private TabelaListFacade fac8; private TabelaListController ctrl8;
    private UnidadeRepositoryImpl repo9; private UnidadeApplication app9; private UnidadeFacade fac9; private UnidadeController ctrl9;
    private UsuarioRepositoryImpl repo10; private UsuarioApplication app10; private UsuarioFacade fac10; private UsuarioController ctrl10;

    @Before
    public void setUp() {
        dataGlobal = new Date();
        repo1 = new DispositivoRepositoryImpl(); app1 = new DispositivoApplication(repo1); fac1 = new DispositivoFacade(app1); ctrl1 = new DispositivoController(fac1);
        repo2 = new LogRepositoryImpl(); app2 = new LogApplication(repo2); fac2 = new LogFacade(app2); ctrl2 = new LogController(fac2);
        repo3 = new MedicaoRepositoryImpl(); app3 = new MedicaoApplication(repo3); fac3 = new MedicaoFacade(app3); ctrl3 = new MedicaoController(fac3);
        repo4 = new MedicaoListaRepositoryImpl(); app4 = new MedicaoListaApplication(repo4); fac4 = new MedicaoListaFacade(app4); ctrl4 = new MedicaoListaController(fac4, app4);
        repo5 = new PacienteRepositoryImpl(); app5 = new PacienteApplication(repo5); fac5 = new PacienteFacade(app5); ctrl5 = new PacienteController(fac5);
        repo6 = new SensorRepositoryImpl(); app6 = new SensorApplication(repo6); fac6 = new SensorFacade(app6); ctrl6 = new SensorController(fac6);
        repo7 = new StatusDispositivoImpl(); app7 = new StatusDispositivoApplication(repo7); fac7 = new StatusDispositivoFacade(app7); ctrl7 = new StatusDispositivoController(fac7);
        repo8 = new TabelaListRepositoryImpl(); app8 = new TabelaListApplication(repo8); fac8 = new TabelaListFacade(app8); ctrl8 = new TabelaListController(fac8);
        repo9 = new UnidadeRepositoryImpl(); app9 = new UnidadeApplication(repo9); fac9 = new UnidadeFacade(app9); ctrl9 = new UnidadeController(fac9);
        repo10 = new UsuarioRepositoryImpl(); app10 = new UsuarioApplication(repo10); fac10 = new UsuarioFacade(app10); ctrl10 = new UsuarioController(fac10);
    }

    @After
    public void tearDown() {
        repo1 = null; app1 = null; fac1 = null; ctrl1 = null;
        repo2 = null; app2 = null; fac2 = null; ctrl2 = null;
        repo3 = null; app3 = null; fac3 = null; ctrl3 = null;
        repo4 = null; app4 = null; fac4 = null; ctrl4 = null;
        repo5 = null; app5 = null; fac5 = null; ctrl5 = null;
        repo6 = null; app6 = null; fac6 = null; ctrl6 = null;
        repo7 = null; app7 = null; fac7 = null; ctrl7 = null;
        repo8 = null; app8 = null; fac8 = null; ctrl8 = null;
        repo9 = null; app9 = null; fac9 = null; ctrl9 = null;
        repo10 = null; app10 = null; fac10 = null; ctrl10 = null;
        dataGlobal = null; mockMvc = null;
    }

    @Test
    public void testDispositivoControllerExaustivo() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ctrl1).build();
        Dispositivo e = new Dispositivo(1, "ESP32-NODE", "SN-123", dataGlobal, 10, null, 1, null, new ArrayList<>());

        mockMvc.perform(post("/api/dispositivo").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(e))).andExpect(status().isOk());
        mockMvc.perform(get("/api/dispositivo/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.modelo", is("ESP32-NODE")))
                .andExpect(jsonPath("$.numeroSerie", is("SN-123")))
                .andExpect(jsonPath("$.unidadeId", is(10)));
    }

    @Test
    public void testUsuarioControllerExaustivo() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ctrl10).build();
        Usuario e = new Usuario(1, "Dr. Augusto", "MEDICO", "augusto@vmed.com", "senha123", 5, null, new ArrayList<>());

        mockMvc.perform(post("/api/usuario").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(e))).andExpect(status().isOk());
        mockMvc.perform(get("/api/usuario/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Dr. Augusto")))
                .andExpect(jsonPath("$.email", is("augusto@vmed.com")))
                .andExpect(jsonPath("$.tipo", is("MEDICO")))
                .andExpect(jsonPath("$.unidadeId", is(5)));
    }

    @Test
    public void testMedicaoListaEMonitoramento() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ctrl4).build();
        MedicaoLista e = new MedicaoLista(1, 115.0, "BPM", "10:00", 1, null, 5, null);

        mockMvc.perform(post("/api/medicao-lista").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(e))).andExpect(status().isCreated());
        mockMvc.perform(get("/api/medicao-lista/monitorar")).andExpect(status().isOk())
                .andExpect(jsonPath("$.resultado", is(115.0)))
                .andExpect(jsonPath("$.acimaDaNormal", is(true)))
                .andExpect(jsonPath("$.mensagem", containsString("acima do normal")));
    }

    @Test
    public void testPacienteControllerExaustivo() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ctrl5).build();
        Paciente e = new Paciente(1, "Maria das Dores", "Leito 10", new ArrayList<>());

        mockMvc.perform(post("/api/paciente").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(e))).andExpect(status().isOk());
        mockMvc.perform(get("/api/paciente/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Maria das Dores")))
                .andExpect(jsonPath("$.referencia", is("Leito 10")));
    }

    @Test
    public void testUnidadeControllerExaustivo() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ctrl9).build();
        Unidade e = new Unidade(1, "Hosp. Central", "Rua X", "123", "h@h.com", new ArrayList<>(), new ArrayList<>());

        mockMvc.perform(post("/api/unidade").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(e))).andExpect(status().isOk());
        mockMvc.perform(get("/api/unidade/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Hosp. Central")))
                .andExpect(jsonPath("$.email", is("h@h.com")))
                .andExpect(jsonPath("$.telefone", is("123")));
    }

    @Test
    public void testMedicaoControllerExaustivo() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ctrl3).build();
        Medicao e = new Medicao(1, "Triagem", "2026-03-10", 1, null, 1, null, new ArrayList<>());

        mockMvc.perform(post("/api/medicao").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(e))).andExpect(status().isCreated());
        mockMvc.perform(get("/api/medicao/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao", is("Triagem")))
                .andExpect(jsonPath("$.pacienteId", is(1)));
    }

    @Test
    public void testLogControllerExaustivo() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ctrl2).build();
        Log e = new Log(1, "UPDATE", "Alteração de status", dataGlobal, 20, null, 1, null);

        mockMvc.perform(post("/api/log").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(e))).andExpect(status().isOk());
        mockMvc.perform(get("/api/log/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.acao", is("UPDATE")))
                .andExpect(jsonPath("$.tabelaListId", is(20)));
    }

    @Test
    public void testSensorControllerExaustivo() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ctrl6).build();
        Sensor e = new Sensor(1, "MAX30102", "BPM", new ArrayList<>());

        mockMvc.perform(post("/api/sensor").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(e))).andExpect(status().isOk());
        mockMvc.perform(get("/api/sensor/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("MAX30102")))
                .andExpect(jsonPath("$.unidadeMedida", is("BPM")));
    }

    @Test
    public void testStatusControllerExaustivo() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ctrl7).build();
        StatusDispositivo e = new StatusDispositivo(1, "ATIVO");

        mockMvc.perform(post("/api/statusDispositivo").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(e))).andExpect(status().isOk());
        mockMvc.perform(get("/api/statusDispositivo/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.estado", is("ATIVO")));
    }

    @Test
    public void testTabelaControllerExaustivo() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ctrl8).build();
        TabelaList e = new TabelaList(1, "TB_LOGS", new ArrayList<>());

        mockMvc.perform(post("/api/tabelaList").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(e))).andExpect(status().isOk());
        mockMvc.perform(get("/api/tabelaList/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("TB_LOGS")));
    }
}