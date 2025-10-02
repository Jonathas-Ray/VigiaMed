package org.example.controllers;

import org.example.models.UsuarioModel;
import org.example.facades.UsuarioFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioFacade usuarioFacade;

    @Autowired
    public UsuarioController(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    @GetMapping
    public List<UsuarioModel> getUsuarios() {
        return usuarioFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> getUsuario(@PathVariable int id) {
        UsuarioModel usuarioModel = usuarioFacade.buscarPorId(id);
        if (usuarioModel != null) {
            return ResponseEntity.ok(usuarioModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarUsuario(@RequestBody UsuarioModel usuarioModel) {
        usuarioFacade.adicionar(usuarioModel);
    }

    @PutMapping("/{id}")
    public void atualizarUsuario(@PathVariable int id, @RequestBody UsuarioModel usuarioModel) {
        usuarioFacade.atualizar(id, usuarioModel);
    }

    @DeleteMapping("/{id}")
    public void removerUsuario(@PathVariable int id) {
        usuarioFacade.excluir(id);
    }
}