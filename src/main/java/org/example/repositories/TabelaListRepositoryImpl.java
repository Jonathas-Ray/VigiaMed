package org.example.repositories;

import org.example.interfaces.TabelaListRepository;
import org.example.models.TabelaListModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class TabelaListRepositoryImpl implements TabelaListRepository {
    private final List<TabelaListModel> tabelas = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<TabelaListModel> buscarTodos() {
        return tabelas;
    }

    public TabelaListModel buscarPorId(int id) {
        for (TabelaListModel tabela : tabelas) {
            if (tabela.getId() == id) {
                return tabela;
            }
        }
        return null;
    }

    public void adicionar(TabelaListModel tabela) {
        if (tabela.getId() == 0) {
            tabela.setId(idCounter.getAndIncrement());
        }
        tabelas.add(tabela);
    }

    public void excluir(int id) {
        TabelaListModel tabelaRemover = buscarPorId(id);
        if (tabelaRemover != null) {
            tabelas.remove(tabelaRemover);
        }
    }

    public void atualizar(int id, TabelaListModel tabela) {
        TabelaListModel existente = buscarPorId(id);
        if (existente != null) {
            existente.setNome(tabela.getNome());
        }
    }
}
