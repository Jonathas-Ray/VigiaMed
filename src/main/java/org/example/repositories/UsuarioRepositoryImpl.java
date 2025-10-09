package org.example.repositories;

import org.example.models.UsuarioModel;
import org.example.interfaces.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final List<UsuarioModel> usuarioModels = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<UsuarioModel> buscarTodos() {
        return usuarioModels;
    }

    public UsuarioModel buscarPorId(int id) {
        for (UsuarioModel usuarioModel : usuarioModels) {
            if (usuarioModel.getId() == id) {
                return usuarioModel;
            }
        }
        return null;
    }

    public void adicionar(UsuarioModel usuarioModel) {
        if (usuarioModel.getId() == 0) {
            usuarioModel.setId(idCounter.getAndIncrement());
        }
        this.usuarioModels.add(usuarioModel);
    }

    public void excluir(int id) {
        UsuarioModel usuarioParaRemover = null;
        for (UsuarioModel usuario : usuarioModels) {
            if (usuario.getId() == id) {
                usuarioParaRemover = usuario;
                break;
            }
        }
        if (usuarioParaRemover != null) {
            usuarioModels.remove(usuarioParaRemover);
        }
    }

    public void atualizar(int id, UsuarioModel usuarioModel) {
        UsuarioModel usuarioModelExistente = buscarPorId(id);
        if (usuarioModelExistente != null) {
            usuarioModelExistente.setNome(usuarioModel.getNome());
            usuarioModelExistente.setTipo(usuarioModel.getTipo());
            usuarioModelExistente.setEmail(usuarioModel.getEmail());
            usuarioModelExistente.setSenha(usuarioModel.getSenha());
            usuarioModelExistente.setUnidade(usuarioModel.getUnidade());
        }
    }
}