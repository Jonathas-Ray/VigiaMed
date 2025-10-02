package org.example.facades;

import org.example.applications.UnidadeApplication;
import org.example.models.UnidadeModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeFacade {
    private final UnidadeApplication unidadeApplication;

    public UnidadeFacade(UnidadeApplication unidadeApplication) {
        this.unidadeApplication = unidadeApplication;
    }

    public List<UnidadeModel> buscarTodos() {
        return this.unidadeApplication.buscarTodos();
    }

    public UnidadeModel buscarPorId(int id) {
        return this.unidadeApplication.buscarPorId(id);
    }

    public void adicionar(UnidadeModel unidadeModel) {
        this.unidadeApplication.adicionar(unidadeModel);
    }

    public void excluir(int id) {
        this.unidadeApplication.excluir(id);
    }

    public void atualizar(int id, UnidadeModel unidadeModel) {
        this.unidadeApplication.atualizar(id, unidadeModel);
    }
}