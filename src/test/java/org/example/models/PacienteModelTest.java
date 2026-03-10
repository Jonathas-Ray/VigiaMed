package org.example.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class PacienteModelTest {

    @Test
    void deveTestarConstrutorEGetters() {
        List<MedicaoModel> medicoes = new ArrayList<>();
        PacienteModel paciente = new PacienteModel("Carlos Oliveira", "REF-999", medicoes);
        paciente.setId(10);

        assertEquals(10, paciente.getId());
        assertEquals("Carlos Oliveira", paciente.getNome());
        assertEquals("REF-999", paciente.getReferencia());
        assertEquals(medicoes, paciente.getMedicoes());
    }

    @Test
    void deveTestarSetters() {
        PacienteModel paciente = new PacienteModel();
        List<MedicaoModel> novaLista = new ArrayList<>();
        
        paciente.setId(5);
        paciente.setNome("Ana Souza");
        paciente.setReferencia("REF-000");
        paciente.setMedicoes(novaLista);

        assertEquals(5, paciente.getId());
        assertEquals("Ana Souza", paciente.getNome());
        assertEquals("REF-000", paciente.getReferencia());
        assertNotNull(paciente.getMedicoes());
    }

    @Test
    void deveGarantirListaVaziaPorPadrao() {
        PacienteModel paciente = new PacienteModel();
        assertNotNull(paciente.getMedicoes(), "A lista de medições não deve ser nula ao iniciar o objeto");
        assertTrue(paciente.getMedicoes().isEmpty());
    }
}