package org.example.repositories;

import org.example.models.MedicaoListaModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MedicaoListaRepositoryImpl {
    private final List<MedicaoListaModel> listaMedicao = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<MedicaoListaModel> buscarTodos() {
        return listaMedicao;
    }

    public MedicaoListaModel buscarPorId(int id) {
        for (MedicaoListaModel med : listaMedicao) {
            if (med.getId() == id) {
                return med;
            }
        }
        return null;
    }

    public void adicionar(MedicaoListaModel medicao) {
        if (medicao.getId() == 0) {
            medicao.setId(idCounter.getAndIncrement());
        }
        this.listaMedicao.add(medicao);
    }

    public void excluir(int id) {
        MedicaoListaModel medicaoRemover = buscarPorId(id);
        if (medicaoRemover != null) {
            listaMedicao.remove(medicaoRemover);
        }
    }

    public void atualizar(int id, MedicaoListaModel medicao) {
        MedicaoListaModel medExistente = buscarPorId(id);
        if (medExistente != null) {
            medExistente.setData_hora(medicao.getData_hora());
        }
    }
}
