package org.example.repositories;

import org.example.interfaces.StatusDispositivoModelRepositoryJpa;
import org.example.interfaces.StatusDispositivoRepository;
import org.example.models.StatusDispositivoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusDispositivoRepositoryJpa implements StatusDispositivoRepository {

    private final StatusDispositivoModelRepositoryJpa statusDispositivoModelRepositoryJpa;
    @Autowired
    public StatusDispositivoRepositoryJpa(StatusDispositivoModelRepositoryJpa statusDispositivoModelRepositoryJpa) {
        this.statusDispositivoModelRepositoryJpa = statusDispositivoModelRepositoryJpa;
    }

    @Override
    public List<StatusDispositivoModel> buscarTodos() {
        return this.statusDispositivoModelRepositoryJpa.findAll();
    }

    @Override
    public StatusDispositivoModel buscarPorId(int id) {
        return statusDispositivoModelRepositoryJpa.findById(id).get();
    }

    @Override
    public void adicionar(StatusDispositivoModel statusDispositivoModel) {
        this.statusDispositivoModelRepositoryJpa.save(statusDispositivoModel);
    }

    @Override
    public void excluir(int id) {
        this.statusDispositivoModelRepositoryJpa.deleteById(id);
    }

    @Override
    public void atualizar(int id, StatusDispositivoModel statusDispositivoModel) {
        statusDispositivoModel.setId(id);
        this.statusDispositivoModelRepositoryJpa.save(statusDispositivoModel);
    }
}
