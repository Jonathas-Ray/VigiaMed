package org.example.controllers;

import org.example.entities.TabelaList;
import org.example.facades.TabelaListFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tabelaList")
public class TabelaListController {

    private final TabelaListFacade tabelaListFacade;

    @Autowired
    public TabelaListController(TabelaListFacade tabelaListFacade) {
        this.tabelaListFacade = tabelaListFacade;
    }

    @GetMapping
    public List<TabelaList> getTabelaLists() {
        return tabelaListFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TabelaList> getTabelaList(@PathVariable int id) {
        TabelaList tabelaList = tabelaListFacade.buscarPorId(id);
        if (tabelaList != null) {
            return ResponseEntity.ok(tabelaList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarTabelaList(@RequestBody TabelaList tabelaList) {
        tabelaListFacade.adicionar(tabelaList);
    }

    @PutMapping("/{id}")
    public void atualizarTabelaList(@PathVariable int id, @RequestBody TabelaList tabelaList) {
        tabelaListFacade.atualizar(id, tabelaList);
    }

    @DeleteMapping("/{id}")
    public void removerTabelaList(@PathVariable int id) {
        tabelaListFacade.excluir(id);
    }
}