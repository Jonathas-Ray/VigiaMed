package org.example.facades;

import org.example.applications.SensorApplication;
import org.example.entities.Sensor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorFacade {
    private final SensorApplication sensorApplication;

    public SensorFacade(SensorApplication sensorApplication) {
        this.sensorApplication = sensorApplication;
    }

    public List<Sensor> buscarTodos() {
        return this.sensorApplication.buscarTodos();
    }

    public Sensor buscarPorId(int id) {
        return this.sensorApplication.buscarPorId(id);
    }

    public void adicionar(Sensor sensor) {
        this.sensorApplication.adicionar(sensor);
    }

    public void excluir(int id) {
        this.sensorApplication.excluir(id);
    }

    public void atualizar(int id, Sensor sensor) {
        this.sensorApplication.atualizar(id, sensor);
    }
}