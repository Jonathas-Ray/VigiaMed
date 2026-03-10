package org.example.dispositivosTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.example.applications.DispositivoApplication;
import org.example.entities.Dispositivo;
import org.example.facades.DispositivoFacade;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre dispositivos, realizadas pela classe.
 *
 * @Heitor Trindade
 * @10/03/2026
 */
public class DispositivoFacadeTest {

    private DispositivoFacade dispositivoFacade;

    @Mock
    private DispositivoApplication dispositivoApplication;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dispositivoFacade = new DispositivoFacade(dispositivoApplication);
    }

    @Test
    public void testBuscarTodosDelegacao() {
        List<Dispositivo> dispositivos = new ArrayList<>();
        dispositivos.add(new Dispositivo());
        when(dispositivoApplication.buscarTodos()).thenReturn(dispositivos);

        List<Dispositivo> resultado = dispositivoFacade.buscarTodos();

        assertThat(resultado, is(dispositivos));
        verify(dispositivoApplication).buscarTodos();
    }

    @Test
    public void testBuscarPorIdDelegacao() {
        int idTeste = 1;
        Dispositivo dispositivo = new Dispositivo();
        when(dispositivoApplication.buscarPorId(idTeste)).thenReturn(dispositivo);

        Dispositivo resultado = dispositivoFacade.buscarPorId(idTeste);

        assertThat(resultado, is(dispositivo));
        verify(dispositivoApplication).buscarPorId(idTeste);
    }

    @Test
    public void testAdicionarDelegacao() {
        Dispositivo dispositivo = new Dispositivo();

        dispositivoFacade.adicionar(dispositivo);

        verify(dispositivoApplication).adicionar(dispositivo);
    }

    @Test
    public void testExcluirDelegacao() {
        int idTeste = 10;

        dispositivoFacade.excluir(idTeste);

        verify(dispositivoApplication).excluir(idTeste);
    }

    @Test
    public void testAtualizarDelegacao() {
        int idTeste = 5;
        Dispositivo dispositivo = new Dispositivo();

        dispositivoFacade.atualizar(idTeste, dispositivo);

        verify(dispositivoApplication).atualizar(idTeste, dispositivo);
    }
}