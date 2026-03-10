package org.example.applications;

import static org.mockito.Mockito.*;
import org.example.entities.Paciente;
import org.example.interfaces.PacienteRepository;
import org.example.models.PacienteModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class PacienteApplicationTest {

    @Mock
    private PacienteRepository repository;

    @InjectMocks
    private PacienteApplication application;

    @Test
    void deveAdicionarConvertendoParaModel() {
        Paciente p = new Paciente(1, "Nome", "REF", new ArrayList<>());
        application.adicionar(p);
        
        verify(repository).adicionar(any(PacienteModel.class));
    }
}