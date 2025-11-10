package org.example.controllers;

import org.example.models.SensorModel;
import org.example.facades.SensorFacade;
<<<<<<< HEAD
import org.example.models.SensorModel;
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    private final SensorFacade sensorFacade;

<<<<<<< HEAD
    public SensorController(SensorFacade sensorFacade) { this.sensorFacade = sensorFacade; }
=======
    @Autowired
    public SensorController(SensorFacade sensorFacade) {
        this.sensorFacade = sensorFacade;
    }
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

    @GetMapping
    public List<SensorModel> getSensores() {
        return sensorFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorModel> getSensor(@PathVariable int id) {
<<<<<<< HEAD
        SensorModel sensor = sensorFacade.buscarPorId(id);
        if (sensor != null) {
            return ResponseEntity.ok(sensor);
=======
        SensorModel sensorModel = sensorFacade.buscarPorId(id);
        if (sensorModel != null) {
            return ResponseEntity.ok(sensorModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
<<<<<<< HEAD
    public void criarSensor(@RequestBody SensorModel sensor) {
        sensorFacade.adicionar(sensor);
    }

    @PutMapping("/{id}")
    public void atualizarSensor(@PathVariable int id, @RequestBody SensorModel sensor) {
        sensorFacade.atualizar(id, sensor);
=======
    public void criarSensor(@RequestBody SensorModel sensorModel) {
        sensorFacade.adicionar(sensorModel);
    }

    @PutMapping("/{id}")
    public void atualizarSensor(@PathVariable int id, @RequestBody SensorModel sensorModel) {
        sensorFacade.atualizar(id, sensorModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    @DeleteMapping("/{id}")
    public void removerSensor(@PathVariable int id) {
        sensorFacade.excluir(id);
    }
}
