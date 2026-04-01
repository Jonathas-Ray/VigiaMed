package org.example.medicaoTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.example.applications.MedicaoListaApplication;
import org.example.applications.MedicaoListaApplication.ResultadoValidacao;
import org.example.entities.MedicaoLista;
import org.example.interfaces.MedicaoListaRepository;
import org.example.models.MedicaoListaModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MedicaoListaApplicationTest {

    private MedicaoListaApplication medicaoListaApplication;

    @Mock
    private MedicaoListaRepository medicaoListaRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        medicaoListaApplication = new MedicaoListaApplication(medicaoListaRepository);
    }

    @Test
    public void testBuscarTodosRetornaListaConvertida() {
        List<MedicaoListaModel> models = new ArrayList<>();
        models.add(new MedicaoListaModel());
        when(medicaoListaRepository.buscarTodos()).thenReturn(models);

        List<MedicaoLista> resultado = medicaoListaApplication.buscarTodos();

        assertThat(resultado.size(), is(1));
        verify(medicaoListaRepository).buscarTodos();
    }

    @Test
    public void testBuscarPorId() {
        int idTeste = 10;
        MedicaoListaModel model = new MedicaoListaModel();
        model.setId(idTeste);
        when(medicaoListaRepository.buscarPorId(idTeste)).thenReturn(model);

        MedicaoLista resultado = medicaoListaApplication.buscarPorId(idTeste);

        assertThat(resultado.getId(), is(idTeste));
        verify(medicaoListaRepository).buscarPorId(idTeste);
    }

    @Test
    public void testAdicionarChamaRepository() {
        MedicaoLista medicaoLista = new MedicaoLista();
        medicaoLista.setResultado(80.0);
        medicaoLista.setTipoMedicao("BPM");

        medicaoListaApplication.adicionar(medicaoLista);

        verify(medicaoListaRepository).adicionar(any(MedicaoListaModel.class));
    }

    @Test
    public void testExcluirChamaRepository() {
        int idTeste = 5;
        medicaoListaApplication.excluir(idTeste);
        verify(medicaoListaRepository).excluir(idTeste);
    }

    @Test
    public void testAtualizarChamaRepository() {
        int idTeste = 1;
        MedicaoLista medicaoLista = new MedicaoLista();
        medicaoLista.setResultado(90.0);

        medicaoListaApplication.atualizar(idTeste, medicaoLista);

        verify(medicaoListaRepository).atualizar(eq(idTeste), any(MedicaoListaModel.class));
    }

    // ==================== TESTES PARA VERIFICAR ULTIMO RESULTADO (VALIDACAO) ====================

    @Test
    public void testVerificarUltimoResultado_QuandoResultadoAbaixo60_DeveIndicarAbaixoDoNormal() {
        // Arrange
        List<MedicaoListaModel> models = new ArrayList<>();
        MedicaoListaModel model = new MedicaoListaModel();
        model.setResultado(50.0);
        models.add(model);
        when(medicaoListaRepository.buscarTodos()).thenReturn(models);

        // Act
        ResultadoValidacao resultado = medicaoListaApplication.verificarUltimoResultado();

        // Assert
        assertThat(resultado.isAcimaDaNormal(), is(false));
        assertThat(resultado.getMensagem(), containsString("abaixo do normal"));
        assertThat(resultado.getResultado(), is(50.0));
    }

    @Test
    public void testVerificarUltimoResultado_QuandoResultadoEntre60e100_DeveIndicarNormal() {
        // Arrange
        List<MedicaoListaModel> models = new ArrayList<>();
        MedicaoListaModel model = new MedicaoListaModel();
        model.setResultado(80.0);
        models.add(model);
        when(medicaoListaRepository.buscarTodos()).thenReturn(models);

        // Act
        ResultadoValidacao resultado = medicaoListaApplication.verificarUltimoResultado();

        // Assert
        assertThat(resultado.isAcimaDaNormal(), is(false));
        assertThat(resultado.getMensagem(), containsString("normais"));
        assertThat(resultado.getResultado(), is(80.0));
    }

    @Test
    public void testVerificarUltimoResultado_QuandoResultadoIgual60_DeveIndicarNormal() {
        // Arrange
        List<MedicaoListaModel> models = new ArrayList<>();
        MedicaoListaModel model = new MedicaoListaModel();
        model.setResultado(60.0);
        models.add(model);
        when(medicaoListaRepository.buscarTodos()).thenReturn(models);

        // Act
        ResultadoValidacao resultado = medicaoListaApplication.verificarUltimoResultado();

        // Assert
        assertThat(resultado.isAcimaDaNormal(), is(false));
        assertThat(resultado.getMensagem(), containsString("normais"));
        assertThat(resultado.getResultado(), is(60.0));
    }

    @Test
    public void testVerificarUltimoResultado_QuandoResultadoIgual100_DeveIndicarNormal() {
        // Arrange
        List<MedicaoListaModel> models = new ArrayList<>();
        MedicaoListaModel model = new MedicaoListaModel();
        model.setResultado(100.0);
        models.add(model);
        when(medicaoListaRepository.buscarTodos()).thenReturn(models);

        // Act
        ResultadoValidacao resultado = medicaoListaApplication.verificarUltimoResultado();

        // Assert
        assertThat(resultado.isAcimaDaNormal(), is(false));
        assertThat(resultado.getMensagem(), containsString("normais"));
        assertThat(resultado.getResultado(), is(100.0));
    }

    @Test
    public void testVerificarUltimoResultado_QuandoResultadoAcima100_DeveIndicarAcimaDoNormal() {
        // Arrange
        List<MedicaoListaModel> models = new ArrayList<>();
        MedicaoListaModel model = new MedicaoListaModel();
        model.setResultado(120.0);
        models.add(model);
        when(medicaoListaRepository.buscarTodos()).thenReturn(models);

        // Act
        ResultadoValidacao resultado = medicaoListaApplication.verificarUltimoResultado();

        // Assert
        assertThat(resultado.isAcimaDaNormal(), is(true));
        assertThat(resultado.getMensagem(), containsString("acima do normal"));
        assertThat(resultado.getResultado(), is(120.0));
    }

    @Test
    public void testVerificarUltimoResultado_QuandoListaVazia_DeveRetornarMensagemErro() {
        // Arrange
        List<MedicaoListaModel> models = new ArrayList<>();
        when(medicaoListaRepository.buscarTodos()).thenReturn(models);

        // Act
        ResultadoValidacao resultado = medicaoListaApplication.verificarUltimoResultado();

        // Assert
        assertThat(resultado.isAcimaDaNormal(), is(false));
        assertThat(resultado.getMensagem(), is("Nenhuma medição encontrada"));
        assertThat(resultado.getResultado(), is(nullValue()));
    }

    @Test
    public void testVerificarUltimoResultado_QuandoListaNull_DeveRetornarMensagemErro() {
        // Arrange
        when(medicaoListaRepository.buscarTodos()).thenReturn(null);

        // Act
        ResultadoValidacao resultado = medicaoListaApplication.verificarUltimoResultado();

        // Assert
        assertThat(resultado.isAcimaDaNormal(), is(false));
        assertThat(resultado.getMensagem(), is("Nenhuma medição encontrada"));
        assertThat(resultado.getResultado(), is(nullValue()));
    }

    @Test
    public void testVerificarUltimoResultado_QuandoResultadoNull_DeveRetornarMensagemErro() {
        // Arrange
        List<MedicaoListaModel> models = new ArrayList<>();
        MedicaoListaModel model = new MedicaoListaModel();
        model.setResultado(null); // Simulando resultado null
        models.add(model);
        when(medicaoListaRepository.buscarTodos()).thenReturn(models);

        // Act
        ResultadoValidacao resultado = medicaoListaApplication.verificarUltimoResultado();

        // Assert
        assertThat(resultado.isAcimaDaNormal(), is(false));
        assertThat(resultado.getMensagem(), is("Resultado não disponível"));
        assertThat(resultado.getResultado(), is(nullValue()));
    }

    // ==================== TESTES ADICIONAIS ====================

    @Test
    public void testBuscarTodos_QuandoListaVazia_DeveRetornarListaVazia() {
        List<MedicaoListaModel> models = new ArrayList<>();
        when(medicaoListaRepository.buscarTodos()).thenReturn(models);

        List<MedicaoLista> resultado = medicaoListaApplication.buscarTodos();

        assertThat(resultado.isEmpty(), is(true));
        verify(medicaoListaRepository, times(1)).buscarTodos();
    }

    @Test
    public void testBuscarPorId_QuandoIdNaoExiste_DeveLancarNullPointerException() {
        int idInexistente = 999;
        when(medicaoListaRepository.buscarPorId(idInexistente)).thenReturn(null);

        try {
            medicaoListaApplication.buscarPorId(idInexistente);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is(nullValue()));
        }
        verify(medicaoListaRepository).buscarPorId(idInexistente);
    }

    @Test
    public void testBuscarPorId_ComIdZero_DeveChamarRepository() {
        int idZero = 0;
        when(medicaoListaRepository.buscarPorId(idZero)).thenReturn(null);

        try {
            medicaoListaApplication.buscarPorId(idZero);
        } catch (NullPointerException e) {
            // Esperado
        }

        verify(medicaoListaRepository).buscarPorId(idZero);
    }

    @Test
    public void testBuscarPorId_ComIdNegativo_DeveChamarRepository() {
        int idNegativo = -1;
        when(medicaoListaRepository.buscarPorId(idNegativo)).thenReturn(null);

        try {
            medicaoListaApplication.buscarPorId(idNegativo);
        } catch (NullPointerException e) {
            // Esperado
        }

        verify(medicaoListaRepository).buscarPorId(idNegativo);
    }

    @Test
    public void testAdicionar_ComMedicaoListaNull_DeveLancarNullPointerException() {
        try {
            medicaoListaApplication.adicionar(null);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
        verify(medicaoListaRepository, never()).adicionar(any(MedicaoListaModel.class));
    }

    @Test
    public void testAdicionar_ComMedicaoListaCompleta_DeveConverterCorretamente() {
        MedicaoLista medicaoLista = new MedicaoLista();
        medicaoLista.setResultado(85.5);
        medicaoLista.setTipoMedicao("BPM");
        medicaoLista.setDataHora("2026-03-30 14:00:00");
        medicaoLista.setMedicaoId(10);
        medicaoLista.setSensorId(5);

        medicaoListaApplication.adicionar(medicaoLista);

        verify(medicaoListaRepository, times(1)).adicionar(any(MedicaoListaModel.class));
    }

    @Test
    public void testAtualizar_ComIdInexistente_DeveChamarRepositoryMesmoAssim() {
        int idInexistente = 999;
        MedicaoLista medicaoLista = new MedicaoLista();
        medicaoLista.setResultado(75.0);

        medicaoListaApplication.atualizar(idInexistente, medicaoLista);

        verify(medicaoListaRepository).atualizar(eq(idInexistente), any(MedicaoListaModel.class));
    }

    @Test
    public void testAtualizar_ComMedicaoListaNull_DeveLancarNullPointerException() {
        int idTeste = 1;

        try {
            medicaoListaApplication.atualizar(idTeste, null);
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
        verify(medicaoListaRepository, never()).atualizar(anyInt(), any(MedicaoListaModel.class));
    }

    @Test
    public void testExcluir_ComIdInexistente_DeveChamarRepositoryMesmoAssim() {
        int idInexistente = 999;

        medicaoListaApplication.excluir(idInexistente);

        verify(medicaoListaRepository).excluir(idInexistente);
    }

    @Test
    public void testExcluir_ComIdZero_DeveChamarRepository() {
        int idZero = 0;

        medicaoListaApplication.excluir(idZero);

        verify(medicaoListaRepository).excluir(idZero);
    }

    @Test
    public void testExcluir_ComIdNegativo_DeveChamarRepository() {
        int idNegativo = -5;

        medicaoListaApplication.excluir(idNegativo);

        verify(medicaoListaRepository).excluir(idNegativo);
    }

    @Test
    public void testBuscarTodos_QuandoRepositoryRetornaNull_DeveLancarNullPointerException() {
        when(medicaoListaRepository.buscarTodos()).thenReturn(null);

        try {
            medicaoListaApplication.buscarTodos();
            fail("Deveria ter lançado NullPointerException");
        } catch (NullPointerException e) {
            // Esperado
        }
    }

    @Test
    public void testVerificarUltimaMedicao_DelegacaoCorreta() {
        // Arrange
        List<MedicaoListaModel> models = new ArrayList<>();
        MedicaoListaModel model = new MedicaoListaModel();
        model.setResultado(75.0);
        models.add(model);
        when(medicaoListaRepository.buscarTodos()).thenReturn(models);

        // Act
        ResultadoValidacao resultadoViaUltima = medicaoListaApplication.verificarUltimaMedicao();
        ResultadoValidacao resultadoViaUltimo = medicaoListaApplication.verificarUltimoResultado();

        // Assert
        assertThat(resultadoViaUltima.getMensagem(), is(resultadoViaUltimo.getMensagem()));
        assertThat(resultadoViaUltima.getResultado(), is(resultadoViaUltimo.getResultado()));
        assertThat(resultadoViaUltima.isAcimaDaNormal(), is(resultadoViaUltimo.isAcimaDaNormal()));
    }

    @Test
    public void testVerificarUltimoResultado_ComMultiplasMedicoes_PegaUltima() {
        // Arrange
        List<MedicaoListaModel> models = new ArrayList<>();

        MedicaoListaModel model1 = new MedicaoListaModel();
        model1.setResultado(50.0);
        models.add(model1);

        MedicaoListaModel model2 = new MedicaoListaModel();
        model2.setResultado(80.0);
        models.add(model2);

        MedicaoListaModel model3 = new MedicaoListaModel();
        model3.setResultado(120.0);
        models.add(model3);

        when(medicaoListaRepository.buscarTodos()).thenReturn(models);

        // Act
        ResultadoValidacao resultado = medicaoListaApplication.verificarUltimoResultado();

        // Assert - Deve pegar a última (model3)
        assertThat(resultado.getResultado(), is(120.0));
        assertThat(resultado.isAcimaDaNormal(), is(true));
        assertThat(resultado.getMensagem(), containsString("acima do normal"));
    }
}