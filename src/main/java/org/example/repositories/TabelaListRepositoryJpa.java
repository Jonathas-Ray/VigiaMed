package org.example.repositories;

import org.example.interfaces.TabelaListModelRepositoryJpa;
import org.example.interfaces.TabelaListRepository;
import org.example.models.TabelaListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TabelaListRepositoryJpa implements TabelaListRepository {

    private final TabelaListModelRepositoryJpa tabelaListModelRepositoryJpa;

    @Autowired
    public TabelaListRepositoryJpa(TabelaListModelRepositoryJpa tabelaListModelRepositoryJpa) {
        this.tabelaListModelRepositoryJpa = tabelaListModelRepositoryJpa;
    }

    @Override
    public List<TabelaListModel> buscarTodos() {
        return this.tabelaListModelRepositoryJpa.findAll();
    }

    @Override
    public TabelaListModel buscarPorId(int id) {
        return this.tabelaListModelRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public void adicionar(TabelaListModel tabelaList) {
        this.tabelaListModelRepositoryJpa.save(tabelaList);
    }

    @Override
    public void excluir(int id) {
        this.tabelaListModelRepositoryJpa.deleteById(id);
    }

    @Override
    public void atualizar(int id, TabelaListModel tabelaList) {
        tabelaList.setId(id);
        this.tabelaListModelRepositoryJpa.save(tabelaList);
    }
}
