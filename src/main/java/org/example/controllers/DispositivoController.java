package org.example.controllers;

import org.example.entities.Dispositivo;
import org.example.facades.DispositivoFacade;
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
    public List<Dispositivo> getDispositivo(){
        return dispositivoFacade.buscarTodos();
    }

    @GetMapping("{id}")
    public ResponseEntity<Dispositivo> GetDispositivo(@PathVariable int id){
        Dispositivo dispositivo = dispositivoFacade.buscarPorId(id);
        if (dispositivo != null){
            return ResponseEntity.ok(dispositivo);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarDispositivo(@RequestBody Dispositivo dispositivo){
        dispositivoFacade.adicionar(dispositivo);
    }

    @DeleteMapping("/{id}")
    public void removerDispoitivo(@PathVariable int id){
        dispositivoFacade.excluir(id);
    }
}

