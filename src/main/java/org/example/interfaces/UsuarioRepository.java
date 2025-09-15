package org.example.interfaces;

import org.example.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {
    public List<Usuario>buscarTodos();
    public Usuario buscarPorId(int id);
    public void adicionar(Usuario usuario);
    public void excluir(int id);
    public void atualizar(int id, Usuario usuario);
}