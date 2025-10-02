package org.example.repositories;

import org.example.entities.TabelaList;
import org.example.interfaces.TabelaListRepository;
import org.example.models.TabelaListModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TabelaListRepositoryImpl implements TabelaListRepository {
    private List<TabelaListModel> tabelaListsModels = new ArrayList<>();

    public List<TabelaListModel> buscarTodos() {
        return tabelaListsModels;
    }

    public TabelaListModel buscarPorId(int id) {
        return tabelaListsModels
                .stream()
                .filter(tb -> tb.getId() == id)
                .findFirst()
                .get();
    }


    public void adicionar(TabelaListModel tabelaListModel) {
        this.tabelaListsModels.add(tabelaListModel);
    }

    public void excluir(int id) {
        this.tabelaListsModels.removeIf(tb -> tb.getId() == id);
    }

    public void atualizar(int id, TabelaListModel usuario) {

    }

    public void atualizar(int id, TabelaList tabelaList) {
        TabelaListModel tabelaListExist = buscarPorId(id);
        if (tabelaListExist != null) {
            tabelaListExist.setNome(tabelaListExist.getNome());
        }
    }
}
