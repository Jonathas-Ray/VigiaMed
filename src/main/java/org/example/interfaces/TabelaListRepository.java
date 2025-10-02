package org.example.interfaces;

import org.example.models.TabelaListModel;

import java.util.List;

public interface TabelaListRepository {
    List<TabelaListModel> buscarTodos();
    TabelaListModel buscarPorId(int id);
    void adicionar(TabelaListModel tabelaList);
    void excluir(int id);
    void atualizar(int id, TabelaListModel usuario);
}
