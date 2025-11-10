package org.example.controllers;

import org.example.entities.StatusDispositivo;
import org.example.facades.StatusDispositivoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statusDispositivo")
public class StatusDispositivoController {
    private final StatusDispositivoFacade statusDispositivoFacade;

    @Autowired
    public StatusDispositivoController(StatusDispositivoFacade statusDispositivoFacade){
        this.statusDispositivoFacade = statusDispositivoFacade;
    }

    @GetMapping
    public List<StatusDispositivo> getStatusDispositivo(){
        return statusDispositivoFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDispositivo> getStatusDispositivo(@PathVariable int id){
        StatusDispositivo statusDispositivo = statusDispositivoFacade.buscarPorId(id);
        if(statusDispositivo != null){
            return ResponseEntity.ok(statusDispositivo);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarStatusDispositivo(@RequestBody StatusDispositivo statusDispositivo){
        statusDispositivoFacade.adicionar(statusDispositivo);
    }

    @PutMapping("/{id}")
    public void atualizarStatusDispositivo(@PathVariable int id, @RequestBody StatusDispositivo statusDispositivo){
        statusDispositivoFacade.atualizar(id, statusDispositivo);
    }

    @DeleteMapping("/{id}")
    public void removerStatusDispositivo(@PathVariable int id){
        statusDispositivoFacade.excluir(id);
    }
}