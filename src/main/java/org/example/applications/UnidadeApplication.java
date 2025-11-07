package org.example.applications;

import org.example.entities.Unidade;
import org.example.interfaces.UnidadeRepository;
import org.example.models.UnidadeModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnidadeApplication {
    private final UnidadeRepository unidadeRepository;

    public UnidadeApplication(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public List<Unidade> buscarTodos() {
        List<UnidadeModel> modelList = this.unidadeRepository.buscarTodos();
        List<Unidade> entitieList = new ArrayList<>();
        for(UnidadeModel unidadeModel : modelList) {
            entitieList.add(new Unidade().fromModel(unidadeModel));
        }
        return entitieList;
    }

    public Unidade buscarPorId(int id) {
        Unidade unidade = new Unidade().fromModel(this.unidadeRepository.buscarPorId(id));
        return unidade;
    }

    public void adicionar(Unidade unidade) {
        UnidadeModel unidadeModel = unidade.toModel();
        this.unidadeRepository.adicionar(unidadeModel);
    }

    public void excluir(int id) {
        this.unidadeRepository.excluir(id);
    }

    public void atualizar(int id, Unidade unidade){
        UnidadeModel unidadeModel = unidade.toModel();
        this.unidadeRepository.atualizar(id, unidadeModel);
    }
}