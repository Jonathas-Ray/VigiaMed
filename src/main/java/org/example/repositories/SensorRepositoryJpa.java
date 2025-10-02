package org.example.repositories;

import org.example.interfaces.SensorRepository;
import org.example.models.SensorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensorRepositoryJpa implements SensorRepository {

    private final SensorRepositoryJpa sensorRepositoryJpa;

    @Autowired
    public SensorRepositoryJpa(SensorRepositoryJpa sensorRepositoryJpa) {
        this.sensorRepositoryJpa = sensorRepositoryJpa;
    }

    @Override
    public List<SensorModel> buscarTodos() { return List.of(); }

    @Override
    public SensorModel buscarPorId(long id) { return null; }

    @Override
    public void adicionar(SensorModel sensorModel) { }

    @Override
    public void excluir(long id) { }

    @Override
    public void atualizar(long id, SensorModel sensorModel) { }
}
