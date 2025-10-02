package org.example.repositories;

import org.example.models.UsuarioModel;
import org.example.interfaces.UsuarioRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final List<UsuarioModel> UsuarioModels = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<UsuarioModel> buscarTodos() {
        return UsuarioModels;
    }

    public UsuarioModel buscarPorId(int id) {
        for (UsuarioModel UsuarioModel : UsuarioModels) {
            if (UsuarioModel.getId() == id) {
                return UsuarioModel;
            }
        }
        return null;
    }

    public void adicionar(UsuarioModel UsuarioModel) {
        if (UsuarioModel.getId() == 0) {
            UsuarioModel.setId(idCounter.getAndIncrement());
        }
        this.UsuarioModels.add(UsuarioModel);
    }

    public void excluir(int id) {
        UsuarioModel UsuarioParaRemover = null;
        for (UsuarioModel Usuario : UsuarioModels) {
            if (Usuario.getId() == id) {
                UsuarioParaRemover = Usuario;
                break;
            }
        }
        if (UsuarioParaRemover != null) {
            UsuarioModels.remove(UsuarioParaRemover);
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