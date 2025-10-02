package org.example.interfaces;

import org.example.models.LogModel;

import java.util.List;

public interface LogRepository {
    List<LogModel> buscarTodos();
    LogModel buscarPorId(int id);
    void adicionar(LogModel log);
    void excluir(int id);
    void atualizar(int id, LogModel log);
}
