package org.example.logTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;
import org.example.models.LogModel;
import org.example.repositories.LogRepositoryImpl;
import org.junit.Before;
import org.junit.Test;


/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre os logs, realizadas pela classe.
 *
 * @Heitor Trindade
 * @10/03/2026
 */
public class LogRepoTest {

    private LogRepositoryImpl repository;

    @Before
    public void setUp() {
        repository = new LogRepositoryImpl();
    }

    @Test
    public void testAdicionarGeraIdAutomatico() {
        LogModel log = new LogModel();
        log.setId(0);

        repository.adicionar(log);

        assertThat(log.getId(), is(1));
    }

    @Test
    public void testBuscarTodosRetornaListaCompleta() {
        repository.adicionar(new LogModel());
        repository.adicionar(new LogModel());

        List<LogModel> resultado = repository.buscarTodos();

        assertThat(resultado.size(), is(2));
    }

    @Test
    public void testBuscarPorIdExistente() {
        LogModel log = new LogModel();
        log.setId(50);
        repository.adicionar(log);

        LogModel resultado = repository.buscarPorId(50);

        assertNotNull(resultado);
        assertThat(resultado.getId(), is(50));
    }

    @Test
    public void testBuscarPorIdInexistente() {
        LogModel resultado = repository.buscarPorId(999);

        assertNull(resultado);
    }

    @Test
    public void testExcluirLog() {
        LogModel log = new LogModel();
        log.setId(1);
        repository.adicionar(log);

        repository.excluir(1);

        assertThat(repository.buscarTodos().size(), is(0));
    }

    @Test
    public void testAtualizarDadosExistentes() {
        LogModel original = new LogModel();
        original.setId(1);
        original.setAcao("OLD");
        repository.adicionar(original);

        LogModel novosDados = new LogModel();
        novosDados.setAcao("NEW");
        novosDados.setDescricao("Updated log");

        repository.atualizar(1, novosDados);

        LogModel resultado = repository.buscarPorId(1);
        assertThat(resultado.getAcao(), is("NEW"));
        assertThat(resultado.getDescricao(), is("Updated log"));
    }

    @Test
    public void testAtualizarInexistenteNaoFazNada() {
        LogModel novosDados = new LogModel();
        novosDados.setAcao("NEW");

        repository.atualizar(99, novosDados);

        assertThat(repository.buscarTodos().size(), is(0));
    }
}