package org.example.interfaces;

import org.example.entities.Usuario;
import org.example.models.UsuarioModel;

import java.util.List;

public interface UsuarioRepository {
    List<UsuarioModel> buscarTodos();
    UsuarioModel buscarPorId(int id);
    void adicionar(UsuarioModel usuarioModel);
    void excluir(int id);
    void atualizar(int id, UsuarioModel usuarioModel);
}