package org.example.dispositivosTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.example.entities.Dispositivo;
import org.example.models.DispositivoModel;
import org.example.models.MedicaoModel;
import org.junit.Test;


/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre dispositivos, realizadas pela classe.
 *
 * @Heitor Trindade
 * @10/03/2026
 */
public class DispositivoEntitieTest {

    @Test
    public void testToModelAtributosBasicos() {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setModelo("Sensor Termico");
        dispositivo.setNumeroSerie("SN-123");

        DispositivoModel model = dispositivo.toModel();

        assertThat(model.getModelo(), is("Sensor Termico"));
        assertThat(model.getNumeroSerie(), is("SN-123"));
    }

    @Test
    public void testToModelDatasERelacoes() {
        Date data = new Date();
        List<MedicaoModel> medicoes = new ArrayList<>();
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setDataAquisicao(data);
        dispositivo.setUnidadeId(10);
        dispositivo.setMedicoes(medicoes);

        DispositivoModel model = dispositivo.toModel();

        assertThat(model.getDataAquisicao(), is(data));
        assertThat(model.getUnidadeId(), is(10));
        assertThat(model.getMedicoes(), is(medicoes));
    }

    @Test
    public void testFromModelAtributosBasicos() {
        DispositivoModel model = new DispositivoModel();
        model.setId(500);
        model.setModelo("Controlador Industrial");

        Dispositivo dispositivo = Dispositivo.fromModel(model);

        assertThat(dispositivo.getId(), is(500));
        assertThat(dispositivo.getModelo(), is("Controlador Industrial"));
    }

    @Test
    public void testFromModelIdsERelacoes() {
        DispositivoModel model = new DispositivoModel();
        model.setUnidadeId(5);
        model.setStatusDispositivoId(1);
        List<MedicaoModel> medicoes = new ArrayList<>();
        model.setMedicoes(medicoes);

        Dispositivo dispositivo = Dispositivo.fromModel(model);

        assertThat(dispositivo.getUnidadeId(), is(5));
        assertThat(dispositivo.getStatusDispositivoId(), is(1));
        assertThat(dispositivo.getMedicoes(), is(medicoes));
    }
}