package org.example.controllers;

import org.example.models.MedicaoListaModel;
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
    public List<MedicaoListaModel> getMedicaoListas() {
        return medicaoListaFacade.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicaoListaModel> getMedicaoLista(@PathVariable int id) {
        MedicaoListaModel medicaoListaModel = medicaoListaFacade.buscarPorId(id);
        if (medicaoListaModel != null) {
            return ResponseEntity.ok(medicaoListaModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public void criarMedicaoLista(@RequestBody MedicaoListaModel medicaoListaModel) {
        medicaoListaFacade.adicionar(medicaoListaModel);
    }

    @PutMapping("/{id}")
    public void atualizarMedicaoLista(@PathVariable int id, @RequestBody MedicaoListaModel medicaoListaModel) {
        medicaoListaFacade.atualizar(id, medicaoListaModel);
    }

    @DeleteMapping("/{id}")
    public void removerMedicaoLista(@PathVariable int id) {
        medicaoListaFacade.excluir(id);
    }
}
