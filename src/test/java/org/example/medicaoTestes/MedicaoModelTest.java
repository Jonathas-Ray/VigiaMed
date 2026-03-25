package org.example.medicaoTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.example.models.DispositivoModel;
import org.example.models.MedicaoListaModel;
import org.example.models.MedicaoModel;
import org.example.models.PacienteModel;
import org.junit.Before;
import org.junit.Test;

public class MedicaoModelTest {

    private MedicaoModel model;

    @Before
    public void setUp() {
        model = new MedicaoModel();
    }

    @Test
    public void testSetId() {
        int valor = 1;
        model.setId(valor);
        assertThat(model.getId(), is(valor));
    }

    @Test
    public void testSetDescricao() {
        String valor = "Medição de Pressão Arterial";
        model.setDescricao(valor);
        assertThat(model.getDescricao(), is(valor));
    }

    @Test
    public void testSetDataHora() {
        String valor = "2026-03-10 11:00:00";
        model.setDataHora(valor);
        assertThat(model.getDataHora(), is(valor));
    }

    @Test
    public void testSetPacienteId() {
        int valor = 10;
        model.setPacienteId(valor);
        assertThat(model.getPacienteId(), is(valor));
    }

    @Test
    public void testSetPaciente() {
        PacienteModel valor = new PacienteModel();
        model.setPaciente(valor);
        assertThat(model.getPaciente(), is(valor));
    }

    @Test
    public void testSetDispositivoId() {
        int valor = 5;
        model.setDispositivoId(valor);
        assertThat(model.getDispositivoId(), is(valor));
    }

    @Test
    public void testSetDispositivo() {
        DispositivoModel valor = new DispositivoModel();
        model.setDispositivo(valor);
        assertThat(model.getDispositivo(), is(valor));
    }

    @Test
    public void testSetMedicoesLista() {
        List<MedicaoListaModel> valor = new ArrayList<>();
        model.setMedicoesLista(valor);
        assertThat(model.getMedicoesLista(), is(valor));
    }

    @Test
    public void testConstrutorComParametros() {
        String desc = "Teste";
        String data = "2026-03-10";
        int pacId = 1;
        int dispId = 2;
        List<MedicaoListaModel> lista = new ArrayList<>();

        MedicaoModel novoModel = new MedicaoModel(desc, data, pacId, null, dispId, null, lista);

        assertThat(novoModel.getDescricao(), is(desc));
        assertThat(novoModel.getDataHora(), is(data));
        assertThat(novoModel.getPacienteId(), is(pacId));
        assertThat(novoModel.getDispositivoId(), is(dispId));
        assertThat(novoModel.getMedicoesLista(), is(lista));
    }
}