package org.example.applications;

import org.example.interfaces.UsuarioRepository;
import org.example.entities.Usuario;
import org.example.models.UsuarioModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioApplication {
    private final UsuarioRepository usuarioRepository;

    public UsuarioApplication(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioModel> buscarTodos() {
        return this.usuarioRepository.buscarTodos();
    }

    public UsuarioModel buscarPorId(int id) {
        return this.usuarioRepository.buscarPorId(id);
    }

    public void adicionar(UsuarioModel usuarioModel) {
        this.usuarioRepository.adicionar(usuarioModel);
    }

    public void excluir(int id) {
        this.usuarioRepository.excluir(id);
    }

    public void atualizar(int id, UsuarioModel usuarioModel) {
        this.usuarioRepository.atualizar(id, usuarioModel);
    }
}