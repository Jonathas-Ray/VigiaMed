package org.example.interfaces;

import org.example.entities.Unidade;
import java.util.List;

public interface UnidadeRepository {
    List<Unidade> buscarTodos();
    Unidade buscarPorId(int id);
    void adicionar(Unidade unidade);
    void excluir(int id);
    void atualizar(int id, Unidade unidade);
}