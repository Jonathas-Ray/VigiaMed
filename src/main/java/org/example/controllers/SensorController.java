package org.example.controllers;

import org.example.models.SensorModel;
import org.example.facades.SensorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    private final SensorFacade sensorFacade;

    @Autowired
    public SensorController(SensorFacade sensorFacade) {
        this.sensorFacade = sensorFacade;
    }

    @GetMapping
    public List<SensorModel> getSensores() {
        return sensorFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorModel> getSensor(@PathVariable int id) {
        SensorModel sensorModel = sensorFacade.buscarPorId(id);
        if (sensorModel != null) {
            return ResponseEntity.ok(sensorModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarSensor(@RequestBody SensorModel sensorModel) {
        sensorFacade.adicionar(sensorModel);
    }

    @PutMapping("/{id}")
    public void atualizarSensor(@PathVariable int id, @RequestBody SensorModel sensorModel) {
        sensorFacade.atualizar(id, sensorModel);
    }

    @DeleteMapping("/{id}")
    public void removerSensor(@PathVariable int id) {
        sensorFacade.excluir(id);
    }
}
