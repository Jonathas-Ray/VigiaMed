package org.example.controllers;

import org.example.entities.Sensor;
import org.example.facades.SensorFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {
    private final SensorFacade sensorFacade;

    public SensorController(SensorFacade sensorFacade) {
        this.sensorFacade = sensorFacade;
    }

    @GetMapping
    public List<Sensor> getSensores() {
        return sensorFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensor(@PathVariable int id) {
        Sensor sensor = sensorFacade.buscarPorId(id);
        if (sensor != null) {
            return ResponseEntity.ok(sensor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarSensor(@RequestBody Sensor sensor) {
        sensorFacade.adicionar(sensor);
    }

    @PutMapping("/{id}")
    public void atualizarSensor(@PathVariable int id, @RequestBody Sensor sensor) {
        sensorFacade.atualizar(id, sensor);
    }

    @DeleteMapping("/{id}")
    public void removerSensor(@PathVariable int id) {
        sensorFacade.excluir(id);
    }
}