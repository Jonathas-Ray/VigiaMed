package org.example.controllers;

import org.example.entities.Unidade;
import org.example.facades.UnidadeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidade")
public class UnidadeController {

    private final UnidadeFacade unidadeFacade;

    public UnidadeController(UnidadeFacade unidadeFacade) {
        this.unidadeFacade = unidadeFacade;
    }

    @GetMapping
    public List<Unidade> getUnidades() {
        return unidadeFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> getUnidade(@PathVariable int id) {
        Unidade unidade = unidadeFacade.buscarPorId(id);
        if (unidade != null) {
            return ResponseEntity.ok(unidade);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarUnidade(@RequestBody Unidade unidade) {
        unidadeFacade.adicionar(unidade);
    }

    @PutMapping("/{id}")
    public void atualizarUnidade(@PathVariable int id, @RequestBody Unidade unidade) {
        unidadeFacade.atualizar(id, unidade);
    }

    @DeleteMapping("/{id}")
    public void removerUnidade(@PathVariable int id) {
        unidadeFacade.excluir(id);
    }
}