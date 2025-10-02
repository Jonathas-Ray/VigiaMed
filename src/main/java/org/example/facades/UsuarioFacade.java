package org.example.facades;

import org.example.applications.UsuarioApplication;
import org.example.models.UsuarioModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioFacade {
    private final UsuarioApplication usuarioApplication;

    public UsuarioFacade(UsuarioApplication usuarioApplication) {
        this.usuarioApplication = usuarioApplication;
    }

    public List<UsuarioModel> buscarTodos() {
        return this.usuarioApplication.buscarTodos();
    }

    public UsuarioModel buscarPorId(int id) {
        return this.usuarioApplication.buscarPorId(id);
    }

    public void adicionar(UsuarioModel usuarioModel) {
        this.usuarioApplication.adicionar(usuarioModel);
    }

    public void excluir(int id) {
        this.usuarioApplication.excluir(id);
    }

    public void atualizar(int id, UsuarioModel usuarioModel) {
        this.usuarioApplication.atualizar(id, usuarioModel);
    }
}