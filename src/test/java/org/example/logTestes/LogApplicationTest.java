package org.example.logTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
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
}