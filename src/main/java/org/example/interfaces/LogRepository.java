package org.example.interfaces;

import org.example.entities.Log;

import java.util.List;

public interface LogRepository {
    List<Log> buscarTodos();
    Log buscarPorId(int id);
    void adicionar(Log log);
    void excluir(int id);
    void atualizar(int id, Log log);
}
