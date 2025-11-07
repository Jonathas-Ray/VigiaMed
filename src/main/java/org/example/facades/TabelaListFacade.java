package org.example.facades;

import org.example.applications.TabelaListApplication;
import org.example.entities.TabelaList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabelaListFacade {
    private final TabelaListApplication tabelaListApplication;

    public TabelaListFacade(TabelaListApplication tabelaListApplication) {
        this.tabelaListApplication = tabelaListApplication;
    }

    public List<TabelaList> buscarTodos() {
        return this.tabelaListApplication.buscarTodos();
    }

    public TabelaList buscarPorId(int id) {
        return this.tabelaListApplication.buscarPorId(id);
    }

    public void adicionar(TabelaList tabelaList) {
        this.tabelaListApplication.adicionar(tabelaList);
    }

    public void excluir(int id) {
        this.tabelaListApplication.excluir(id);
    }

    public void atualizar(int id, TabelaList tabelaList) {
        this.tabelaListApplication.atualizar(id, tabelaList);
    }
}