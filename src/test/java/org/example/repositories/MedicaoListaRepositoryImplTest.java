package org.example.repositories;

import static org.junit.jupiter.api.Assertions.*;
import org.example.models.MedicaoListaModel;
import org.junit.jupiter.api.Test;

class MedicaoListaRepositoryImplTest {

    @Test
    void deveAdicionarERemoverMedicao() {
        MedicaoListaRepositoryImpl repo = new MedicaoListaRepositoryImpl();
        MedicaoListaModel model = new MedicaoListaModel(70.0, "BPM", "2023-10-10", 1, null, 1, null);
        
        repo.adicionar(model);
        assertEquals(1, repo.buscarTodos().size());

        repo.excluir(model.getId());
        assertEquals(0, repo.buscarTodos().size());
    }
}