package org.example.controllers;

import org.example.entities.Sensor;
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
    public List<Sensor> getSensores() {
        return sensorFacade.buscarTodos();
    }

    @GetMapping("/{id}")
<<<<<<< HEAD
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
=======
    public ResponseEntity<Sensor> getSensor(@PathVariable int id) {
        Sensor sensor = sensorFacade.buscarPorId(id);
        if (sensor != null) {
            return ResponseEntity.ok(sensor);
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
<<<<<<< HEAD
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
=======
    public void criarSensor(@RequestBody Sensor sensor) {
        sensorFacade.adicionar(sensor);
    }

    @PutMapping("/{id}")
    public void atualizarSensor(@PathVariable int id, @RequestBody Sensor sensor) {
        sensorFacade.atualizar(id, sensor);
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
    }

    @DeleteMapping("/{id}")
    public void removerSensor(@PathVariable int id) {
        sensorFacade.excluir(id);
    }
}