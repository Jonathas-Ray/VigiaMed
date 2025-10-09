package org.example.repositories;

import org.example.interfaces.LogModelRepositoryJpa;
import org.example.interfaces.LogRepository;
import org.example.models.LogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogRepositoryJpa implements LogRepository {

    private final LogModelRepositoryJpa logModelRepositoryJpa;

    @Autowired
    public LogRepositoryJpa(LogModelRepositoryJpa logModelRepositoryJpa) {
        this.logModelRepositoryJpa = logModelRepositoryJpa;
    }

    @Override
    public List<LogModel> buscarTodos() {
        return List.of();
    }

    @Override
    public LogModel buscarPorId(int id) {
        return null;
    }

    @Override
    public void adicionar(LogModel log) {

    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public void atualizar(int id, LogModel log) {

    }
}
