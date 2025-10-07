package org.example.controllers;


import org.example.models.MedicaoModel;
import org.example.facades.MedicaoFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MedicaoController {
        private final MedicaoFacade medicaoFacade;

        public MedicaoController(MedicaoFacade medicaoFacade) {
            this.medicaoFacade = medicaoFacade;
        }

        @GetMapping
        public List<MedicaoModel> getMedicoes() {
            return medicaoFacade.buscarTodos();
        }

        @GetMapping("/{id}")
        public ResponseEntity<MedicaoModel> getMedicao(@PathVariable int id) {
            MedicaoModel medicaoModel = medicaoFacade.buscarPorId(id);
            if (medicaoModel != null) {
                return ResponseEntity.ok(medicaoModel);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public void criaMedicao(@RequestBody MedicaoModel medicaoModel) {
            medicaoFacade.adicionar(medicaoModel);
        }

        @PutMapping("/{id}")
        public void atualizarMedicao(@PathVariable int id, @RequestBody MedicaoModel medicaoModel) {
            medicaoFacade.atualizar(id, medicaoModel);
        }

        @DeleteMapping("/{id}")
        public void removerMedicao(@PathVariable int id) {
            medicaoFacade.excluir(id);
        }

}
