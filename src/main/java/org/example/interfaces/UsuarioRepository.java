package org.example.interfaces;

import org.example.entities.Usuario;
import java.util.List;

public interface UsuarioRepository {
    List<Usuario> buscarTodos();
    Usuario buscarPorId(int id);
    void adicionar(Usuario usuario);
    void excluir(int id);
    void atualizar(int id, Usuario usuario);
}