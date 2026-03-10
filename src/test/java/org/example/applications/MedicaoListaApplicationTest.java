package org.example.applications;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.example.interfaces.MedicaoListaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MedicaoListaApplicationTest {

    @Mock
    private MedicaoListaRepository repository;

    @InjectMocks
    private MedicaoListaApplication application;

    @Test
    void deveValidarBatimentosNormais() {
        // Simulando que o último resultado no banco foi 80 bpm
        when(repository.findUltimoResultado()).thenReturn(80.0);

        var resultado = application.verificarUltimoResultado();

        assertEquals(80.0, resultado.getResultado());
        assertFalse(resultado.isAcimaDaNormal());
        assertTrue(resultado.getMensagem().contains("normais"));
    }

    @Test
    void deveValidarBatimentosAltos() {
        when(repository.findUltimoResultado()).thenReturn(110.0);

        var resultado = application.verificarUltimoResultado();

        assertTrue(resultado.isAcimaDaNormal());
        assertTrue(resultado.getMensagem().contains("acima do normal"));
    }
}