package org.example.repositories;

import org.example.interfaces.MedicaoModelRepositoryJpa;
import org.example.interfaces.MedicaoRepository;
import org.example.interfaces.UsuarioModelRepositoryJpa;
import org.example.models.MedicaoModel;

import java.util.List;

public class MedicaoRepositoryJpa implements MedicaoRepository {

    private final UsuarioModelRepositoryJpa usuarioModelRepositoryJpa;

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
