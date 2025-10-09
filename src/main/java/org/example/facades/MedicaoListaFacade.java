package org.example.facades;


import org.example.applications.MedicaoListaApplication;
import org.example.models.MedicaoListaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicaoListaFacade {
    private final MedicaoListaApplication medicaoListaApplication;

    public MedicaoListaFacade(MedicaoListaApplication medicaoListaApplication) {
        this.medicaoListaApplication = medicaoListaApplication;
    }

    public List<MedicaoListaModel> buscarTodos() {
        return this.medicaoListaApplication.buscarTodos();
    }

    public MedicaoListaModel buscarPorId(int id) {
        return this.medicaoListaApplication.buscarPorId(id);
    }

    public void adicionar(MedicaoListaModel medicaoListaModel) {
        this.medicaoListaApplication.inserir(medicaoListaModel);
    }

    public void excluir(int id) {
        this.medicaoListaApplication.excluir(id);
    }

    public void atualizar(int id, MedicaoListaModel medicaoListaModel) {
        this.medicaoListaApplication.atualizar(id, medicaoListaModel);
    }
}
