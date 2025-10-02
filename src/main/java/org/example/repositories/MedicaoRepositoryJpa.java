package org.example.repositories;

import org.example.interfaces.MedicaoModelRepositoryJpa;
import org.example.interfaces.MedicaoRepository;
import org.example.interfaces.UsuarioModelRepositoryJpa;
import org.example.models.MedicaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicaoRepositoryJpa implements MedicaoRepository {

    private final UsuarioModelRepositoryJpa usuarioModelRepositoryJpa;

    @Autowired
    public MedicaoRepositoryJpa(UsuarioModelRepositoryJpa usuarioModelRepositoryJpa) {
        this.usuarioModelRepositoryJpa = usuarioModelRepositoryJpa;
    }

    @Override
    public List<MedicaoModel> buscarTodos() {
        return List.of();
    }

    @Override
    public MedicaoModel buscarPorId(int id) {
        return null;
    }

    @Override
    public void adicionar(MedicaoModel medicao) {

    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public void atualizar(int id, MedicaoModel medicao) {

    }
}
