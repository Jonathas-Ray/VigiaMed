package org.example.medicaoTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.example.applications.MedicaoApplication;
import org.example.entities.Medicao;
import org.example.facades.MedicaoFacade;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MedicaoFacadeTest {

    private MedicaoFacade medicaoFacade;

    @Mock
    private MedicaoApplication medicaoApplication;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        medicaoFacade = new MedicaoFacade(medicaoApplication);
    }

    @Test
    public void testBuscarTodosDelegacao() {
        List<Medicao> lista = new ArrayList<>();
        lista.add(new Medicao());
        when(medicaoApplication.buscarTodos()).thenReturn(lista);

        List<Medicao> resultado = medicaoFacade.buscarTodos();

        assertThat(resultado, is(lista));
        verify(medicaoApplication).buscarTodos();
    }

    @Test
    public void testBuscarPorIdDelegacao() {
        int idTeste = 1;
        Medicao medicao = new Medicao();
        when(medicaoApplication.buscarPorId(idTeste)).thenReturn(medicao);

        Medicao resultado = medicaoFacade.buscarPorId(idTeste);

        assertThat(resultado, is(medicao));
        verify(medicaoApplication).buscarPorId(idTeste);
    }

    @Test
    public void testAdicionarDelegacao() {
        Medicao entrada = new Medicao();
        Medicao salva = new Medicao();
        salva.setId(1);
        when(medicaoApplication.adicionar(entrada)).thenReturn(salva);

        Medicao resultado = medicaoFacade.adicionar(entrada);

        assertThat(resultado.getId(), is(1));
        verify(medicaoApplication).adicionar(entrada);
    }

    @Test
    public void testExcluirDelegacao() {
        int idTeste = 10;
        medicaoFacade.excluir(idTeste);
        verify(medicaoApplication).excluir(idTeste);
    }

    @Test
    public void testAtualizarDelegacao() {
        int idTeste = 5;
        Medicao medicao = new Medicao();
        medicaoFacade.atualizar(idTeste, medicao);
        verify(medicaoApplication).atualizar(idTeste, medicao);
    }
}