package org.example.dispositivosTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.example.models.DispositivoModel;
import org.example.models.MedicaoModel;
import org.example.models.StatusDispositivoModel;
import org.example.models.UnidadeModel;
import org.junit.Before;
import org.junit.Test;


/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre dispositivos, realizadas pela classe.
 *
 * @Heitor Trindade
 * @10/03/2026
 */
public class DispositivoModelTest {

    private DispositivoModel model;

    @Before
    public void setUp() {
        model = new DispositivoModel();
    }

    @Test
    public void testSetId() {
        int valor = 10;
        model.setId(valor);
        assertThat(model.getId(), is(valor));
    }

    @Test
    public void testSetModelo() {
        String valor = "Sensor XP";
        model.setModelo(valor);
        assertThat(model.getModelo(), is(valor));
    }

    @Test
    public void testSetNumeroSerie() {
        String valor = "SN123456";
        model.setNumeroSerie(valor);
        assertThat(model.getNumeroSerie(), is(valor));
    }

    @Test
    public void testSetDataAquisicao() {
        Date valor = new Date();
        model.setDataAquisicao(valor);
        assertThat(model.getDataAquisicao(), is(valor));
    }

    @Test
    public void testSetUnidadeId() {
        int valor = 5;
        model.setUnidadeId(valor);
        assertThat(model.getUnidadeId(), is(valor));
    }

    @Test
    public void testSetUnidade() {
        UnidadeModel valor = new UnidadeModel();
        model.setUnidade(valor);
        assertThat(model.getUnidade(), is(valor));
    }

    @Test
    public void testSetStatusDispositivoId() {
        int valor = 2;
        model.setStatusDispositivoId(valor);
        assertThat(model.getStatusDispositivoId(), is(valor));
    }

    @Test
    public void testSetStatusDispositivoModel() {
        StatusDispositivoModel valor = new StatusDispositivoModel();
        model.setStatusDispositivoModel(valor);
        assertThat(model.getStatusDispositivoModel(), is(valor));
    }

    @Test
    public void testSetMedicoes() {
        List<MedicaoModel> valor = new ArrayList<>();
        model.setMedicoes(valor);
        assertThat(model.getMedicoes(), is(valor));
    }

    @Test
    public void testConstrutorComParametros() {
        String modelo = "Modelo Teste";
        String serie = "123";
        Date data = new Date();
        int unidadeId = 1;
        int statusId = 1;
        List<MedicaoModel> medicoes = new ArrayList<>();

        DispositivoModel novoModel = new DispositivoModel(
                modelo, serie, data, unidadeId, null, statusId, null, medicoes
        );

        assertThat(novoModel.getModelo(), is(modelo));
        assertThat(novoModel.getNumeroSerie(), is(serie));
        assertThat(novoModel.getDataAquisicao(), is(data));
        assertThat(novoModel.getUnidadeId(), is(unidadeId));
        assertThat(novoModel.getStatusDispositivoId(), is(statusId));
        assertThat(novoModel.getMedicoes(), is(medicoes));
    }
}