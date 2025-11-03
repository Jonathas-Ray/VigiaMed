package org.example.repositories;

import org.example.interfaces.MedicaoRepository;
import org.example.models.MedicaoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MedicaoRepositoryImpl implements MedicaoRepository {
    private final List<MedicaoModel> medicoes = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<MedicaoModel> buscarTodos() {
        return medicoes;
    }

    public MedicaoModel buscarPorId(int id) {
        for (MedicaoModel med : medicoes) {
            if (med.getId() == id) {
                return med;
            }
        }
        return null;
    }

    public void adicionar(MedicaoModel medicao) {
        if (medicao.getId() == 0) {
            medicao.setId(idCounter.getAndIncrement());
        }
        medicoes.add(medicao);
    }

    public void excluir(int id) {
        MedicaoModel medRemover = buscarPorId(id);
        if (medRemover != null) {
            medicoes.remove(medRemover);
        }
    }

    public void atualizar(int id, MedicaoModel medicao) {
        MedicaoModel medExistente = buscarPorId(id);
        if (medExistente != null) {
            medExistente.setDataHora(medicao.getDataHora());
        }
    }
}
