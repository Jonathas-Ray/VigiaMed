package org.example.facades;

import org.example.applications.SensorApplication;
<<<<<<< HEAD
import org.example.entities.Sensor;
import org.example.models.SensorModel;
=======
import org.example.models.SensorModel;
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.List;

@Service
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

<<<<<<< HEAD
    public void adicionar(SensorModel sensor) {
        this.sensorApplication.adicionar(sensor);
=======
    public void adicionar(SensorModel sensorModel) {
        this.sensorApplication.adicionar(sensorModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.sensorApplication.excluir(id);
    }

<<<<<<< HEAD
    public void atualizar(int id, SensorModel sensor) {
        this.sensorApplication.atualizar(id, sensor);
=======
    public void atualizar(int id, SensorModel sensorModel) {
        this.sensorApplication.atualizar(id, sensorModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}
