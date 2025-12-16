package org.example.controllers;

import org.example.applications.MedicaoListaApplication;
import org.example.entities.MedicaoLista;
import org.example.facades.MedicaoListaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicao-lista")
@CrossOrigin(origins = "*")
public class MedicaoListaController {

    private final MedicaoListaFacade medicaoListaFacade;

    private final MedicaoListaApplication medicaoListaApplication;

    @Autowired
    public MedicaoListaController(MedicaoListaFacade medicaoListaFacade,
                                  MedicaoListaApplication medicaoListaApplication) {
        this.medicaoListaFacade = medicaoListaFacade;
        this.medicaoListaApplication = medicaoListaApplication;
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
    public ResponseEntity<MedicaoLista> criarMedicaoLista(@RequestBody MedicaoLista medicaoLista) {
        // 1. Assumindo que medicaoListaFacade.adicionar() retorna o objeto salvo com o ID.
        MedicaoLista novaMedicaoLista = medicaoListaFacade.adicionar(medicaoLista);

        // 2. Retorna a resposta 201 Created com o objeto JSON no corpo.
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMedicaoLista);
    }

    @PutMapping("/{id}")
    public void atualizarMedicaoLista(@PathVariable int id, @RequestBody MedicaoLista medicaoLista) {
        medicaoListaFacade.atualizar(id, medicaoLista);
    }

    @DeleteMapping("/{id}")
    public void removerMedicaoLista(@PathVariable int id) {
        medicaoListaFacade.excluir(id);
    }

    @GetMapping("/monitorar")
    public ResponseEntity<MedicaoListaApplication.ResultadoValidacao> monitorar() {
        MedicaoListaApplication.ResultadoValidacao resultado =
                medicaoListaApplication.verificarUltimoResultado();
        return ResponseEntity.ok(resultado);
    }
}