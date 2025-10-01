package org.example.repositories;

import org.example.entities.Usuario;
import org.example.interfaces.UsuarioModelRepositoryJpa;
import org.example.interfaces.UsuarioRepository;
import org.example.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepositoryJpa implements UsuarioRepository {

    private final UsuarioModelRepositoryJpa usuarioModelRepositoryJpa;

    @Autowired
    public UsuarioRepositoryJpa(UsuarioModelRepositoryJpa usuarioModelRepositoryJpa){
        this.usuarioModelRepositoryJpa = usuarioModelRepositoryJpa;
    }

    @Override
    public List<UsuarioModel> buscarTodos() {
        return this.usuarioModelRepositoryJpa.findAll();
    }

    @Override
    public UsuarioModel buscarPorId(int id) {
        return this.usuarioModelRepositoryJpa.findById(id).get();
    }

    @Override
    public void adicionar(Usuario usuario) {

    }

    @Override
    public void adicionar(UsuarioModel usuarioModel) {
        this.usuarioModelRepositoryJpa.save(usuarioModel);
    }

    @Override
    public void excluir(int id) {
        this.usuarioModelRepositoryJpa.deleteById(id);
    }

    @Override
    public void atualizar(int id, Usuario usuario) {

    }

    @Override
    public void atualizar(int id, UsuarioModel usuarioModel) {
        usuarioModel.setId(id);
        this.usuarioModelRepositoryJpa.save(usuarioModel);
    }
}
