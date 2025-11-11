package org.example.facades;

import org.example.applications.MedicaoListaApplication;
<<<<<<< HEAD
import org.example.models.MedicaoListaModel;
<<<<<<< HEAD
=======
=======
import org.example.entities.MedicaoLista;
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

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

<<<<<<< HEAD
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
=======
    public MedicaoLista buscarPorId(int id) {
        return this.medicaoListaApplication.buscarPorId(id);
    }

    public void adicionar(MedicaoLista medicaoLista) {
        this.medicaoListaApplication.adicionar(medicaoLista);
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
    }

//    public void excluir(long id) { this.medicaoListaApplication.excluir(id); }

<<<<<<< HEAD
<<<<<<< HEAD
//    public void atualizar(long id, MedicaoListaModel medicaoLista) { this.medicaoListaApplication.atualizar(id, medicaoLista); }
=======
    public void atualizar(int id, MedicaoListaModel medicaoListaModel) {
        this.medicaoListaApplication.atualizar(id, medicaoListaModel);
    }
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
}
=======
    public void atualizar(int id, MedicaoLista medicaoLista) {
        this.medicaoListaApplication.atualizar(id, medicaoLista);
    }
}
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
