package org.example.medicaoTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.example.entities.Medicao;
import org.example.models.MedicaoListaModel;
import org.example.models.MedicaoModel;
import org.junit.Before;
import org.junit.Test;

public class MedicaoEntityTest {

    private Medicao medicao;

    @Before
    public void setUp() {
        medicao = new Medicao();
    }

    @Test
    public void testSetId() {
        int valor = 500;
        medicao.setId(valor);
        assertThat(medicao.getId(), is(valor));
    }

    @Test
    public void testSetDescricao() {
        String valor = "Check-up Semanal";
        medicao.setDescricao(valor);
        assertThat(medicao.getDescricao(), is(valor));
    }

    @Test
    public void testSetDataHora() {
        String valor = "2026-03-10 14:00";
        medicao.setDataHora(valor);
        assertThat(medicao.getDataHora(), is(valor));
    }

    @Test
    public void testSetPacienteId() {
        int valor = 12;
        medicao.setPacienteId(valor);
        assertThat(medicao.getPacienteId(), is(valor));
    }

    @Test
    public void testSetDispositivoId() {
        int valor = 8;
        medicao.setDispositivoId(valor);
        assertThat(medicao.getDispositivoId(), is(valor));
    }

    @Test
    public void testSetMedicoesLista() {
        List<MedicaoListaModel> lista = new ArrayList<>();
        medicao.setMedicoesLista(lista);
        assertThat(medicao.getMedicoesLista(), is(lista));
    }

    @Test
    public void testToModelMapeamento() {
        medicao.setDescricao("Glicemia");
        medicao.setDataHora("2026-03-10");
        medicao.setPacienteId(1);
        medicao.setDispositivoId(2);
        List<MedicaoListaModel> lista = new ArrayList<>();
        medicao.setMedicoesLista(lista);

        MedicaoModel model = medicao.toModel();

        assertThat(model.getDescricao(), is(medicao.getDescricao()));
        assertThat(model.getDataHora(), is(medicao.getDataHora()));
        assertThat(model.getPacienteId(), is(medicao.getPacienteId()));
        assertThat(model.getDispositivoId(), is(medicao.getDispositivoId()));
        assertThat(model.getMedicoesLista(), is(lista));
    }

    @Test
    public void testFromModelMapeamento() {
        MedicaoModel model = new MedicaoModel();
        model.setId(77);
        model.setDescricao("Oximetria");
        model.setPacienteId(3);
        model.setDispositivoId(4);
        List<MedicaoListaModel> lista = new ArrayList<>();
        model.setMedicoesLista(lista);

        Medicao resultado = Medicao.fromModel(model);

        assertThat(resultado.getId(), is(77));
        assertThat(resultado.getDescricao(), is("Oximetria"));
        assertThat(resultado.getPacienteId(), is(3));
        assertThat(resultado.getDispositivoId(), is(4));
        assertThat(resultado.getMedicoesLista(), is(lista));
    }

    @Test
    public void testConstrutorCompleto() {
        List<MedicaoListaModel> lista = new ArrayList<>();
        Medicao novaMedicao = new Medicao(10, "Pressao", "Hoje", 5, null, 2, null, lista);

        assertThat(novaMedicao.getId(), is(10));
        assertThat(novaMedicao.getDescricao(), is("Pressao"));
        assertThat(novaMedicao.getPacienteId(), is(5));
        assertThat(novaMedicao.getMedicoesLista(), is(lista));
    }
}