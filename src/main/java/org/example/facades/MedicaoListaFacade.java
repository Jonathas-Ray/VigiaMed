package org.example.facades;

import org.example.applications.MedicaoListaApplication;
import org.example.entities.MedicaoLista;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicaoListaFacade {
    private final MedicaoListaApplication medicaoListaApplication;

    public MedicaoListaFacade(MedicaoListaApplication medicaoListaApplication) {
        this.medicaoListaApplication = medicaoListaApplication;
    }

    public List<MedicaoLista> buscarTodos() {
        return this.medicaoListaApplication.buscarTodos();
    }

    public MedicaoLista buscarPorId(int id) {
        return this.medicaoListaApplication.buscarPorId(id);
    }

    public MedicaoLista adicionar(MedicaoLista medicaoLista) {
        this.medicaoListaApplication.adicionar(medicaoLista);
        return medicaoLista;
    }

    public void excluir(int id) {
        this.medicaoListaApplication.excluir(id);
    }

    public void atualizar(int id, MedicaoLista medicaoLista) {
        this.medicaoListaApplication.atualizar(id, medicaoLista);
    }
}