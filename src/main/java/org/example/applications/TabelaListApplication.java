package org.example.applications;

import org.example.entities.TabelaList;
import org.example.interfaces.TabelaListRepository;

import java.util.List;

public class TabelaListApplication {
    private final TabelaListRepository tabelaListRepository;

    public TabelaListApplication(TabelaListRepository tabelaListRepository){
        this.tabelaListRepository = tabelaListRepository;
    }

    public List<TabelaList> buscarTodos(){
        return this.tabelaListRepository.buscarTodos();
    }

    public TabelaList buscarPorId(int id){
        return this.tabelaListRepository.buscarPorId(id);
    }

    public void adicionar(TabelaList tabelaList){
        this.tabelaListRepository.adicionar(tabelaList);
    }

    public void excluir(int id){
        this.tabelaListRepository.excluir(id);
    }

    public void atualizar(int id, TabelaList tabelaList){
        this.tabelaListRepository.atualizar(id, tabelaList);
    }
}
