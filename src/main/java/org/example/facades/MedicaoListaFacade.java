package org.example.facades;

import org.example.applications.MedicaoListaApplication;
import org.example.models.MedicaoListaModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

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

<<<<<<< HEAD
//    public MedicaoListaModel buscarPorId(long id) { return this.medicaoListaApplication.buscarPorId(id); }

    public void adicionar(MedicaoListaModel medicaoLista) {
        this.medicaoListaApplication.inserir(medicaoLista);
=======
    public MedicaoListaModel buscarPorId(int id) {
        return this.medicaoListaApplication.buscarPorId(id);
    }

    public void adicionar(MedicaoListaModel medicaoListaModel) {
        this.medicaoListaApplication.adicionar(medicaoListaModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

//    public void excluir(long id) { this.medicaoListaApplication.excluir(id); }

<<<<<<< HEAD
//    public void atualizar(long id, MedicaoListaModel medicaoLista) { this.medicaoListaApplication.atualizar(id, medicaoLista); }
=======
    public void atualizar(int id, MedicaoListaModel medicaoListaModel) {
        this.medicaoListaApplication.atualizar(id, medicaoListaModel);
    }
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
}
