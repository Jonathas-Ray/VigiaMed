package org.example.interfaces;

import org.example.entities.Sensor;

import java.util.List;

public interface SensorRepository {
    public List<Sensor> buscarTodos();
    public Sensor buscarPorId(int id);
    public void adicionar(Sensor sensor);
    public void excluir(int id);
    public void atualizar(int id, Sensor sensor);
}