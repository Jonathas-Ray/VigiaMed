package org.example.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.example.interfaces.MedicaoListaModelRepositoryJpa;
import org.example.interfaces.MedicaoModelRepositoryJpa;
import org.example.models.MedicaoListaModel;
import org.example.models.MedicaoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

@DataJpaTest
class MedicaoListaRepositoryJpaTest {

    @Autowired
    private MedicaoListaModelRepositoryJpa repository;

    @Test
    void deveRetornarListaDeResultadosOrdenadaPeloIdDescendente() {
        // Preparando dados: Salva três medições com valores diferentes
        MedicaoListaModel m1 = new MedicaoListaModel(70.0, "BPM", "10:00", 1, null, 1, null);
        MedicaoListaModel m2 = new MedicaoListaModel(85.0, "BPM", "10:05", 1, null, 1, null);
        MedicaoListaModel m3 = new MedicaoListaModel(90.0, "BPM", "10:10", 1, null, 1, null);
        
        repository.save(m1);
        repository.save(m2);
        repository.save(m3);

        // Executa a query customizada definida na Interface
        List<Double> resultados = repository.findUltimoResultadoList();

        // Validações
        assertNotNull(resultados);
        assertFalse(resultados.isEmpty());
        assertEquals(3, resultados.size());
        
        // O primeiro item deve ser o último inserido (ID maior devido ao ORDER BY ID DESC)
        assertEquals(90.0, resultados.get(0));
        assertEquals(85.0, resultados.get(1));
        assertEquals(70.0, resultados.get(2));
    }

    @Test
    void deveRetornarVazioQuandoNaoHouverDados() {
        List<Double> resultados = repository.findUltimoResultadoList();
        assertTrue(resultados.isEmpty());
    }
}