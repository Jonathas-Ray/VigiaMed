package org.example.facades;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.example.applications.MedicaoListaApplication;
import org.example.entities.MedicaoLista;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MedicaoListaFacadeTest {

    @Mock
    private MedicaoListaApplication application;

    @InjectMocks
    private MedicaoListaFacade facade;

    @Test
    void deveBuscarTodos() {
        List<MedicaoLista> lista = Arrays.asList(new MedicaoLista(), new MedicaoLista());
        when(application.buscarTodos()).thenReturn(lista);

        List<MedicaoLista> resultado = facade.buscarTodos();

        assertEquals(2, resultado.size());
        verify(application, times(1)).buscarTodos();
    }

    @Test
    void deveAdicionarERetornarObjeto() {
        MedicaoLista medicao = new MedicaoLista();
        MedicaoLista resultado = facade.adicionar(medicao);

        assertEquals(medicao, resultado);
        verify(application, times(1)).adicionar(medicao);
    }

    @Test
    void deveExcluirCorretamente() {
        facade.excluir(1);
        verify(application).excluir(1);
    }
}