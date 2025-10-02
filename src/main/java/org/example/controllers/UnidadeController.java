package org.example.controllers;

import org.example.models.UnidadeModel;
import org.example.facades.UnidadeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidade")
public class UnidadeController {

    private final UnidadeFacade unidadeFacade;

    @Autowired
    public UnidadeController(UnidadeFacade unidadeFacade) {
        this.unidadeFacade = unidadeFacade;
    }

    @GetMapping
    public List<UnidadeModel> getUnidades() {
        return unidadeFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeModel> getUnidade(@PathVariable int id) {
        UnidadeModel unidadeModel = unidadeFacade.buscarPorId(id);
        if (unidadeModel != null) {
            return ResponseEntity.ok(unidadeModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarUnidade(@RequestBody UnidadeModel unidadeModel) {
        unidadeFacade.adicionar(unidadeModel);
    }

    @PutMapping("/{id}")
    public void atualizarUnidade(@PathVariable int id, @RequestBody UnidadeModel unidadeModel) {
        unidadeFacade.atualizar(id, unidadeModel);
    }

    @DeleteMapping("/{id}")
    public void removerUnidade(@PathVariable int id) {
        unidadeFacade.excluir(id);
    }
}