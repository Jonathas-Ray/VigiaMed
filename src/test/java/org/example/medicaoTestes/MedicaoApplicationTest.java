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

    /*
     * @Denver Oliveira
     * @30/03/2026
     */

    @Test
    public void testBuscarPorId_QuandoIdNaoExiste_DeveLancarNullPointerException() {
        int idInexistente = 999;
        when(medicaoRepository.buscarPorId(idInexistente)).thenReturn(null);

        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            medicaoApplication.buscarPorId(idInexistente);
        });

        assertThat(exception.getMessage(), containsString("model is null"));
        verify(medicaoRepository).buscarPorId(idInexistente);
    }

    @Test
    public void testBuscarTodos_QuandoListaVazia_DeveRetornarListaVazia() {
        List<MedicaoModel> models = new ArrayList<>();
        when(medicaoRepository.buscarTodos()).thenReturn(models);

        List<Medicao> resultado = medicaoApplication.buscarTodos();

        assertThat(resultado.isEmpty(), is(true));
        verify(medicaoRepository, times(1)).buscarTodos();
    }


    @Test
    public void testBuscarPorId_ComIdZero_DeveChamarRepository() {
        int idZero = 0;
        when(medicaoRepository.buscarPorId(idZero)).thenReturn(null);

        try {
            medicaoApplication.buscarPorId(idZero);
        } catch (NullPointerException e) {
            // Esperado
        }

        verify(medicaoRepository).buscarPorId(idZero);
    }

    @Test
    public void testBuscarPorId_ComIdNegativo_DeveChamarRepository() {
        int idNegativo = -1;
        when(medicaoRepository.buscarPorId(idNegativo)).thenReturn(null);

        try {
            medicaoApplication.buscarPorId(idNegativo);
        } catch (NullPointerException e) {
            // Esperado
        }

        verify(medicaoRepository).buscarPorId(idNegativo);
    }

    @Test
    public void testAdicionar_ComMedicaoNull_DeveLancarNullPointerException() {
        try {
            medicaoApplication.adicionar(null);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
        verify(medicaoRepository, never()).adicionar(any(MedicaoModel.class));
    }

    @Test
    public void testAdicionar_QuandoRepositoryRetornaNull_DeveLancarNullPointerException() {
        Medicao medicao = new Medicao();
        medicao.setDescricao("Teste");

        when(medicaoRepository.adicionar(any(MedicaoModel.class))).thenReturn(null);

        try {
            medicaoApplication.adicionar(medicao);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
    }

    @Test
    public void testAdicionar_ComMedicaoCompleta_DeveConverterCorretamente() {
        Medicao medicao = new Medicao();
        medicao.setDescricao("Coleta de Sinais");
        medicao.setDataHora("2026-03-30 14:00:00");
        medicao.setPacienteId(100);
        medicao.setDispositivoId(50);

        MedicaoModel modelSalvo = new MedicaoModel();
        modelSalvo.setId(1);
        modelSalvo.setDescricao("Coleta de Sinais");
        modelSalvo.setDataHora("2026-03-30 14:00:00");
        modelSalvo.setPacienteId(100);
        modelSalvo.setDispositivoId(50);

        when(medicaoRepository.adicionar(any(MedicaoModel.class))).thenReturn(modelSalvo);

        Medicao resultado = medicaoApplication.adicionar(medicao);

        assertThat(resultado.getId(), is(1));
        assertThat(resultado.getDescricao(), is("Coleta de Sinais"));
        assertThat(resultado.getDataHora(), is("2026-03-30 14:00:00"));
        assertThat(resultado.getPacienteId(), is(100));
        assertThat(resultado.getDispositivoId(), is(50));
        verify(medicaoRepository).adicionar(any(MedicaoModel.class));
    }

    @Test
    public void testAdicionar_ComIdJaExistente_DeveManterId() {
        Medicao medicao = new Medicao();
        medicao.setId(100);
        medicao.setDescricao("Teste");

        MedicaoModel modelSalvo = new MedicaoModel();
        modelSalvo.setId(100);
        modelSalvo.setDescricao("Teste");

        when(medicaoRepository.adicionar(any(MedicaoModel.class))).thenReturn(modelSalvo);

        Medicao resultado = medicaoApplication.adicionar(medicao);

        assertThat(resultado.getId(), is(100));
        verify(medicaoRepository).adicionar(any(MedicaoModel.class));
    }

    @Test
    public void testAtualizar_ComIdInexistente_DeveChamarRepositoryMesmoAssim() {
        int idInexistente = 999;
        Medicao medicao = new Medicao();
        medicao.setDescricao("Descrição Atualizada");

        medicaoApplication.atualizar(idInexistente, medicao);

        verify(medicaoRepository).atualizar(eq(idInexistente), any(MedicaoModel.class));
    }

    @Test
    public void testAtualizar_ComMedicaoNull_DeveLancarNullPointerException() {
        int idTeste = 1;

        try {
            medicaoApplication.atualizar(idTeste, null);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
        verify(medicaoRepository, never()).atualizar(anyInt(), any(MedicaoModel.class));
    }

    @Test
    public void testAtualizar_ComDadosCompletos_DeveConverterCorretamente() {
        int idTeste = 1;
        Medicao medicao = new Medicao();
        medicao.setDescricao("Medição Atualizada");
        medicao.setDataHora("2026-03-30 15:00:00");
        medicao.setPacienteId(200);
        medicao.setDispositivoId(75);

        medicaoApplication.atualizar(idTeste, medicao);

        verify(medicaoRepository).atualizar(eq(idTeste), any(MedicaoModel.class));
    }

    @Test
    public void testExcluir_ComIdInexistente_DeveChamarRepositoryMesmoAssim() {
        int idInexistente = 999;

        medicaoApplication.excluir(idInexistente);

        verify(medicaoRepository).excluir(idInexistente);
    }

    @Test
    public void testExcluir_ComIdZero_DeveChamarRepository() {
        int idZero = 0;

        medicaoApplication.excluir(idZero);

        verify(medicaoRepository).excluir(idZero);
    }

    @Test
    public void testExcluir_ComIdNegativo_DeveChamarRepository() {
        int idNegativo = -5;

        medicaoApplication.excluir(idNegativo);

        verify(medicaoRepository).excluir(idNegativo);
    }

    @Test
    public void testBuscarTodos_QuandoRepositoryRetornaNull_DeveLancarNullPointerException() {
        when(medicaoRepository.buscarTodos()).thenReturn(null);

        try {
            medicaoApplication.buscarTodos();
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
    }

    @Test
    public void testBuscarPorId_ComIdValido_DeveRetornarMedicaoComDadosCorretos() {
        int idTeste = 5;
        MedicaoModel model = new MedicaoModel();
        model.setId(idTeste);
        model.setDescricao("Medição de Pressão");
        model.setDataHora("2026-03-30");
        model.setPacienteId(10);
        model.setDispositivoId(3);

        when(medicaoRepository.buscarPorId(idTeste)).thenReturn(model);

        Medicao resultado = medicaoApplication.buscarPorId(idTeste);

        assertThat(resultado.getId(), is(idTeste));
        assertThat(resultado.getDescricao(), is("Medição de Pressão"));
        assertThat(resultado.getDataHora(), is("2026-03-30"));
        assertThat(resultado.getPacienteId(), is(10));
        assertThat(resultado.getDispositivoId(), is(3));
        verify(medicaoRepository).buscarPorId(idTeste);
    }

    @Test
    public void testAdicionar_ComCamposOpcionaisNulos_DeveFuncionar() {
        Medicao medicao = new Medicao();
        medicao.setDescricao("Teste sem campos opcionais");

        MedicaoModel modelSalvo = new MedicaoModel();
        modelSalvo.setId(1);
        modelSalvo.setDescricao("Teste sem campos opcionais");

        when(medicaoRepository.adicionar(any(MedicaoModel.class))).thenReturn(modelSalvo);

        Medicao resultado = medicaoApplication.adicionar(medicao);

        assertThat(resultado.getId(), is(1));
        assertThat(resultado.getDescricao(), is("Teste sem campos opcionais"));
        assertThat(resultado.getDataHora(), is(nullValue()));
        assertThat(resultado.getPacienteId(), is(0));
        assertThat(resultado.getDispositivoId(), is(0));
    }
}