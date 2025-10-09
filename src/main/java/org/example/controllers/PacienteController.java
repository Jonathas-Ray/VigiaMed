package org.example.controllers;

import org.example.models.PacienteModel;
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
    public List<PacienteModel> getPacientes() {
        return pacienteFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteModel> getPaciente(@PathVariable int id) {
        PacienteModel pacienteModel = pacienteFacade.buscarPorId(id);
        if (pacienteModel != null) {
            return ResponseEntity.ok(pacienteModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarPaciente(@RequestBody PacienteModel pacienteModel) {
        pacienteFacade.adicionar(pacienteModel);
    }

    @PutMapping("/{id}")
    public void atualizarPaciente(@PathVariable int id, @RequestBody PacienteModel pacienteModel) {
        pacienteFacade.atualizar(id, pacienteModel);
    }

    @DeleteMapping("/{id}")
    public void removerPaciente(@PathVariable int id) {
        pacienteFacade.excluir(id);
    }
}