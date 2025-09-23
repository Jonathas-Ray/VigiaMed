package org.example.controllers;


import org.example.entities.Log;
import org.example.facades.LogFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/unidade")
public class LogController {

        private final LogFacade logFacade;

        public LogController(LogFacade logFacade) {
            this.logFacade = logFacade;
        }

        @GetMapping
        public List<Log> getLogs() {
            return logFacade.buscarTodos();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Log> getUnidade(@PathVariable int id) {
            Log log = logFacade.buscarPorId(id);
            if (log != null) {
                return ResponseEntity.ok(log);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public void criarLog(@RequestBody Log log) {
            logFacade.adicionar(log);
        }

        @PutMapping("/{id}")
        public void atualizarLog(@PathVariable int id, @RequestBody Log log) {
            logFacade.atualizar(id, log);
        }

        @DeleteMapping("/{id}")
        public void removerLog(@PathVariable int id) {
            logFacade.excluir(id);
        }
}
