package org.example.interfaces;

import org.example.entities.Unidade;

import java.util.List;

public interface UnidadeRepository {
    public List<Unidade> buscarTodos();
    public Unidade buscarPorId(int id);
    public void adicionar(Unidade unidade);
    public void excluir(int id);
    public void atualizar(int id, Unidade unidade);
}

