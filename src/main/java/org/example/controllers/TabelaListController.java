package org.example.controllers;



import org.example.entities.TabelaList;
import org.example.facades.TabelaListFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


    @RestController
    @RequestMapping("/api/unidade")
public class TabelaListController {


        private final TabelaListFacade TabelaListFacade;

        public TabelaListController(TabelaListFacade tabelaListFacade) {
            this.TabelaListFacade = tabelaListFacade;
        }

        @GetMapping
        public List<TabelaList> getTabelaLists() {
            return TabelaListFacade.buscarTodos();
        }

        @GetMapping("/{id}")
        public ResponseEntity<TabelaList> getUnidade(@PathVariable int id) {
            TabelaList tabelaList = TabelaListFacade.buscarPorId(id);
            if (tabelaList != null) {
                return ResponseEntity.ok(tabelaList);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public void criarTabelaList(@RequestBody TabelaList tabelaList) {
            TabelaListFacade.adicionar(tabelaList);
        }

        @PutMapping("/{id}")
        public void atualizarTabelaList(@PathVariable int id, @RequestBody TabelaList tabelaList) {
            TabelaListFacade.atualizar(id, tabelaList);
        }

        @DeleteMapping("/{id}")
        public void removerTabelaList(@PathVariable int id) {
            TabelaListFacade.excluir(id);
        }

}
