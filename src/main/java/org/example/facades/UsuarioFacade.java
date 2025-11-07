package org.example.facades;

import org.example.applications.UsuarioApplication;
import org.example.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioFacade {
    private final UsuarioApplication usuarioApplication;

    public UsuarioFacade(UsuarioApplication usuarioApplication) {
        this.usuarioApplication = usuarioApplication;
    }

    public List<Usuario> buscarTodos() {
        return this.usuarioApplication.buscarTodos();
    }

    public Usuario buscarPorId(int id) {
        return this.usuarioApplication.buscarPorId(id);
    }

    public void adicionar(Usuario usuario) {
        this.usuarioApplication.adicionar(usuario);
    }

    public void excluir(int id) {
        this.usuarioApplication.excluir(id);
    }

    public void atualizar(int id, Usuario usuario) {
        this.usuarioApplication.atualizar(id, usuario);
    }
}