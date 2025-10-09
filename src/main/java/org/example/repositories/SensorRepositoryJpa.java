package org.example.repositories;

import org.example.interfaces.SensorModelRepositoryJpa;
import org.example.interfaces.SensorRepository;
import org.example.models.SensorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SensorRepositoryJpa implements SensorRepository {

    private final SensorModelRepositoryJpa sensorModelRepositoryJpa;


    public SensorRepositoryJpa(SensorModelRepositoryJpa sensorModelRepositoryJpa) {
        this.sensorModelRepositoryJpa = sensorModelRepositoryJpa;
    }

    @Override
    public List<SensorModel> buscarTodos() {
        return this.sensorModelRepositoryJpa.findAll();
    }

    @Override
    public SensorModel buscarPorId(int id) {
        return this.sensorModelRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public void adicionar(SensorModel sensorModel) {
        this.sensorModelRepositoryJpa.save(sensorModel);
    }

    @Override
    public void excluir(int id) {
        this.sensorModelRepositoryJpa.deleteById(id);
    }

    @Override
    public void atualizar(int id, SensorModel sensorModel) {
        sensorModel.setId(id);
        this.sensorModelRepositoryJpa.save(sensorModel);
    }
}
