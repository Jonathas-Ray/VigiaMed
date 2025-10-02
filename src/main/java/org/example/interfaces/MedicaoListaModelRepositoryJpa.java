package org.example.interfaces;

import org.example.models.MedicaoListaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicaoListaModelRepositoryJpa extends JpaRepository<MedicaoListaModel, Integer> {
}
