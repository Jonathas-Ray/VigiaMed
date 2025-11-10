package org.example.controllers;

import org.example.entities.StatusDispositivo;
import org.example.facades.StatusDispositivoFacade;
import org.example.models.StatusDispositivoModel;
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
    public List<StatusDispositivoModel> getStatusDispositivo(){
        return statusDispositivoFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDispositivoModel> getStatusDispositivo(@PathVariable int id){
        StatusDispositivoModel statusDispositivoModel = statusDispositivoFacade.buscarPorId(id);
        if(statusDispositivoModel != null){
            return ResponseEntity.ok(statusDispositivoModel);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarStatusDispositivo(@RequestBody StatusDispositivoModel statusDispositivoModel){
        statusDispositivoFacade.adicionar(statusDispositivoModel);
    }

    @PutMapping("/{id}")
    public void atualizarStatusDispositivo(@PathVariable int id, @RequestBody StatusDispositivoModel statusDispositivoModel){
        statusDispositivoFacade.atualizar(id, statusDispositivoModel);
    }

    @DeleteMapping("/{id}")
    public void removerStatusDispositivo(@PathVariable int id){
        statusDispositivoFacade.excluir(id);
    }
}
