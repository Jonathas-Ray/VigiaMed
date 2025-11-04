package org.example.facades;

import org.example.applications.SensorApplication;
import org.example.entities.Sensor;
import org.example.models.SensorModel;

import java.util.List;

public class SensorFacade {
    private final SensorApplication sensorApplication;

    public SensorFacade(SensorApplication sensorApplication) {
        this.sensorApplication = sensorApplication;
    }

    public List<SensorModel> buscarTodos() {
        return this.sensorApplication.buscarTodos();
    }

    public SensorModel buscarPorId(int id) {
        return this.sensorApplication.buscarPorId(id);
    }

    public void adicionar(SensorModel sensor) {
        this.sensorApplication.adicionar(sensor);
    }

    public void excluir(int id) {
        this.sensorApplication.excluir(id);
    }

    public void atualizar(int id, SensorModel sensor) {
        this.sensorApplication.atualizar(id, sensor);
    }
}
