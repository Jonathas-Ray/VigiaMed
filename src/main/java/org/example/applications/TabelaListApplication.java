package org.example.applications;

import org.example.interfaces.TabelaListRepository;
import org.example.models.TabelaListModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.List;

@Service
public class TabelaListApplication {
    private final TabelaListRepository tabelaListRepository;

    public TabelaListApplication(TabelaListRepository tabelaListRepository) {
        this.tabelaListRepository = tabelaListRepository;
    }

<<<<<<< HEAD
    public List<TabelaListModel> buscarTodos(){
        return this.tabelaListRepository.buscarTodos();
    }

    public TabelaListModel buscarPorId(int id){
        return this.tabelaListRepository.buscarPorId(id);
    }

    public void adicionar(TabelaListModel tabelaList){
        this.tabelaListRepository.adicionar(tabelaList);
=======
    public List<TabelaListModel> buscarTodos() {
        return this.tabelaListRepository.buscarTodos();
    }

    public TabelaListModel buscarPorId(int id) {
        return this.tabelaListRepository.buscarPorId(id);
    }

    public void adicionar(TabelaListModel tabelaListModel) {
        this.tabelaListRepository.adicionar(tabelaListModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.tabelaListRepository.excluir(id);
    }

<<<<<<< HEAD
    public void atualizar(int id, TabelaListModel tabelaList){
        this.tabelaListRepository.atualizar(id, tabelaList);
=======
    public void atualizar(int id, TabelaListModel tabelaListModel) {
        this.tabelaListRepository.atualizar(id, tabelaListModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}
