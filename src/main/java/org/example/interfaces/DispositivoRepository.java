package org.example.interfaces;

import org.example.entities.Dispositivo;

import java.util.List;

public interface DispositivoRepository {
    public List<Dispositivo> buscarTodos();
    public Dispositivo buscarPorId(int id);
    public void adicionar(Dispositivo Dispositivo);
    public void excluir(int id);
}