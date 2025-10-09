package org.example.repositories;

import org.example.interfaces.UnidadeRepository;
import org.example.models.UnidadeModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//
public class UnidadeRepositoryImpl implements UnidadeRepository {
    private final List<UnidadeModel> unidadeModels = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<UnidadeModel> buscarTodos() {
        return unidadeModels;
    }

    public UnidadeModel buscarPorId(int id) {
        for (UnidadeModel unidade : unidadeModels) {
            if (unidade.getId() == id) {
                return unidade;
            }
        }
        return null;
    }

    public void adicionar(UnidadeModel unidadeModel) {
        if (unidadeModel.getId() == 0) {
            unidadeModel.setId(idCounter.getAndIncrement());
        }
        this.unidadeModels.add(unidadeModel);
    }

    public void excluir(int id) {
        UnidadeModel unidadeParaRemover = null;
        for (UnidadeModel unidadeModel : unidadeModels) {
            if (unidadeModel.getId() == id) {
                unidadeParaRemover = unidadeModel;
                break;
            }
        }
        if (unidadeParaRemover != null) {
            unidadeModels.remove(unidadeParaRemover);
        }
    }

    public void atualizar(int id, UnidadeModel unidadeModel) {
        UnidadeModel unidadeExistente = buscarPorId(id);
        if (unidadeExistente != null) {
            unidadeExistente.setNome(unidadeModel.getNome());
            unidadeExistente.setEndereço(unidadeModel.getEndereço());
            unidadeExistente.setTelefone(unidadeModel.getTelefone());
            unidadeExistente.setEmail(unidadeModel.getEmail());
        }
    }
}