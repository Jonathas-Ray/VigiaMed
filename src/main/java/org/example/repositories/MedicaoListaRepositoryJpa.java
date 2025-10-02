package org.example.repositories;

import org.example.interfaces.MedicaoListaRepository;
import org.example.models.MedicaoListaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicaoListaRepositoryJpa implements MedicaoListaRepository {

    private final MedicaoListaRepositoryJpa medicaoListaRepositoryJpa;

    @Autowired
    public MedicaoListaRepositoryJpa(MedicaoListaRepositoryJpa medicaoListaRepositoryJpa) {
        this.medicaoListaRepositoryJpa = medicaoListaRepositoryJpa;
    }

    @Override
    public List<MedicaoListaModel> buscarTodos() {
        return List.of();
    }

    @Override
    public MedicaoListaModel buscarPorId(int id) {
        return null;
    }

    @Override
    public void adicionar(MedicaoListaModel listaMedicao) {

    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public void atualizar(int id, MedicaoListaModel listaMedicao) {

    }
}
