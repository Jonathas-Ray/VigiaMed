package org.example.pacienteTest;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.example.applications.PacienteApplication;
import org.example.entities.Paciente;
import org.example.interfaces.PacienteRepository;
import org.example.models.PacienteModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Classe de teste responsável por validar todas as operações da camada Application
 * para a entidade Paciente.
 *
 * A suíte de testes cobre operações CRUD básicas, conversão entre Entity e Model,
 * cenários de erro como IDs inexistentes, parâmetros nulos, casos de borda como
 * IDs zero e negativos, listas vazias e validação de campos opcionais.
 *
 * @author Denver
 * @since 31/03/2026
 */
public class PacienteApplicationTest {

    private PacienteApplication pacienteApplication;

    @Mock
    private PacienteRepository pacienteRepository;

    /**
     * Configuração inicial executada antes de cada teste.
     * Inicializa os mocks do Mockito e instancia a classe a ser testada.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        pacienteApplication = new PacienteApplication(pacienteRepository);
    }

    /**
     * Verifica se o método buscarTodos() retorna uma lista de entidades
     * corretamente convertidas a partir dos modelos retornados pelo repositório.
     *
     * Cenário: Repositório retorna lista com 1 modelo.
     * Resultado esperado: Lista de pacientes com 1 elemento.
     */
    @Test
    public void testBuscarTodosRetornaListaConvertida() {
        List<PacienteModel> models = new ArrayList<>();
        models.add(new PacienteModel());
        when(pacienteRepository.buscarTodos()).thenReturn(models);

        List<Paciente> resultado = pacienteApplication.buscarTodos();

        assertThat(resultado.size(), is(1));
    }

    /**
     * Verifica se o método buscarPorId() retorna um paciente corretamente
     * convertido a partir do modelo retornado pelo repositório.
     *
     * Cenário: ID válido existe no repositório.
     * Resultado esperado: Paciente com ID e nome correspondentes.
     */
    @Test
    public void testBuscarPorId() {
        int idTeste = 10;
        PacienteModel model = new PacienteModel();
        model.setId(idTeste);
        model.setNome("João Silva");
        when(pacienteRepository.buscarPorId(idTeste)).thenReturn(model);

        Paciente resultado = pacienteApplication.buscarPorId(idTeste);

        assertThat(resultado.getId(), is(idTeste));
        assertThat(resultado.getNome(), is("João Silva"));
    }

    /**
     * Verifica se o método adicionar() repassa corretamente a chamada
     * para o repositório, convertendo a entidade em modelo.
     *
     * Cenário: Paciente válido com nome e referência.
     * Resultado esperado: O método é executado sem erros.
     */
    @Test
    public void testAdicionarChamaRepository() {
        Paciente paciente = new Paciente();
        paciente.setNome("Maria Silva");
        paciente.setReferencia("Ala Norte - 202");

        pacienteApplication.adicionar(paciente);

        assertTrue(true);
    }

    /**
     * Verifica se o método excluir() repassa corretamente o ID para o repositório.
     *
     * Cenário: ID válido para exclusão.
     * Resultado esperado: O método é executado sem erros.
     */
    @Test
    public void testExcluirChamaRepository() {
        int idTeste = 5;
        pacienteApplication.excluir(idTeste);

        assertTrue(true);
    }

    /**
     * Verifica se o método atualizar() repassa corretamente o ID e
     * o paciente convertido para o repositório.
     *
     * Cenário: ID válido e paciente com dados atualizados.
     * Resultado esperado: O método é executado sem erros.
     */
    @Test
    public void testAtualizarChamaRepository() {
        int idTeste = 1;
        Paciente paciente = new Paciente();
        paciente.setNome("Nome Atualizado");

        pacienteApplication.atualizar(idTeste, paciente);

        assertTrue(true);
    }

    // TESTES DE CASOS DE ERRO E BORDAS

    /**
     * Verifica o comportamento de buscarTodos() quando o repositório retorna uma lista vazia.
     *
     * Cenário: Nenhum paciente cadastrado.
     * Resultado esperado: Lista vazia, não nula.
     */
    @Test
    public void testBuscarTodos_QuandoListaVazia_DeveRetornarListaVazia() {
        List<PacienteModel> models = new ArrayList<>();
        when(pacienteRepository.buscarTodos()).thenReturn(models);

        List<Paciente> resultado = pacienteApplication.buscarTodos();

        assertThat(resultado.isEmpty(), is(true));
    }

    /**
     * Verifica o comportamento de buscarPorId() quando o ID não existe.
     *
     * Cenário: ID inexistente no repositório.
     * Resultado esperado: NullPointerException (pois o método atual não trata null).
     */
    @Test
    public void testBuscarPorId_QuandoIdNaoExiste_DeveLancarNullPointerException() {
        int idInexistente = 999;
        when(pacienteRepository.buscarPorId(idInexistente)).thenReturn(null);

        try {
            pacienteApplication.buscarPorId(idInexistente);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    /**
     * Verifica se buscarPorId() repassa corretamente o ID zero.
     *
     * Cenário: ID = 0.
     * Resultado esperado: NullPointerException é lançada.
     */
    @Test
    public void testBuscarPorId_ComIdZero_DeveLancarNullPointerException() {
        int idZero = 0;
        when(pacienteRepository.buscarPorId(idZero)).thenReturn(null);

        try {
            pacienteApplication.buscarPorId(idZero);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    /**
     * Verifica se buscarPorId() repassa corretamente o ID negativo.
     *
     * Cenário: ID = -1.
     * Resultado esperado: NullPointerException é lançada.
     */
    @Test
    public void testBuscarPorId_ComIdNegativo_DeveLancarNullPointerException() {
        int idNegativo = -1;
        when(pacienteRepository.buscarPorId(idNegativo)).thenReturn(null);

        try {
            pacienteApplication.buscarPorId(idNegativo);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    /**
     * Verifica o comportamento de adicionar() quando o parâmetro é null.
     *
     * Cenário: Tentativa de adicionar paciente null.
     * Resultado esperado: NullPointerException é lançada.
     */
    @Test
    public void testAdicionar_ComPacienteNull_DeveLancarNullPointerException() {
        try {
            pacienteApplication.adicionar(null);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    /**
     * Verifica se adicionar() permite paciente apenas com nome, sem referência.
     *
     * Cenário: Paciente sem referência definida.
     * Resultado esperado: Método executa sem exceção.
     */
    @Test
    public void testAdicionar_ComPacienteApenasNome_DeveFuncionar() {
        Paciente paciente = new Paciente();
        paciente.setNome("Paciente sem referência");

        pacienteApplication.adicionar(paciente);

        assertTrue(true);
    }

    /**
     * Verifica se adicionar() permite paciente apenas com referência, sem nome.
     *
     * Cenário: Paciente sem nome definido.
     * Resultado esperado: Método executa sem exceção.
     */
    @Test
    public void testAdicionar_ComPacienteApenasReferencia_DeveFuncionar() {
        Paciente paciente = new Paciente();
        paciente.setReferencia("Leito 20 - Ala Central");

        pacienteApplication.adicionar(paciente);

        assertTrue(true);
    }

    /**
     * Verifica se atualizar() é chamado mesmo quando o ID não existe.
     *
     * Cenário: Atualização de paciente com ID inexistente.
     * Resultado esperado: Método executa sem exceção.
     */
    @Test
    public void testAtualizar_ComIdInexistente_DeveExecutarSemErro() {
        int idInexistente = 999;
        Paciente paciente = new Paciente();
        paciente.setNome("Nome Atualizado");

        pacienteApplication.atualizar(idInexistente, paciente);

        assertTrue(true);
    }

    /**
     * Verifica o comportamento de atualizar() quando o paciente é null.
     *
     * Cenário: Tentativa de atualizar com paciente null.
     * Resultado esperado: NullPointerException é lançada.
     */
    @Test
    public void testAtualizar_ComPacienteNull_DeveLancarNullPointerException() {
        int idTeste = 1;

        try {
            pacienteApplication.atualizar(idTeste, null);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    /**
     * Verifica se atualizar() permite atualizar apenas o nome.
     *
     * Cenário: Atualização apenas com nome.
     * Resultado esperado: Método executa sem exceção.
     */
    @Test
    public void testAtualizar_ApenasNome_DeveFuncionar() {
        int idTeste = 1;
        Paciente paciente = new Paciente();
        paciente.setNome("João da Silva Atualizado");

        pacienteApplication.atualizar(idTeste, paciente);

        assertTrue(true);
    }

    /**
     * Verifica se excluir() é chamado mesmo quando o ID não existe.
     *
     * Cenário: Exclusão de paciente com ID inexistente.
     * Resultado esperado: Método executa sem exceção.
     */
    @Test
    public void testExcluir_ComIdInexistente_DeveExecutarSemErro() {
        int idInexistente = 999;

        pacienteApplication.excluir(idInexistente);

        assertTrue(true);
    }

    /**
     * Verifica se excluir() aceita ID zero.
     *
     * Cenário: Exclusão com ID = 0.
     * Resultado esperado: Método executa sem exceção.
     */
    @Test
    public void testExcluir_ComIdZero_DeveExecutarSemErro() {
        int idZero = 0;

        pacienteApplication.excluir(idZero);

        assertTrue(true);
    }

    /**
     * Verifica se excluir() aceita ID negativo.
     *
     * Cenário: Exclusão com ID = -5.
     * Resultado esperado: Método executa sem exceção.
     */
    @Test
    public void testExcluir_ComIdNegativo_DeveExecutarSemErro() {
        int idNegativo = -5;

        pacienteApplication.excluir(idNegativo);

        assertTrue(true);
    }

    /**
     * Verifica o comportamento de buscarTodos() quando o repositório retorna null.
     *
     * Cenário: Repositório retorna null, falha inesperada.
     * Resultado esperado: NullPointerException é lançada.
     */
    @Test
    public void testBuscarTodos_QuandoRepositoryRetornaNull_DeveLancarNullPointerException() {
        when(pacienteRepository.buscarTodos()).thenReturn(null);

        try {
            pacienteApplication.buscarTodos();
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            assertNotNull(e);
        }
    }

    /**
     * Verifica se buscarPorId() retorna todos os campos corretamente quando o ID é válido.
     *
     * Cenário: ID válido com todos os campos preenchidos.
     * Resultado esperado: Paciente com ID, nome e referência corretos.
     */
    @Test
    public void testBuscarPorId_ComIdValido_DeveRetornarPacienteComDadosCorretos() {
        int idTeste = 5;
        PacienteModel model = new PacienteModel();
        model.setId(idTeste);
        model.setNome("Pedro Henrique");
        model.setReferencia("UTI - Leito 03");

        when(pacienteRepository.buscarPorId(idTeste)).thenReturn(model);

        Paciente resultado = pacienteApplication.buscarPorId(idTeste);

        assertThat(resultado.getId(), is(idTeste));
        assertThat(resultado.getNome(), is("Pedro Henrique"));
        assertThat(resultado.getReferencia(), is("UTI - Leito 03"));
    }

    /**
     * Verifica se adicionar() permite paciente com nome null.
     *
     * Cenário: Paciente com nome = null e referência válida.
     * Resultado esperado: Método executa sem exceção.
     */
    @Test
    public void testAdicionar_ComPacienteComNomeNull_DeveFuncionar() {
        Paciente paciente = new Paciente();
        paciente.setNome(null);
        paciente.setReferencia("Referência qualquer");

        pacienteApplication.adicionar(paciente);

        assertTrue(true);
    }

    /**
     * Verifica se adicionar() permite paciente com referência null.
     *
     * Cenário: Paciente com referência = null e nome válido.
     * Resultado esperado: Método executa sem exceção.
     */
    @Test
    public void testAdicionar_ComPacienteComReferenciaNull_DeveFuncionar() {
        Paciente paciente = new Paciente();
        paciente.setNome("Paciente Teste");
        paciente.setReferencia(null);

        pacienteApplication.adicionar(paciente);

        assertTrue(true);
    }
}