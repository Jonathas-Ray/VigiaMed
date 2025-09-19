package org.example.interfaces;

import org.example.entities.StatusDispositivo;
import org.example.entities.Unidade;


import java.util.List;

public interface StatusDispositivoRepository {
    public List<StatusDispositivo> buscarTodos();
    public StatusDispositivo buscarPorId(int id);
    public void adicionar(StatusDispositivo statusDispositivo);
    public void excluir(int id);
    public void atualizar(int id, StatusDispositivo statusDispositivo);
}
