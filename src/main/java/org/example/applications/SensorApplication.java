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

    public void adicionar(SensorModel sensorModel) {
        this.sensorRepository.adicionar(sensorModel);
    }

    public void excluir(int id) {
        this.sensorRepository.excluir(id);
    }

    public void atualizar(int id, SensorModel sensorModel) {
        this.sensorRepository.atualizar(id, sensorModel);
    }
}
