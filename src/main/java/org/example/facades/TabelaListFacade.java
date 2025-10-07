package org.example.facades;

import org.example.applications.TabelaListApplication;
import org.example.models.TabelaListModel;

import java.util.List;

public class TabelaListFacade {
    private final TabelaListApplication tabelaListApplication;

    public TabelaListFacade(TabelaListApplication tabelaListApplication){
        this.tabelaListApplication = tabelaListApplication;
    }

    public List<TabelaListModel> buscarTodos() {
        return this.tabelaListApplication.buscarTodos();
    }

    public TabelaListModel buscarPorId(int id) {
        return this.tabelaListApplication.buscarPorId(id);
    }

    public void adicionar(TabelaListModel tabelaListModel) {
        this.tabelaListApplication.adicionar(tabelaListModel);
    }

    public void excluir(int id) {
        this.tabelaListApplication.excluir(id);
    }

    public void atualizar(int id, TabelaListModel tabelaListModel) {
        this.tabelaListApplication.atualizar(id, tabelaListModel);
    }
}
