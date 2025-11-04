package org.example.facades;


import org.example.applications.MedicaoListaApplication;
import org.example.models.MedicaoListaModel;

import java.util.List;

public class MedicaoListaFacade {
    private final MedicaoListaApplication medicaoListaApplication;

    public MedicaoListaFacade(MedicaoListaApplication medicaoListaApplication) {
        this.medicaoListaApplication = medicaoListaApplication;
    }

    public List<MedicaoListaModel> buscarTodos() {
        return this.medicaoListaApplication.buscarTodos();
    }

//    public MedicaoListaModel buscarPorId(long id) { return this.medicaoListaApplication.buscarPorId(id); }

    public void adicionar(MedicaoListaModel medicaoLista) {
        this.medicaoListaApplication.inserir(medicaoLista);
    }

//    public void excluir(long id) { this.medicaoListaApplication.excluir(id); }

//    public void atualizar(long id, MedicaoListaModel medicaoLista) { this.medicaoListaApplication.atualizar(id, medicaoLista); }
}
