package org.example.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.example.models.PacienteModel;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class PacienteTest {
    @Test
    void deveConverterParaModelMantendoOsDados() {
        Paciente paciente = new Paciente(1, "João Silva", "REF123", new ArrayList<>());
        PacienteModel model = paciente.toModel();

        assertEquals("João Silva", model.getNome());
        assertEquals("REF123", model.getReferencia());
    }

    @Test
    void deveCriarAPartirDeModel() {
        PacienteModel model = new PacienteModel("Maria Souza", "REF456", new ArrayList<>());
        model.setId(2);
        
        Paciente paciente = new Paciente().fromModel(model);

        assertEquals(2, paciente.getId());
        assertEquals("Maria Souza", paciente.getNome());
    }
}