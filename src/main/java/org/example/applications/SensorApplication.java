package org.example.applications;

import org.example.entities.Sensor;
import org.example.interfaces.SensorRepository;
import org.example.models.SensorModel;
import org.springframework.stereotype.Service;

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

    public void adicionar(Sensor sensor) {
        SensorModel sensorModel = sensor.toModel();
        this.sensorRepository.adicionar(sensorModel);
    }

    public void excluir(int id) {
        this.sensorRepository.excluir(id);
    }

    public void atualizar(int id, Sensor sensor){
        SensorModel sensorModel = sensor.toModel();
        this.sensorRepository.atualizar(id, sensorModel);
    }
}