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
}