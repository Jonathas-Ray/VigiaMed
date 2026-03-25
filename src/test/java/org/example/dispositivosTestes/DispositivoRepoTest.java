package org.example.dispositivosTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;
import org.example.models.DispositivoModel;
import org.example.repositories.DispositivoRepositoryImpl;
import org.junit.Before;
import org.junit.Test;


/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre dispositivos, realizadas pela classe.
 *
 * @Heitor Trindade
 * @10/03/2026
 */
public class DispositivoRepoTest {

    private DispositivoRepositoryImpl repository;

    @Before
    public void setUp() {
        repository = new DispositivoRepositoryImpl();
    }

    @Test
    public void testAdicionarIncrementaId() {
        DispositivoModel dispositivo = new DispositivoModel();
        dispositivo.setId(0);

        repository.adicionar(dispositivo);

        assertThat(dispositivo.getId(), is(1));
    }

    @Test
    public void testBuscarTodos() {
        repository.adicionar(new DispositivoModel());
        repository.adicionar(new DispositivoModel());

        List<DispositivoModel> resultado = repository.buscarTodos();

        assertThat(resultado.size(), is(2));
    }

    @Test
    public void testBuscarPorIdExistente() {
        DispositivoModel d1 = new DispositivoModel();
        d1.setId(10);
        repository.adicionar(d1);

        DispositivoModel resultado = repository.buscarPorId(10);

        assertNotNull(resultado);
        assertThat(resultado.getId(), is(10));
    }

    @Test
    public void testBuscarPorIdInexistente() {
        DispositivoModel resultado = repository.buscarPorId(99);

        assertNull(resultado);
    }

    @Test
    public void testAtualizarDadosDispositivo() {
        DispositivoModel original = new DispositivoModel();
        original.setId(1);
        original.setModelo("Antigo");
        repository.adicionar(original);

        DispositivoModel novosDados = new DispositivoModel();
        novosDados.setModelo("Novo");

        repository.atualizar(1, novosDados);

        DispositivoModel resultado = repository.buscarPorId(1);
        assertThat(resultado.getModelo(), is("Novo"));
    }

    @Test
    public void testAtualizarInexistenteNaoAlteraNada() {
        DispositivoModel novosDados = new DispositivoModel();
        novosDados.setModelo("Novo");

        repository.atualizar(99, novosDados);

        assertThat(repository.buscarTodos().size(), is(0));
    }
}