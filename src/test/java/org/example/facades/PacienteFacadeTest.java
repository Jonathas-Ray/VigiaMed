package org.example.facades;

import static org.mockito.Mockito.*;
import org.example.applications.PacienteApplication;
import org.example.entities.Paciente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PacienteFacadeTest {

    @Mock
    private PacienteApplication application;

    @InjectMocks
    private PacienteFacade facade;

    @Test
    void deveChamarMetodosDaApplication() {
        Paciente p = new Paciente();
        facade.adicionar(p);
        verify(application).adicionar(p);
        
        facade.buscarTodos();
        verify(application).buscarTodos();
    }
}