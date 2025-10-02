package org.example.interfaces;

import org.example.entities.Unidade;
import org.example.models.UnidadeModel;

import java.util.List;

public interface UnidadeRepository {
    List<UnidadeModel> buscarTodos();
    UnidadeModel buscarPorId(int id);
    void adicionar(UnidadeModel unidadeModel);
    void excluir(int id);
    void atualizar(int id, UnidadeModel unidadeModel);
}