package org.example.applications;

import org.example.interfaces.UnidadeRepository;
import org.example.models.UnidadeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadeApplication {
    private final UnidadeRepository unidadeRepository;

    @Autowired
    public UnidadeApplication(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public List<UnidadeModel> buscarTodos() {
        return this.unidadeRepository.buscarTodos();
    }

    public UnidadeModel buscarPorId(int id) {
        return this.unidadeRepository.buscarPorId(id);
    }

    public void adicionar(UnidadeModel unidadeModel) {
        this.unidadeRepository.adicionar(unidadeModel);
    }

    public void excluir(int id) {
        this.unidadeRepository.excluir(id);
    }

    public void atualizar(int id, UnidadeModel unidadeModel) {
        this.unidadeRepository.atualizar(id, unidadeModel);
    }
}