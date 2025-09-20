package org.example.facades;

import org.example.applications.UnidadeApplication;
import org.example.entities.Unidade;
import java.util.List;

public class UnidadeFacade {
    private final UnidadeApplication unidadeApplication;

    public UnidadeFacade(UnidadeApplication unidadeApplication) {
        this.unidadeApplication = unidadeApplication;
    }

    public List<Unidade> buscarTodos() {
        return this.unidadeApplication.buscarTodos();
    }

    public Unidade buscarPorId(int id) {
        return this.unidadeApplication.buscarPorId(id);
    }

    public void adicionar(Unidade unidade) {
        this.unidadeApplication.adicionar(unidade);
    }

    public void excluir(int id) {
        this.unidadeApplication.excluir(id);
    }

    public void atualizar(int id, Unidade unidade) {
        this.unidadeApplication.atualizar(id, unidade);
    }
}