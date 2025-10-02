package org.example.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.models.LogModel;

public interface LogModelRepositoryJpa extends JpaRepository<LogModel, Integer> {
}
