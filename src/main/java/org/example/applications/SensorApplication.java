package org.example.applications;

import org.example.interfaces.SensorRepository;
import org.example.entities.Sensor;
import java.util.List;

public class SensorApplication {
    private SensorRepository sensorRepository;

    public SensorApplication(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> buscarTodos() {
        return this.sensorRepository.buscarTodos();
    }

    public Sensor buscarPorId(int id) {
        return this.sensorRepository.buscarPorId(id);
    }

    public void adicionar(Sensor sensor) {
        this.sensorRepository.adicionar(sensor);
    }

    public void excluir(int id) {
        this.sensorRepository.excluir(id);
    }

    public void atualizar(int id, Sensor sensor) {
        this.sensorRepository.atualizar(id, sensor);
    }
}
