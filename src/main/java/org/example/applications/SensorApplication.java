package org.example.applications;

import org.example.interfaces.SensorRepository;
import org.example.models.SensorModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.List;

@Service
public class SensorApplication {
    private final SensorRepository sensorRepository;

    public SensorApplication(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<SensorModel> buscarTodos() {
        return this.sensorRepository.buscarTodos();
    }

    public SensorModel buscarPorId(int id) {
        return this.sensorRepository.buscarPorId(id);
    }

<<<<<<< HEAD
    public void adicionar(SensorModel sensor) {
        this.sensorRepository.adicionar(sensor);
=======
    public void adicionar(SensorModel sensorModel) {
        this.sensorRepository.adicionar(sensorModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.sensorRepository.excluir(id);
    }

<<<<<<< HEAD
    public void atualizar(int id, SensorModel sensor) {
        this.sensorRepository.atualizar(id, sensor);
=======
    public void atualizar(int id, SensorModel sensorModel) {
        this.sensorRepository.atualizar(id, sensorModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}
