package org.example.interfaces;

import org.example.models.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteModelRepositoryJpa extends JpaRepository<PacienteModel,Integer> {
}
