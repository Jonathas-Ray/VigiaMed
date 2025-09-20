package org.example.controllers;

import org.example.entities.Usuario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final List<Usuario> usuarioList = new ArrayList<>();

    @GetMapping("/")
    public List<Usuario> getUsuarios(){
        return this.usuarioList;
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable int id) {
        return usuarioList
                .stream()
                .filter(c -> c.getId() == id)
                .findFirst().get();
    }

    @PostMapping("/")
    public void salvarUsuario(@RequestBody Usuario usuario) {
        this.usuarioList.add(usuario);
    }

    @PutMapping("/{id}")
    public void editarUsuario(@PathVariable int id,
                              @RequestBody Usuario usuario) {
        for (int i = 0; i < usuarioList.size(); i++) {
            /*Comentários Desnecessários:
            Honestamente, cabia aqui tentar implementar aquela lógica do Divide and Conquer do Merge Sort/Binary Search
            já que Id é naturalmente crescente
            */
            if (usuarioList.get(i).getId() == id) {
                // Especifica o ID do novo objeto Usuario para o ID que você queria procurar na List
                usuario.setId(id);
                // Para só então alterar a List colocando o novo objeto no lugar do anterior
                usuarioList.set(i, usuario);
                break;
            }
        }
    }

    @DeleteMapping("/{id}")
    public void removerPessoa(@PathVariable int Id) {
        usuarioList.removeIf(c -> c.getId() == Id);
    }
}

