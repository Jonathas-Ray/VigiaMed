package org.example.repositories;

import org.example.entities.Sensor;
import org.example.interfaces.SensorRepository;

import java.util.List;
import java.util.ArrayList;

public class SensorRepositoryImpl implements SensorRepository {
    private List<Sensor> sensores = new ArrayList<>();

    public List<Sensor> buscarTodos() {
        return sensores;
    }

    public Sensor buscarPorId(int id) {
        return sensores
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar(Sensor sensor) {
        this.sensores.add(sensor);
    }

    public void excluir(int id) {
        this.sensores.removeIf(l -> l.getId() == id);
    }

    public void atualizar(int id, Sensor sensor) {
        Sensor sensorInMemory = buscarPorId(id);

        sensorInMemory.setNome(sensor.getNome());
        sensorInMemory.setUnidadeMedida(sensor.getUnidadeMedida());
    }
}
