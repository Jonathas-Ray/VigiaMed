package org.example.facades;

import org.example.applications.SensorApplication;
import org.example.models.SensorModel;
import org.springframework.stereotype.Service;

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

    public void adicionar(SensorModel sensorModel) {
        this.sensorApplication.adicionar(sensorModel);
    }

    public void excluir(int id) {
        this.sensorApplication.excluir(id);
    }

    public void atualizar(int id, SensorModel sensorModel) {
        this.sensorApplication.atualizar(id, sensorModel);
    }
}
