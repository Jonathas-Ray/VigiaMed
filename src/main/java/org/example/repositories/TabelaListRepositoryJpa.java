package org.example.repositories;

import org.example.interfaces.TabelaListRepository;
import org.example.models.TabelaListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TabelaListRepositoryJpa implements TabelaListRepository {

    private final TabelaListRepositoryJpa tabelaListRepositoryJpa;

    @Autowired
    public TabelaListRepositoryJpa(TabelaListRepositoryJpa tabelaListRepositoryJpa) {
        this.tabelaListRepositoryJpa = tabelaListRepositoryJpa;
    }

    @Override
    public List<TabelaListModel> buscarTodos() {
        return List.of();
    }

    @Override
    public TabelaListModel buscarPorId(int id) {
        return null;
    }

    @Override
    public void adicionar(TabelaListModel tabelaList) {

    }

    @Override
    public void excluir(int id) {

    }

    @Override
    public void atualizar(int id, TabelaListModel usuario) {

    }
}
