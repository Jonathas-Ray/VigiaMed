package org.example.controllers;

import org.example.entities.Paciente;
import org.example.facades.PacienteFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    private final PacienteFacade pacienteFacade;

    @Autowired
    public PacienteController(PacienteFacade pacienteFacade) {
        this.pacienteFacade = pacienteFacade;
    }

    @GetMapping
    public List<Paciente> getPacientes() {
        return pacienteFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPaciente(@PathVariable int id) {
        Paciente paciente = pacienteFacade.buscarPorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarPaciente(@RequestBody Paciente paciente) {
        pacienteFacade.adicionar(paciente);
    }

    @PutMapping("/{id}")
    public void atualizarPaciente(@PathVariable int id, @RequestBody Paciente paciente) {
        pacienteFacade.atualizar(id, paciente);
    }

    @DeleteMapping("/{id}")
    public void removerPaciente(@PathVariable int id) {
        pacienteFacade.excluir(id);
    }
}