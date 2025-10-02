package org.example.interfaces;

import org.example.models.MedicaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicaoModelRepositoryJpa extends JpaRepository<MedicaoModel, Integer> {
}
