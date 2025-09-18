package org.example.applications;

import org.example.interfaces.UsuarioRepository;
import org.example.entities.Usuario;

import java.util.List;

public class UsuarioApplication {
    private UsuarioRepository usuarioRepository;

    public UsuarioApplication(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarTodos() {
        return this.usuarioRepository.buscarTodos();
    }

    public Usuario buscarPorId(int id) {
        return this.usuarioRepository.buscarPorId(id);
    }

    public void adicionar(Usuario usuario) {
        this.usuarioRepository.adicionar(usuario);
    }

    public void excluir(int id) {
        this.usuarioRepository.excluir(id);
    }

    public void atualizar(int id, Usuario usuario) {
        this.usuarioRepository.atualizar(id, usuario);
    }
}
