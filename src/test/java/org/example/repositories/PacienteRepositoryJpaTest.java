package org.example.repositories;

import static org.mockito.Mockito.*;
import java.util.Optional;
import org.example.interfaces.PacienteModelRepositoryJpa;
import org.example.models.PacienteModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PacienteRepositoryJpaTest {

    @Mock
    private PacienteModelRepositoryJpa jpa;

    @InjectMocks
    private PacienteRepositoryJpa repository;

    @Test
    void deveChamarFindByIdNoJpa() {
        PacienteModel model = new PacienteModel();
        when(jpa.findById(1)).thenReturn(Optional.of(model));
        
        repository.buscarPorId(1);
        
        verify(jpa).findById(1);
    }

    @Test
    void deveSalvarAoAtualizar() {
        PacienteModel model = new PacienteModel();
        repository.atualizar(1, model);
        
        verify(jpa).save(model);
    }
}