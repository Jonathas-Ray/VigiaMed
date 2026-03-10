package org.example.repositories;

import static org.junit.jupiter.api.Assertions.*;
import org.example.models.PacienteModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class PacienteRepositoryImplTest {
    private PacienteRepositoryImpl repository;

    @BeforeEach
    void setup() {
        repository = new PacienteRepositoryImpl();
    }

    @Test
    void deveAdicionarEGerarIdAutomaticamente() {
        PacienteModel p = new PacienteModel("Teste", "REF", new ArrayList<>());
        repository.adicionar(p);
        
        assertEquals(1, repository.buscarTodos().size());
        assertEquals(1, p.getId());
    }

    @Test
    void deveExcluirPacienteExistente() {
        PacienteModel p = new PacienteModel("Excluir", "REF", new ArrayList<>());
        repository.adicionar(p);
        repository.excluir(p.getId());
        
        assertNull(repository.buscarPorId(p.getId()));
    }
}