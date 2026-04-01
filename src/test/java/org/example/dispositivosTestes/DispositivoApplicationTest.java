package org.example.dispositivosTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.example.applications.DispositivoApplication;
import org.example.entities.Dispositivo;
import org.example.interfaces.DispositivoRepository;
import org.example.models.DispositivoModel;
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
public class DispositivoApplicationTest {

    private DispositivoApplication dispositivoApplication;

    @Mock
    private DispositivoRepository dispositivoRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dispositivoApplication = new DispositivoApplication(dispositivoRepository);
    }

    @Test
    public void testBuscarTodosRetornaLista() {
        List<DispositivoModel> models = new ArrayList<>();
        models.add(new DispositivoModel());
        when(dispositivoRepository.buscarTodos()).thenReturn(models);

        List<Dispositivo> resultado = dispositivoApplication.buscarTodos();

        assertThat(resultado.size(), is(1));
        verify(dispositivoRepository).buscarTodos();
    }

    @Test
    public void testBuscarPorId() {
        int idTeste = 1;
        DispositivoModel model = new DispositivoModel();
        model.setId(idTeste);
        when(dispositivoRepository.buscarPorId(idTeste)).thenReturn(model);

        Dispositivo resultado = dispositivoApplication.buscarPorId(idTeste);

        assertThat(resultado.getId(), is(idTeste));
        verify(dispositivoRepository).buscarPorId(idTeste);
    }

    @Test
    public void testAdicionarChamaRepository() {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setModelo("Sensor");

        dispositivoApplication.adicionar(dispositivo);

        verify(dispositivoRepository).adicionar(any(DispositivoModel.class));
    }

    @Test
    public void testExcluirChamaRepository() {
        int idTeste = 10;

        dispositivoApplication.excluir(idTeste);

        verify(dispositivoRepository).excluir(idTeste);
    }

    @Test
    public void testAtualizarChamaRepository() {
        int idTeste = 5;
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setModelo("Novo Modelo");

        dispositivoApplication.atualizar(idTeste, dispositivo);

        verify(dispositivoRepository).atualizar(eq(idTeste), any(DispositivoModel.class));
    }

  /*
   * @Denver Oliveira
   * @30/03/2026
   */

    @Test
    public void testBuscarTodos_QuandoListaVazia_DeveRetornarListaVazia() {
        List<DispositivoModel> models = new ArrayList<>();
        when(dispositivoRepository.buscarTodos()).thenReturn(models);

        List<Dispositivo> resultado = dispositivoApplication.buscarTodos();

        assertThat(resultado.isEmpty(), is(true));
        verify(dispositivoRepository, times(1)).buscarTodos();
    }

    @Test
    public void testBuscarPorId_QuandoIdNaoExiste_DeveLancarNullPointerException() {
        int idInexistente = 999;
        when(dispositivoRepository.buscarPorId(idInexistente)).thenReturn(null);

        try {
            dispositivoApplication.buscarPorId(idInexistente);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is(nullValue()));
        }
        verify(dispositivoRepository).buscarPorId(idInexistente);
    }

    @Test
    public void testBuscarPorId_ComIdZero_DeveChamarRepository() {
        int idZero = 0;
        when(dispositivoRepository.buscarPorId(idZero)).thenReturn(null);

        try {
            dispositivoApplication.buscarPorId(idZero);
        } catch (NullPointerException e) {
            // Esperado
        }

        verify(dispositivoRepository).buscarPorId(idZero);
    }

    @Test
    public void testBuscarPorId_ComIdNegativo_DeveChamarRepository() {
        int idNegativo = -1;
        when(dispositivoRepository.buscarPorId(idNegativo)).thenReturn(null);

        try {
            dispositivoApplication.buscarPorId(idNegativo);
        } catch (NullPointerException e) {
            // Esperado
        }

        verify(dispositivoRepository).buscarPorId(idNegativo);
    }

    @Test
    public void testAdicionar_ComDispositivoNull_DeveLancarNullPointerException() {
        try {
            dispositivoApplication.adicionar(null);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
        verify(dispositivoRepository, never()).adicionar(any(DispositivoModel.class));
    }

    @Test
    public void testAdicionar_ComDispositivoSemId_DeveGerarIdNoRepository() {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setModelo("Novo Dispositivo");
        dispositivo.setNumeroSerie("SN-TEST-001");

        dispositivoApplication.adicionar(dispositivo);

        verify(dispositivoRepository, times(1)).adicionar(any(DispositivoModel.class));
    }

    @Test
    public void testAtualizar_ComIdInexistente_DeveChamarRepositoryMesmoAssim() {
        int idInexistente = 999;
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setModelo("Modelo Atualizado");

        dispositivoApplication.atualizar(idInexistente, dispositivo);

        verify(dispositivoRepository).atualizar(eq(idInexistente), any(DispositivoModel.class));
    }

    @Test
    public void testAtualizar_ComDispositivoNull_DeveLancarNullPointerException() {
        int idTeste = 1;

        try {
            dispositivoApplication.atualizar(idTeste, null);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
        verify(dispositivoRepository, never()).atualizar(anyInt(), any(DispositivoModel.class));
    }

    @Test
    public void testExcluir_ComIdInexistente_DeveChamarRepositoryMesmoAssim() {
        int idInexistente = 999;

        dispositivoApplication.excluir(idInexistente);

        verify(dispositivoRepository).excluir(idInexistente);
    }

    @Test
    public void testExcluir_ComIdZero_DeveChamarRepository() {
        int idZero = 0;

        dispositivoApplication.excluir(idZero);

        verify(dispositivoRepository).excluir(idZero);
    }

    @Test
    public void testExcluir_ComIdNegativo_DeveChamarRepository() {
        int idNegativo = -5;

        dispositivoApplication.excluir(idNegativo);

        verify(dispositivoRepository).excluir(idNegativo);
    }

    @Test
    public void testBuscarTodos_QuandoRepositoryRetornaNull_DeveLancarNullPointerException() {
        when(dispositivoRepository.buscarTodos()).thenReturn(null);

        try {
            dispositivoApplication.buscarTodos();
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
    }
}