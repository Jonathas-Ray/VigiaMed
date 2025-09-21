package org.example.repositories;

import org.example.entities.TabelaList;
import org.example.interfaces.TabelaListRepository;

import java.util.ArrayList;
import java.util.List;

public class TabelaListRepositoryImpl implements TabelaListRepository {
    private List<TabelaList> tabelaLists =new ArrayList<>();

    public List<TabelaList> buscarTodos(){
        return tabelaLists;
    }

    public TabelaList buscarPorId(int id){
        return tabelaLists
                .stream()
                .filter(tb -> tb.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar (TabelaList tabelaList){
        this.tabelaLists.add(tabelaList);
    }

    public void excluir(int id){
        this.tabelaLists.removeIf(tb -> tb.getId() == id);
    }

    public void atualizar(int id, TabelaList tabelaList){
        TabelaList tabelaListExist = buscarPorId(id);
        if (tabelaListExist != null){
            tabelaListExist.setNome(tabelaListExist.getNome());
        }
    }
}
