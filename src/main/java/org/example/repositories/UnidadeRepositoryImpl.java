package org.example.repositories;

import org.example.entities.Unidade;
import org.example.interfaces.UnidadeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UnidadeRepositoryImpl implements UnidadeRepository {
    private final List<Unidade> unidades = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<Unidade> buscarTodos() {
        return unidades;
    }

    public Unidade buscarPorId(int id) {
        for (Unidade unidade : unidades) {
            if (unidade.getId() == id) {
                return unidade;
            }
        }
        return null;
    }

    public void adicionar(Unidade unidade) {
        if (unidade.getId() == 0) {
            unidade.setId(idCounter.getAndIncrement());
        }
        this.unidades.add(unidade);
    }

    public void excluir(int id) {
        Unidade unidadeParaRemover = null;
        for (Unidade unidade : unidades) {
            if (unidade.getId() == id) {
                unidadeParaRemover = unidade;
                break;
            }
        }
        if (unidadeParaRemover != null) {
            unidades.remove(unidadeParaRemover);
        }
    }

    public void atualizar(int id, Unidade unidade) {
        Unidade unidadeExistente = buscarPorId(id);
        if (unidadeExistente != null) {
            unidadeExistente.setNome(unidade.getNome());
            unidadeExistente.setEndereço(unidade.getEndereço());
            unidadeExistente.setTelefone(unidade.getTelefone());
            unidadeExistente.setEmail(unidade.getEmail());
        }
    }
}