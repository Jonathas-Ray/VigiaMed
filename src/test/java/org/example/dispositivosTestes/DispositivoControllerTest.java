package org.example.dispositivosTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.example.controllers.DispositivoController;
import org.example.entities.Dispositivo;
import org.example.facades.DispositivoFacade;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre dispositivos, realizadas pela classe.
 *
 * @Heitor Trindade
 * @10/03/2026
 */
public class DispositivoControllerTest {

    private DispositivoController dispositivoController;

    @Mock
    private DispositivoFacade dispositivoFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dispositivoController = new DispositivoController(dispositivoFacade);
    }

    @Test
    public void testGetDispositivoLista() {
        List<Dispositivo> lista = new ArrayList<>();
        lista.add(new Dispositivo());
        when(dispositivoFacade.buscarTodos()).thenReturn(lista);

        List<Dispositivo> resultado = dispositivoController.getDispositivo();

        assertThat(resultado, is(lista));
        verify(dispositivoFacade).buscarTodos();
    }

    @Test
    public void testGetDispositivoPorIdSucesso() {
        int idTeste = 1;
        Dispositivo dispositivo = new Dispositivo();
        when(dispositivoFacade.buscarPorId(idTeste)).thenReturn(dispositivo);

        ResponseEntity<Dispositivo> resposta = dispositivoController.GetDispositivo(idTeste);

        assertThat(resposta.getStatusCode(), is(HttpStatus.OK));
        assertThat(resposta.getBody(), is(dispositivo));
    }

    @Test
    public void testGetDispositivoPorIdNotFound() {
        int idTeste = 99;
        when(dispositivoFacade.buscarPorId(idTeste)).thenReturn(null);

        ResponseEntity<Dispositivo> resposta = dispositivoController.GetDispositivo(idTeste);

        assertThat(resposta.getStatusCode(), is(HttpStatus.NOT_FOUND));
        assertNull(resposta.getBody());
    }

    @Test
    public void testCriarDispositivo() {
        Dispositivo dispositivo = new Dispositivo();

        dispositivoController.criarDispositivo(dispositivo);

        verify(dispositivoFacade).adicionar(dispositivo);
    }

    @Test
    public void testRemoverDispositivo() {
        int idTeste = 10;

        dispositivoController.removerDispositivo(idTeste);

        verify(dispositivoFacade).excluir(idTeste);
    }

    @Test
    public void testAtualizarDispositivo() {
        int idTeste = 5;
        Dispositivo dispositivo = new Dispositivo();

        dispositivoController.atualizarDispositivo(idTeste, dispositivo);

        verify(dispositivoFacade).atualizar(idTeste, dispositivo);
    }
}