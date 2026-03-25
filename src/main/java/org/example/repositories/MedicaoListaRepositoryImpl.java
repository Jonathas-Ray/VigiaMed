package org.example.repositories;

import org.example.interfaces.MedicaoListaRepository;
import org.example.models.MedicaoListaModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MedicaoListaRepositoryImpl implements MedicaoListaRepository {

    private final List<MedicaoListaModel> listaMedicao = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    @Override
    public List<MedicaoListaModel> buscarTodos() {
        return listaMedicao;
    }

    @Override
    public MedicaoListaModel buscarPorId(int id) {
        for (MedicaoListaModel med : listaMedicao) {
            if (med.getId() == id) {
                return med;
            }
        }
        return null;
    }

    @Override
    public void adicionar(MedicaoListaModel medicao) {
        if (medicao.getId() == 0) {
            medicao.setId(idCounter.getAndIncrement());
        }
        this.listaMedicao.add(medicao);
    }

    @Override
    public void excluir(int id) {
        listaMedicao.removeIf(med -> med.getId() == id);
    }

    @Override
    public void atualizar(int id, MedicaoListaModel medicao) {
        MedicaoListaModel medExistente = buscarPorId(id);
        if (medExistente != null) {
            medExistente.setResultado(medicao.getResultado());
            medExistente.setTipoMedicao(medicao.getTipoMedicao());
            medExistente.setData_hora(medicao.getData_hora());
            medExistente.setMedicaoId(medicao.getMedicaoId());
            medExistente.setSensorId(medicao.getSensorId());
        }
    }

    @Override
    public Double findUltimoResultado() {
        if (listaMedicao.isEmpty()) {
            return null;
        }
        return listaMedicao.get(listaMedicao.size() - 1).getResultado();
    }
}