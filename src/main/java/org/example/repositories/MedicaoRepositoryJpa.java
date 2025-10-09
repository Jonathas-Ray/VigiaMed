package org.example.repositories;

import org.example.interfaces.MedicaoModelRepositoryJpa;
import org.example.interfaces.MedicaoRepository;
import org.example.models.MedicaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicaoRepositoryJpa implements MedicaoRepository {

    private final MedicaoModelRepositoryJpa medicaoModelRepositoryJpa;

    @Autowired
    public MedicaoRepositoryJpa(MedicaoModelRepositoryJpa medicaoModelRepositoryJpa) {
        this.medicaoModelRepositoryJpa = medicaoModelRepositoryJpa;
    }

    @Override
    public List<MedicaoModel> buscarTodos() {
        return this.medicaoModelRepositoryJpa.findAll();
    }

    @Override
    public MedicaoModel buscarPorId(int id) {
        return this.medicaoModelRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public void adicionar(MedicaoModel medicao) {
        this.medicaoModelRepositoryJpa.save(medicao);
    }

    @Override
    public void excluir(int id) {
        this.medicaoModelRepositoryJpa.deleteById(id);
    }

    @Override
    public void atualizar(int id, MedicaoModel medicao) {
        medicao.setId(id);
        this.medicaoModelRepositoryJpa.save(medicao);
    }
}
