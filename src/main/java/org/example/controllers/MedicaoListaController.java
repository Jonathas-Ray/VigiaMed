package org.example.controllers;

import org.example.entities.MedicaoLista;
import org.example.facades.MedicaoListaFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MedicaoListaController {

        private final MedicaoListaFacade medicaoListaFacade;

        public MedicaoListaController(MedicaoListaFacade medicaoListaFacade) {
            this.medicaoListaFacade = medicaoListaFacade;
        }

        @GetMapping
        public List<MedicaoLista> getMedicaoListas() {
            return medicaoListaFacade.buscarTodos();
        }

        @GetMapping("/{id}")
        public ResponseEntity<MedicaoLista> getMedicao(@PathVariable int id) {
            MedicaoLista medicaoLista = medicaoListaFacade.buscarPorId(id);
            if (medicaoLista != null) {
                return ResponseEntity.ok(medicaoLista);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping
        public void criaMedicaoLista(@RequestBody MedicaoLista medicaoLista) {
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
