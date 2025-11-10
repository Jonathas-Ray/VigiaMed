package org.example.controllers;

import org.example.models.TabelaListModel;
import org.example.facades.TabelaListFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tabela-list")
public class TabelaListController {

    private final TabelaListFacade tabelaListFacade;

    @Autowired
    public TabelaListController(TabelaListFacade tabelaListFacade) {
        this.tabelaListFacade = tabelaListFacade;
    }

    @GetMapping
    public List<TabelaListModel> getTabelaLists() {
        return tabelaListFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TabelaListModel> getTabelaList(@PathVariable int id) {
        TabelaListModel tabelaListModel = tabelaListFacade.buscarPorId(id);
        if (tabelaListModel != null) {
            return ResponseEntity.ok(tabelaListModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarTabelaList(@RequestBody TabelaListModel tabelaListModel) {
        tabelaListFacade.adicionar(tabelaListModel);
    }

    @PutMapping("/{id}")
    public void atualizarTabelaList(@PathVariable int id, @RequestBody TabelaListModel tabelaListModel) {
        tabelaListFacade.atualizar(id, tabelaListModel);
    }

    @DeleteMapping("/{id}")
    public void removerTabelaList(@PathVariable int id) {
        tabelaListFacade.excluir(id);
    }
}
