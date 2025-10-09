package org.example.controllers;


import org.example.models.LogModel;
import org.example.facades.LogFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/unidade")
public class LogController {

    private final LogFacade logFacade;

    @Autowired
    public LogController(LogFacade logFacade) {
        this.logFacade = logFacade;
    }

    @GetMapping
    public List<LogModel> getLogs() {
        return logFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogModel> getUnidade(@PathVariable int id) {
        LogModel logModel = logFacade.buscarPorId(id);
        if (logModel != null) {
            return ResponseEntity.ok(logModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarLog(@RequestBody LogModel logModel) {
        logFacade.adicionar(logModel);
    }

    @PutMapping("/{id}")
    public void atualizarLog(@PathVariable int id, @RequestBody LogModel logModel) {
        logFacade.atualizar(id, logModel);
    }

    @DeleteMapping("/{id}")
    public void removerLog(@PathVariable int id) {
            logFacade.excluir(id);
        }
}
