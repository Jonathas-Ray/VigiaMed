package org.example.interfaces;

import org.example.models.SensorModel;

import java.util.List;

public interface SensorRepository {
    public List<SensorModel> buscarTodos();
    public SensorModel buscarPorId(long id);
    public void adicionar(SensorModel sensorModel);
    public void excluir(long id);
    public void atualizar(long id, SensorModel sensorModel);
}