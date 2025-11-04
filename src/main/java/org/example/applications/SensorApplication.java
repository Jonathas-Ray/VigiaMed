package org.example.applications;

import org.example.interfaces.SensorRepository;
import org.example.models.SensorModel;

import java.util.List;

public class SensorApplication {
    private SensorRepository sensorRepository;

    public SensorApplication(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<SensorModel> buscarTodos() {
        return this.sensorRepository.buscarTodos();
    }

    public SensorModel buscarPorId(int id) {
        return this.sensorRepository.buscarPorId(id);
    }

    public void adicionar(SensorModel sensor) {
        this.sensorRepository.adicionar(sensor);
    }

    public void excluir(int id) {
        this.sensorRepository.excluir(id);
    }

    public void atualizar(int id, SensorModel sensor) {
        this.sensorRepository.atualizar(id, sensor);
    }
}
