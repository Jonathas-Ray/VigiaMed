package org.example.controllers;

import org.example.models.TabelaListModel;
import org.example.facades.TabelaListFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/api/unidade")
public class TabelaListController {


    private final TabelaListFacade TabelaListFacade;

    @Autowired
    public TabelaListController(TabelaListFacade tabelaListFacade) {
        this.TabelaListFacade = tabelaListFacade;
    }

    @GetMapping
    public List<TabelaListModel> getTabelaLists() {
        return TabelaListFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TabelaListModel> getUnidade(@PathVariable int id) {
        TabelaListModel tabelaListModel = TabelaListFacade.buscarPorId(id);
        if (tabelaListModel != null) {
            return ResponseEntity.ok(tabelaListModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarTabelaList(@RequestBody TabelaListModel tabelaListModel) {
        TabelaListFacade.adicionar(tabelaListModel);
    }

    @PutMapping("/{id}")
    public void atualizarTabelaList(@PathVariable int id, @RequestBody TabelaListModel tabelaListModel) {
        TabelaListFacade.atualizar(id, tabelaListModel);
    }

    @DeleteMapping("/{id}")
    public void removerTabelaList(@PathVariable int id) {
            TabelaListFacade.excluir(id);
        }

}
