package org.example.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.example.models.MedicaoListaModel;
import org.junit.jupiter.api.Test;

class MedicaoListaMappingTest {

    @Test
    void deveConverterEntidadeParaModel() {
        MedicaoLista entidade = new MedicaoLista();
        entidade.setResultado(95.5);
        entidade.setTipoMedicao("Temperatura");

        MedicaoListaModel model = entidade.toModel();

        assertEquals(95.5, model.getResultado());
        assertEquals("Temperatura", model.getTipoMedicao());
    }
}