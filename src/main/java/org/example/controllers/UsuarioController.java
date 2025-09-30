package org.example.controllers;

import org.example.entities.Usuario;
import org.example.facades.UsuarioFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioFacade usuarioFacade;

    public UsuarioController(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable int id) {
        Usuario usuario = usuarioFacade.buscarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarUsuario(@RequestBody Usuario usuario) {
        usuarioFacade.adicionar(usuario);
    }

    @PutMapping("/{id}")
    public void atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        usuarioFacade.atualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void removerUsuario(@PathVariable int id) {
        usuarioFacade.excluir(id);
    }
}