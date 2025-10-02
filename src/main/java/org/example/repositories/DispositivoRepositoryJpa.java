package org.example.repositories;

import org.example.interfaces.DispositivoModelRepositoryJpa;
import org.example.interfaces.DispositivoRepository;
import org.example.models.DispositivoModel;
import org.hibernate.internal.build.AllowNonPortable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DispositivoRepositoryJpa implements DispositivoRepository {

    private final DispositivoModelRepositoryJpa dispositivoModelRepositoryJpa;
    @Autowired
    public DispositivoRepositoryJpa(DispositivoModelRepositoryJpa dispositivoModelRepositoryJpa) {
        this.dispositivoModelRepositoryJpa = dispositivoModelRepositoryJpa;
    }

    @Override
    public List<DispositivoModel> buscarTodos() {
        return this.dispositivoModelRepositoryJpa.findAll();
    }

    @Override
    public DispositivoModel buscarPorId(int id) {
        return dispositivoModelRepositoryJpa.findById(id).get();
    }

    @Override
    public void adicionar(DispositivoModel dispositivoModel) {
        this.dispositivoModelRepositoryJpa.save(dispositivoModel);
    }

    @Override
    public void excluir(int id) {
        this.dispositivoModelRepositoryJpa.deleteById(id);
    }

    @Override
    public void atualizar(int id, DispositivoModel dispositivoModel) {
        dispositivoModel.setId(id);
        this.dispositivoModelRepositoryJpa.save(dispositivoModel);
    }
}
