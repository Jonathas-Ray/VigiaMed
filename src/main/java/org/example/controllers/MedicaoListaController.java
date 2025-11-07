package org.example.controllers;

import org.example.entities.MedicaoLista;
import org.example.facades.MedicaoListaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicao-lista")
public class MedicaoListaController {

    private final MedicaoListaFacade medicaoListaFacade;

    @Autowired
    public MedicaoListaController(MedicaoListaFacade medicaoListaFacade) {
        this.medicaoListaFacade = medicaoListaFacade;
    }

    @GetMapping
    public List<MedicaoLista> getMedicaoListas() {
        return medicaoListaFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicaoLista> getMedicaoLista(@PathVariable int id) {
        MedicaoLista medicaoLista = medicaoListaFacade.buscarPorId(id);
        if (medicaoLista != null) {
            return ResponseEntity.ok(medicaoLista);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarMedicaoLista(@RequestBody MedicaoLista medicaoLista) {
        medicaoListaFacade.adicionar(medicaoLista);
    }

    @PutMapping("/{id}")
    public void atualizarMedicaoLista(@PathVariable int id, @RequestBody MedicaoLista medicaoLista) {
        medicaoListaFacade.atualizar(id, medicaoLista);
    }

    @DeleteMapping("/{id}")
    public void removerMedicaoLista(@PathVariable int id) {
        medicaoListaFacade.excluir(id);
    }
}