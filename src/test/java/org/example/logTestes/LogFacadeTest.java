package org.example.logTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.example.applications.LogApplication;
import org.example.entities.Log;
import org.example.facades.LogFacade;
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
public class LogFacadeTest {

    private LogFacade logFacade;

    @Mock
    private LogApplication logApplication;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        logFacade = new LogFacade(logApplication);
    }

    @Test
    public void testBuscarTodosDelegacao() {
        List<Log> logs = new ArrayList<>();
        logs.add(new Log());
        when(logApplication.buscarTodos()).thenReturn(logs);

        List<Log> resultado = logFacade.buscarTodos();

        assertThat(resultado, is(logs));
        verify(logApplication).buscarTodos();
    }

    @Test
    public void testBuscarPorIdDelegacao() {
        int idTeste = 1;
        Log log = new Log();
        when(logApplication.buscarPorId(idTeste)).thenReturn(log);

        Log resultado = logFacade.buscarPorId(idTeste);

        assertThat(resultado, is(log));
        verify(logApplication).buscarPorId(idTeste);
    }

    @Test
    public void testAdicionarDelegacao() {
        Log log = new Log();

        logFacade.adicionar(log);

        verify(logApplication).adicionar(log);
    }

    @Test
    public void testExcluirDelegacao() {
        int idTeste = 10;

        logFacade.excluir(idTeste);

        verify(logApplication).excluir(idTeste);
    }

    @Test
    public void testAtualizarDelegacao() {
        int idTeste = 5;
        Log log = new Log();

        logFacade.atualizar(idTeste, log);

        verify(logApplication).atualizar(idTeste, log);
    }
}