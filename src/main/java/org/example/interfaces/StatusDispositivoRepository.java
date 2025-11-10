package org.example.interfaces;

import org.example.models.StatusDispositivoModel;


import java.util.List;

public interface StatusDispositivoRepository {
    public List<StatusDispositivoModel> buscarTodos();
    public StatusDispositivoModel buscarPorId(int id);
    public void adicionar(StatusDispositivoModel statusDispositivoModel);
    public void excluir(int id);
    public void atualizar(int id, StatusDispositivoModel statusDispositivoModel);
}
