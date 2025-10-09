package org.example.interfaces;

import org.example.models.SensorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorModelRepositoryJpa extends JpaRepository<SensorModel, Integer> {
}
