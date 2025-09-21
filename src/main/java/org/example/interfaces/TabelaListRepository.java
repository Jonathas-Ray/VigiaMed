package org.example.interfaces;

import org.example.entities.TabelaList;

import java.util.List;

public interface TabelaListRepository {
    List<TabelaList> buscarTodos();
    TabelaList buscarPorId(int id);
    void adicionar(TabelaList tabelaList);
    void excluir(int id);
    void atualizar(int id, TabelaList usuario);
}
