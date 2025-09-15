package org.example.applications;

import org.example.interfaces.UnidadeRepository;
import org.example.entities.Unidade;

import java.util.List;

public class UnidadeApplication {
    private UnidadeRepository unidadeRepository;

    public UnidadeApplication(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public List<Unidade> buscarTodos() {
        return this.unidadeRepository.buscarTodos();
    }

    public Unidade buscarPorId(int id) {
        return this.unidadeRepository.buscarPorId(id);
    }

    public void adicionar(Unidade unidade) {
        this.unidadeRepository.adicionar(unidade);
    }

    public void excluir(int id) {
        this.unidadeRepository.excluir(id);
    }

    public void atualizar(int id, Unidade unidade) {
        this.unidadeRepository.atualizar(id, unidade);
    }
}