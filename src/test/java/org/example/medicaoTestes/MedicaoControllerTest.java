package org.example.medicaoTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.example.controllers.MedicaoController;
import org.example.entities.Medicao;
import org.example.facades.MedicaoFacade;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

public class MedicaoControllerTest {

    private MedicaoController medicaoController;

    @Mock
    private MedicaoFacade medicaoFacade;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        medicaoController = new MedicaoController(medicaoFacade);
    }

    @Test
    public void testGetMedicoesLista() {
        List<Medicao> lista = new ArrayList<>();
        lista.add(new Medicao());
        when(medicaoFacade.buscarTodos()).thenReturn(lista);

        List<Medicao> resultado = medicaoController.getMedicoes();

        assertThat(resultado, is(lista));
        verify(medicaoFacade).buscarTodos();
    }

    @Test
    public void testGetMedicaoPorIdSucesso() {
        int idTeste = 1;
        Medicao medicao = new Medicao();
        medicao.setId(idTeste);
        when(medicaoFacade.buscarPorId(idTeste)).thenReturn(medicao);

        ResponseEntity<Medicao> resposta = medicaoController.getMedicao(idTeste);

        assertThat(resposta.getStatusCode(), is(HttpStatus.OK));
        assertThat(resposta.getBody().getId(), is(idTeste));
    }

    @Test
    public void testGetMedicaoPorIdNotFound() {
        int idTeste = 999;
        when(medicaoFacade.buscarPorId(idTeste)).thenReturn(null);

        ResponseEntity<Medicao> resposta = medicaoController.getMedicao(idTeste);

        assertThat(resposta.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void testCriarMedicaoRetornaCreated() {
        Medicao medicaoEntrada = new Medicao();
        Medicao medicaoSalva = new Medicao();
        medicaoSalva.setId(100);

        when(medicaoFacade.adicionar(any(Medicao.class))).thenReturn(medicaoSalva);

        ResponseEntity<Medicao> resposta = medicaoController.criarMedicao(medicaoEntrada);

        assertThat(resposta.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(resposta.getBody().getId(), is(100));
        verify(medicaoFacade).adicionar(any(Medicao.class));
    }

    @Test
    public void testAtualizarMedicaoChamaFacade() {
        int idTeste = 1;
        Medicao medicao = new Medicao();

        medicaoController.atualizarMedicao(idTeste, medicao);

        verify(medicaoFacade).atualizar(idTeste, medicao);
    }

    @Test
    public void testRemoverMedicaoChamaFacade() {
        int idTeste = 50;

        medicaoController.removerMedicao(idTeste);

        verify(medicaoFacade).excluir(idTeste);
    }
}