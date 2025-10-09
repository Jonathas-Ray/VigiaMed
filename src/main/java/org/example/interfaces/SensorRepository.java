package org.example.interfaces;

import org.example.models.SensorModel;

import java.util.List;

public interface SensorRepository {
    public List<SensorModel> buscarTodos();
    public SensorModel buscarPorId(int id);
    public void adicionar(SensorModel sensorModel);
    public void excluir(int id);
    public void atualizar(int id, SensorModel sensorModel);
}