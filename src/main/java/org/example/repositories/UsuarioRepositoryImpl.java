package org.example.repositories;

import org.example.entities.Usuario;
import org.example.interfaces.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final List<Usuario> usuarios = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<Usuario> buscarTodos() {
        return usuarios;
    }

    public Usuario buscarPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public void adicionar(Usuario usuario) {
        if (usuario.getId() == 0) {
            usuario.setId(idCounter.getAndIncrement());
        }
        this.usuarios.add(usuario);
    }

    public void excluir(int id) {
        Usuario usuarioParaRemover = null;
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuarioParaRemover = usuario;
                break;
            }
        }
        if (usuarioParaRemover != null) {
            usuarios.remove(usuarioParaRemover);
        }
    }

    public void atualizar(int id, Usuario usuario) {
        Usuario usuarioExistente = buscarPorId(id);
        if (usuarioExistente != null) {
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setTipo(usuario.getTipo());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setSenha(usuario.getSenha());
            usuarioExistente.setUnidade(usuario.getUnidade());
        }
    }
}