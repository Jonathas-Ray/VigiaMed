package org.example.repositories;

import org.example.entities.Usuario;
import org.example.interfaces.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> buscarTodos() {
        return usuarios;
    }

    public Usuario buscarPorId(int id) {
        return usuarios
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void excluir(int id) {
        this.usuarios.removeIf(l -> l.getId() == id);
    }

    public void atualizar(int id, Usuario usuario) {
        Usuario usuarioInMemory = buscarPorId(id);

        usuarioInMemory.setNome(usuario.getNome());
        usuarioInMemory.setTipo(usuario.getTipo());
        usuarioInMemory.setUnidade(usuario.getUnidade());
        usuarioInMemory.setEmail(usuario.getEmail());
        usuarioInMemory.setSenha(usuario.getSenha());
    }
}

