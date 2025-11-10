package org.example.applications;

import org.example.entities.TabelaList;
import org.example.interfaces.TabelaListRepository;
import org.example.models.TabelaListModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.ArrayList;
import java.util.List;

@Service
public class TabelaListApplication {
    private final TabelaListRepository tabelaListRepository;

    public TabelaListApplication(TabelaListRepository tabelaListRepository) {
        this.tabelaListRepository = tabelaListRepository;
    }

<<<<<<< HEAD
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
=======
    public List<TabelaList> buscarTodos() {
        List<TabelaListModel> modelList = this.tabelaListRepository.buscarTodos();
        List<TabelaList> entitieList = new ArrayList<>();
        for(TabelaListModel tabelaListModel : modelList) {
            entitieList.add(new TabelaList().fromModel(tabelaListModel));
        }
        return entitieList;
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
    }

    public TabelaList buscarPorId(int id) {
        TabelaList tabelaList = new TabelaList().fromModel(this.tabelaListRepository.buscarPorId(id));
        return tabelaList;
    }

    public void adicionar(TabelaList tabelaList) {
        TabelaListModel tabelaListModel = tabelaList.toModel();
        this.tabelaListRepository.adicionar(tabelaListModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.tabelaListRepository.excluir(id);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void atualizar(int id, TabelaListModel tabelaList){
        this.tabelaListRepository.atualizar(id, tabelaList);
=======
    public void atualizar(int id, TabelaListModel tabelaListModel) {
=======
    public void atualizar(int id, TabelaList tabelaList){
        TabelaListModel tabelaListModel = tabelaList.toModel();
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
        this.tabelaListRepository.atualizar(id, tabelaListModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}