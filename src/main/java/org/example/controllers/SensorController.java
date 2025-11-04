package org.example.controllers;

import org.example.entities.Sensor;
import org.example.facades.SensorFacade;
import org.example.models.SensorModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {
    private final SensorFacade sensorFacade;

    public SensorController(SensorFacade sensorFacade) { this.sensorFacade = sensorFacade; }

    @GetMapping
    public List<SensorModel> getSensores() {
        return sensorFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorModel> getSensor(@PathVariable int id) {
        SensorModel sensor = sensorFacade.buscarPorId(id);
        if (sensor != null) {
            return ResponseEntity.ok(sensor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarSensor(@RequestBody SensorModel sensor) {
        sensorFacade.adicionar(sensor);
    }

    @PutMapping("/{id}")
    public void atualizarSensor(@PathVariable int id, @RequestBody SensorModel sensor) {
        sensorFacade.atualizar(id, sensor);
    }

    @DeleteMapping("/{id}")
    public void removerSensor(@PathVariable int id) {
        sensorFacade.excluir(id);
    }
}