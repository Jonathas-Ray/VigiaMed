package org.example.logTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.example.controllers.LogController;
import org.example.entities.Log;
import org.example.facades.LogFacade;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre os logs, realizadas pela classe.
 *
 * @Heitor Trindade
 * @10/03/2026
 */
public class LogControllerTest {

    private LogController logController;

    @Mock
    private LogFacade logFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        logController = new LogController(logFacade);
    }

    @Test
    public void testGetLogsLista() {
        List<Log> lista = new ArrayList<>();
        lista.add(new Log());
        when(logFacade.buscarTodos()).thenReturn(lista);

        List<Log> resultado = logController.getLogs();

        assertThat(resultado, is(lista));
        verify(logFacade).buscarTodos();
    }

    @Test
    public void testGetLogPorIdSucesso() {
        int idTeste = 1;
        Log log = new Log();
        when(logFacade.buscarPorId(idTeste)).thenReturn(log);

        ResponseEntity<Log> resposta = logController.getLog(idTeste);

        assertThat(resposta.getStatusCode(), is(HttpStatus.OK));
        assertThat(resposta.getBody(), is(log));
    }

    @Test
    public void testGetLogPorIdNotFound() {
        int idTeste = 99;
        when(logFacade.buscarPorId(idTeste)).thenReturn(null);

        ResponseEntity<Log> resposta = logController.getLog(idTeste);

        assertThat(resposta.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertNull(resposta.getBody());
    }

    @Test
    public void testCriarLog() {
        Log log = new Log();

        logController.criarLog(log);

        verify(logFacade).adicionar(log);
    }

    @Test
    public void testAtualizarLog() {
        int idTeste = 10;
        Log log = new Log();

        logController.atualizarLog(idTeste, log);

        verify(logFacade).atualizar(idTeste, log);
    }

    @Test
    public void testRemoverLog() {
        int idTeste = 5;

        logController.removerLog(idTeste);

        verify(logFacade).excluir(idTeste);
    }
}