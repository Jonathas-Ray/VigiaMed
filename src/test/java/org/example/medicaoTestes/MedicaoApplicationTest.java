package org.example.medicaoTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.example.applications.MedicaoApplication;
import org.example.entities.Medicao;
import org.example.interfaces.MedicaoRepository;
import org.example.models.MedicaoModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MedicaoApplicationTest {

    private MedicaoApplication medicaoApplication;

    @Mock
    private MedicaoRepository medicaoRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        medicaoApplication = new MedicaoApplication(medicaoRepository);
    }

    @Test
    public void testBuscarTodosRetornaListaConvertida() {
        List<MedicaoModel> models = new ArrayList<>();
        models.add(new MedicaoModel());
        when(medicaoRepository.buscarTodos()).thenReturn(models);

        List<Medicao> resultado = medicaoApplication.buscarTodos();

        assertThat(resultado.size(), is(1));
        verify(medicaoRepository).buscarTodos();
    }

    @Test
    public void testBuscarPorId() {
        int idTeste = 10;
        MedicaoModel model = new MedicaoModel();
        model.setId(idTeste);
        when(medicaoRepository.buscarPorId(idTeste)).thenReturn(model);

        Medicao resultado = medicaoApplication.buscarPorId(idTeste);

        assertThat(resultado.getId(), is(idTeste));
        verify(medicaoRepository).buscarPorId(idTeste);
    }

    @Test
    public void testAdicionarRetornaEntidadeComId() {
        Medicao medicaoEntrada = new Medicao();
        medicaoEntrada.setDescricao("Teste");

        MedicaoModel modelSalvo = new MedicaoModel();
        modelSalvo.setId(1); // Simula o ID gerado pelo Repo
        modelSalvo.setDescricao("Teste");

        when(medicaoRepository.adicionar(any(MedicaoModel.class))).thenReturn(modelSalvo);

        Medicao resultado = medicaoApplication.adicionar(medicaoEntrada);

        assertThat(resultado.getId(), is(1));
        assertThat(resultado.getDescricao(), is("Teste"));
        verify(medicaoRepository).adicionar(any(MedicaoModel.class));
    }

    @Test
    public void testExcluirChamaRepository() {
        int idTeste = 5;
        medicaoApplication.excluir(idTeste);
        verify(medicaoRepository).excluir(idTeste);
    }

    @Test
    public void testAtualizarChamaRepository() {
        int idTeste = 1;
        Medicao medicao = new Medicao();
        medicao.setDescricao("Nova Desc");

        medicaoApplication.atualizar(idTeste, medicao);

        verify(medicaoRepository).atualizar(eq(idTeste), any(MedicaoModel.class));
    }
}