package org.example.facades;


import org.example.applications.MedicaoListaApplication;
import org.example.entities.MedicaoLista;

import java.util.List;

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

    public void adicionar(MedicaoLista medicaoLista) {
        this.medicaoListaApplication.inserir(medicaoLista);
    }

    public void excluir(int id) {
        this.medicaoListaApplication.excluir(id);
    }

    public void atualizar(int id, MedicaoLista medicaoLista) {
        this.medicaoListaApplication.atualizar(id, medicaoLista);
    }
}
