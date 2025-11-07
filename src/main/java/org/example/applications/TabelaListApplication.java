package org.example.applications;

import org.example.entities.TabelaList;
import org.example.interfaces.TabelaListRepository;
import org.example.models.TabelaListModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TabelaListApplication {
    private final TabelaListRepository tabelaListRepository;

    public TabelaListApplication(TabelaListRepository tabelaListRepository) {
        this.tabelaListRepository = tabelaListRepository;
    }

    public List<TabelaList> buscarTodos() {
        List<TabelaListModel> modelList = this.tabelaListRepository.buscarTodos();
        List<TabelaList> entitieList = new ArrayList<>();
        for(TabelaListModel tabelaListModel : modelList) {
            entitieList.add(new TabelaList().fromModel(tabelaListModel));
        }
        return entitieList;
    }

    public TabelaList buscarPorId(int id) {
        TabelaList tabelaList = new TabelaList().fromModel(this.tabelaListRepository.buscarPorId(id));
        return tabelaList;
    }

    public void adicionar(TabelaList tabelaList) {
        TabelaListModel tabelaListModel = tabelaList.toModel();
        this.tabelaListRepository.adicionar(tabelaListModel);
    }

    public void excluir(int id) {
        this.tabelaListRepository.excluir(id);
    }

    public void atualizar(int id, TabelaList tabelaList){
        TabelaListModel tabelaListModel = tabelaList.toModel();
        this.tabelaListRepository.atualizar(id, tabelaListModel);
    }
}