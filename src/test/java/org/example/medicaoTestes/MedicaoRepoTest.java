package org.example.medicaoTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;
import org.example.models.MedicaoModel;
import org.example.repositories.MedicaoRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

public class MedicaoRepoTest {

    private MedicaoRepositoryImpl repository;

    @Before
    public void setUp() {
        repository = new MedicaoRepositoryImpl();
    }

    @Test
    public void testAdicionarGeraIdAutomatico() {
        MedicaoModel model = new MedicaoModel();
        model.setId(0);

        MedicaoModel salvo = repository.adicionar(model);

        assertThat(salvo.getId(), is(1));
        assertThat(repository.buscarTodos().size(), is(1));
    }

    @Test
    public void testBuscarTodos() {
        repository.adicionar(new MedicaoModel());
        repository.adicionar(new MedicaoModel());

        List<MedicaoModel> resultado = repository.buscarTodos();

        assertThat(resultado.size(), is(2));
    }

    @Test
    public void testBuscarPorIdExistente() {
        MedicaoModel model = new MedicaoModel();
        model.setId(5);
        repository.adicionar(model);

        MedicaoModel resultado = repository.buscarPorId(5);

        assertNotNull(resultado);
        assertThat(resultado.getId(), is(5));
    }

    @Test
    public void testBuscarPorIdInexistente() {
        MedicaoModel resultado = repository.buscarPorId(999);
        assertNull(resultado);
    }

    @Test
    public void testExcluirMedicao() {
        MedicaoModel model = new MedicaoModel();
        model.setId(1);
        repository.adicionar(model);

        repository.excluir(1);

        assertThat(repository.buscarTodos().size(), is(0));
    }

    @Test
    public void testAtualizarDataHora() {
        MedicaoModel original = new MedicaoModel();
        original.setId(1);
        original.setDataHora("10:00");
        repository.adicionar(original);

        MedicaoModel novosDados = new MedicaoModel();
        novosDados.setDataHora("11:00");

        repository.atualizar(1, novosDados);

        MedicaoModel resultado = repository.buscarPorId(1);
        assertThat(resultado.getDataHora(), is("11:00"));
    }

    @Test
    public void testAtualizarInexistenteNaoAlteraNada() {
        MedicaoModel novosDados = new MedicaoModel();
        novosDados.setDataHora("12:00");

        repository.atualizar(99, novosDados);

        assertThat(repository.buscarTodos().size(), is(0));
    }
}