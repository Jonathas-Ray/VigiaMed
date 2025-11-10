package org.example.applications;

import org.example.entities.Sensor;
import org.example.interfaces.SensorRepository;
import org.example.models.SensorModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorApplication {
    private final SensorRepository sensorRepository;

    public SensorApplication(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> buscarTodos() {
        List<SensorModel> modelList = this.sensorRepository.buscarTodos();
        List<Sensor> entitieList = new ArrayList<>();
        for(SensorModel sensorModel : modelList) {
            entitieList.add(new Sensor().fromModel(sensorModel));
        }
        return entitieList;
    }

    public Sensor buscarPorId(int id) {
        Sensor sensor = new Sensor().fromModel(this.sensorRepository.buscarPorId(id));
        return sensor;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void adicionar(SensorModel sensor) {
        this.sensorRepository.adicionar(sensor);
=======
    public void adicionar(SensorModel sensorModel) {
=======
    public void adicionar(Sensor sensor) {
        SensorModel sensorModel = sensor.toModel();
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
        this.sensorRepository.adicionar(sensorModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.sensorRepository.excluir(id);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void atualizar(int id, SensorModel sensor) {
        this.sensorRepository.atualizar(id, sensor);
=======
    public void atualizar(int id, SensorModel sensorModel) {
=======
    public void atualizar(int id, Sensor sensor){
        SensorModel sensorModel = sensor.toModel();
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
        this.sensorRepository.atualizar(id, sensorModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}