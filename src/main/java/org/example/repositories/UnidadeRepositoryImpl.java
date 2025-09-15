package org.example.repositories;

import org.example.entities.Unidade;
import org.example.interfaces.UnidadeRepository;

import java.util.ArrayList;
import java.util.List;

public class UnidadeRepositoryImpl implements UnidadeRepository {
    private List<Unidade> unidades = new ArrayList<>();

    public List<Unidade> buscarTodos() {
        return unidades;
    }

    public Unidade buscarPorId(int id) {
        return unidades
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar(Unidade unidade) {
        this.unidades.add(unidade);
    }

    public void excluir(int id) {
        this.unidades.removeIf(l -> l.getId() == id);
    }

    public void atualizar(int id, Unidade unidade) {
        Unidade unidadeInMemory = buscarPorId(id);

        unidadeInMemory.setNome(unidade.getNome());
        unidadeInMemory.setEndereço(unidade.getEndereço());
        unidadeInMemory.setEmail(unidade.getEmail());
        unidadeInMemory.setTelefone(unidade.getTelefone());
    }
}

