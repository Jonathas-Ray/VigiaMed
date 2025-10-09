package org.example.repositories;

import org.example.interfaces.SensorRepository;
import org.example.models.SensorModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

public class SensorRepositoryImpl implements SensorRepository {
    private final List<SensorModel> sensorModels = new ArrayList<>();

    public List<SensorModel> buscarTodos() {
        return sensorModels;
    }

    public SensorModel buscarPorId(long id) {
        return sensorModels
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar(SensorModel sensor) {
        this.sensorModels.add(sensor);
    }

    public void excluir(long id) {
        this.sensorModels.removeIf(l -> l.getId() == id);
    }

    public void atualizar(long id, SensorModel sensorModel) {
        SensorModel sensorInMemory = buscarPorId(id);
        sensorInMemory.setNome(sensorModel.getNome());
        sensorInMemory.setUnidadeMedida(sensorModel.getUnidadeMedida());
    }
}
