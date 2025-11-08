package org.example.controllers;

import org.example.entities.Medicao;
import org.example.facades.MedicaoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicao")
public class MedicaoController {

    private final MedicaoFacade medicaoFacade;

    @Autowired
    public MedicaoController(MedicaoFacade medicaoFacade) {
        this.medicaoFacade = medicaoFacade;
    }

    @GetMapping
    public List<Medicao> getMedicoes() {
        return medicaoFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicao> getMedicao(@PathVariable int id) {
        Medicao medicao = medicaoFacade.buscarPorId(id);
        if (medicao != null) {
            return ResponseEntity.ok(medicao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarMedicao(@RequestBody Medicao medicao) {
        medicaoFacade.adicionar(medicao);
    }

    @PutMapping("/{id}")
    public void atualizarMedicao(@PathVariable int id, @RequestBody Medicao medicao) {
        medicaoFacade.atualizar(id, medicao);
    }

    @DeleteMapping("/{id}")
    public void removerMedicao(@PathVariable int id) {
        medicaoFacade.excluir(id);
    }
}