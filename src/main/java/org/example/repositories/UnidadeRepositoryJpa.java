package org.example.repositories;

import org.example.interfaces.UnidadeModelRepositoryJpa;
import org.example.interfaces.UnidadeRepository;
import org.example.models.UnidadeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnidadeRepositoryJpa implements UnidadeRepository {

    private final UnidadeModelRepositoryJpa unidadeModelRepositoryJpa;

    @Autowired
    public UnidadeRepositoryJpa(UnidadeModelRepositoryJpa unidadeModelRepositoryJpa){
        this.unidadeModelRepositoryJpa = unidadeModelRepositoryJpa;
    }

    @Override
    public List<UnidadeModel> buscarTodos() {
        return this.unidadeModelRepositoryJpa.findAll();
    }

    @Override
    public UnidadeModel buscarPorId(int id) {
        return this.unidadeModelRepositoryJpa.findById(id).get();
    }

    @Override
    public void adicionar(UnidadeModel unidadeModel) {
        this.unidadeModelRepositoryJpa.save(unidadeModel);
    }

    @Override
    public void excluir(int id) {
        this.unidadeModelRepositoryJpa.deleteById(id);
    }

    @Override
    public void atualizar(int id, UnidadeModel unidadeModel) {
        unidadeModel.setId(id);
        this.unidadeModelRepositoryJpa.save(unidadeModel);
    }
}
