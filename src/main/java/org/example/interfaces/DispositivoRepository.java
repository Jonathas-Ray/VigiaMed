package org.example.interfaces;

import org.example.models.DispositivoModel;

import java.util.List;

public interface DispositivoRepository {
    List<DispositivoModel> buscarTodos();
    DispositivoModel buscarPorId(int id);
    void adicionar(DispositivoModel dispositivoModel);
    void excluir(int id);
    void atualizar(int id, DispositivoModel dispositivoModel);
}