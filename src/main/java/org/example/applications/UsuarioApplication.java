package org.example.applications;

import org.example.interfaces.UsuarioRepository;
import org.example.entities.Usuario;
import org.example.models.UsuarioModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioApplication {
    private final UsuarioRepository usuarioRepository;

    public UsuarioApplication(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> buscarTodos() {
        List<UsuarioModel> modelList = this.usuarioRepository.buscarTodos();
        List<Usuario> entitieList = new ArrayList<>();
        for(UsuarioModel usuarioModel : modelList) {
            entitieList.add(new Usuario().fromModel(usuarioModel));
        }
        return entitieList;
    }

    public Usuario buscarPorId(int id) {
        Usuario usuario = new Usuario().fromModel(this.usuarioRepository.buscarPorId(id));
        return usuario;
    }

    public void adicionar(Usuario usuario) {
        UsuarioModel usuarioModel = usuario.toModel();
        this.usuarioRepository.adicionar(usuarioModel);
    }

    public void excluir(int id) {
        this.usuarioRepository.excluir(id);
    }

    public void atualizar(int id, Usuario usuario){
        UsuarioModel usuarioModel = usuario.toModel();
        this.usuarioRepository.atualizar(id, usuarioModel);
    }
}