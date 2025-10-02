package org.example.controllers;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import org.example.entities.Dispositivo;
import org.example.facades.DispositivoFacade;
import org.example.models.DispositivoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Dispositivo")
public class DispositivoController{

    private final DispositivoFacade dispositivoFacade;

    @Autowired
    public DispositivoController(DispositivoFacade dispositivoFacade){
        this.dispositivoFacade = dispositivoFacade;
    }

    @GetMapping
    public List<DispositivoModel> getDispositivo(){
        return dispositivoFacade.buscarTodos();
    }

    @GetMapping("{id}")
    public ResponseEntity<DispositivoModel> GetDispositivo(@PathVariable int id){
        DispositivoModel dispositivoModel = dispositivoFacade.buscarPorId(id);
        if (dispositivoModel != null){
            return ResponseEntity.ok(dispositivoModel);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarDispositivo(@RequestBody DispositivoModel dispositivoModel){
        dispositivoFacade.adicionar(dispositivoModel);
    }

    @DeleteMapping("/{id}")
    public void removerDispoitivo(@PathVariable int id){
        dispositivoFacade.excluir(id);
    }

    @PutMapping("/{id}")
    public void atualizarDispositivo(@PathVariable int id, @RequestBody DispositivoModel dispositivoModel){
        dispositivoFacade.atualizar(id, dispositivoModel);
    }
}

