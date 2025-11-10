package org.example.repositories;

import org.example.interfaces.SensorRepository;
import org.example.models.SensorModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class SensorRepositoryImpl implements SensorRepository {
    private final List<SensorModel> sensores = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<SensorModel> buscarTodos() {
        return sensores;
    }

    public SensorModel buscarPorId(int id) {
        for (SensorModel sensor : sensores) {
            if (sensor.getId() == id) {
                return sensor;
            }
        }
        return null;
    }

    public void adicionar(SensorModel sensor) {
        if (sensor.getId() == 0) {
            sensor.setId(idCounter.getAndIncrement());
        }
        sensores.add(sensor);
    }

    public void excluir(int id) {
        SensorModel sensorRemover = buscarPorId(id);
        if (sensorRemover != null) {
            sensores.remove(sensorRemover);
        }
    }

    public void atualizar(int id, SensorModel sensor) {
        SensorModel existente = buscarPorId(id);
        if (existente != null) {
            existente.setNome(sensor.getNome());
            existente.setUnidadeMedida(sensor.getUnidadeMedida());
        }
    }
}
