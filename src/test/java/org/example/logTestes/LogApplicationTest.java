package org.example.logTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.example.applications.LogApplication;
import org.example.entities.Log;
import org.example.interfaces.LogRepository;
import org.example.models.LogModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre os logs, realizadas pela classe.
 *
 * @Heitor Trindade
 * @10/03/2026
 */
public class LogApplicationTest {

    private LogApplication logApplication;

    @Mock
    private LogRepository logRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        logApplication = new LogApplication(logRepository);
    }

    @Test
    public void testBuscarTodosRetornaListaConvertida() {
        List<LogModel> models = new ArrayList<>();
        models.add(new LogModel());
        when(logRepository.buscarTodos()).thenReturn(models);

        List<Log> resultado = logApplication.buscarTodos();

        assertThat(resultado.size(), is(1));
        verify(logRepository).buscarTodos();
    }


    @Test
    public void testAdicionarChamaRepository() {
        Log log = new Log();
        log.setAcao("INSERT");

        logApplication.adicionar(log);

        verify(logRepository).adicionar(any(LogModel.class));
    }

    @Test
    public void testExcluirChamaRepository() {
        int idTeste = 10;

        logApplication.excluir(idTeste);

        verify(logRepository).excluir(idTeste);
    }

    @Test
    public void testAtualizarChamaRepository() {
        int idTeste = 5;
        Log log = new Log();
        log.setAcao("UPDATE");

        logApplication.atualizar(idTeste, log);

        verify(logRepository).atualizar(eq(idTeste), any(LogModel.class));
    }

    /* @Denver
     * @30/03/2026
     */

    @Test
    public void testBuscarPorId_DeveRetornarLogConvertido() {
        int idTeste = 1;
        LogModel model = new LogModel();
        model.setId(idTeste);
        model.setAcao("LOGIN");
        when(logRepository.buscarPorId(idTeste)).thenReturn(model);

        Log resultado = logApplication.buscarPorId(idTeste);

        assertThat(resultado.getId(), is(idTeste));
        assertThat(resultado.getAcao(), is("LOGIN"));
        verify(logRepository).buscarPorId(idTeste);
    }

    @Test
    public void testBuscarTodos_QuandoListaVazia_DeveRetornarListaVazia() {
        List<LogModel> models = new ArrayList<>();
        when(logRepository.buscarTodos()).thenReturn(models);

        List<Log> resultado = logApplication.buscarTodos();

        assertThat(resultado.isEmpty(), is(true));
        verify(logRepository, times(1)).buscarTodos();
    }

    @Test
    public void testBuscarPorId_QuandoIdNaoExiste_DeveLancarNullPointerException() {
        int idInexistente = 999;
        when(logRepository.buscarPorId(idInexistente)).thenReturn(null);

        try {
            logApplication.buscarPorId(idInexistente);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is(nullValue()));
        }
        verify(logRepository).buscarPorId(idInexistente);
    }

    @Test
    public void testBuscarPorId_ComIdZero_DeveChamarRepository() {
        int idZero = 0;
        when(logRepository.buscarPorId(idZero)).thenReturn(null);

        try {
            logApplication.buscarPorId(idZero);
        } catch (NullPointerException e) {
            // Esperado
        }

        verify(logRepository).buscarPorId(idZero);
    }

    @Test
    public void testBuscarPorId_ComIdNegativo_DeveChamarRepository() {
        int idNegativo = -1;
        when(logRepository.buscarPorId(idNegativo)).thenReturn(null);

        try {
            logApplication.buscarPorId(idNegativo);
        } catch (NullPointerException e) {
            // Esperado
        }

        verify(logRepository).buscarPorId(idNegativo);
    }

    @Test
    public void testAdicionar_ComLogNull_DeveLancarNullPointerException() {
        try {
            logApplication.adicionar(null);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
        verify(logRepository, never()).adicionar(any(LogModel.class));
    }

    @Test
    public void testAdicionar_ComLogCompleto_DeveConverterCorretamente() {
        Log log = new Log();
        log.setAcao("DELETE");
        log.setDescricao("Remoção de registro");
        log.setData(new Date());
        log.setTabelaListId(10);
        log.setUsuarioId(5);

        logApplication.adicionar(log);

        verify(logRepository, times(1)).adicionar(any(LogModel.class));
    }

    @Test
    public void testAtualizar_ComIdInexistente_DeveChamarRepositoryMesmoAssim() {
        int idInexistente = 999;
        Log log = new Log();
        log.setAcao("UPDATE");
        log.setDescricao("Atualização de registro");

        logApplication.atualizar(idInexistente, log);

        verify(logRepository).atualizar(eq(idInexistente), any(LogModel.class));
    }

    @Test
    public void testAtualizar_ComLogNull_DeveLancarNullPointerException() {
        int idTeste = 1;

        try {
            logApplication.atualizar(idTeste, null);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
        verify(logRepository, never()).atualizar(anyInt(), any(LogModel.class));
    }

    @Test
    public void testExcluir_ComIdInexistente_DeveChamarRepositoryMesmoAssim() {
        int idInexistente = 999;

        logApplication.excluir(idInexistente);

        verify(logRepository).excluir(idInexistente);
    }

    @Test
    public void testExcluir_ComIdZero_DeveChamarRepository() {
        int idZero = 0;

        logApplication.excluir(idZero);

        verify(logRepository).excluir(idZero);
    }

    @Test
    public void testExcluir_ComIdNegativo_DeveChamarRepository() {
        int idNegativo = -5;

        logApplication.excluir(idNegativo);

        verify(logRepository).excluir(idNegativo);
    }

    @Test
    public void testBuscarTodos_QuandoRepositoryRetornaNull_DeveLancarNullPointerException() {
        when(logRepository.buscarTodos()).thenReturn(null);

        try {
            logApplication.buscarTodos();
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
    }
}