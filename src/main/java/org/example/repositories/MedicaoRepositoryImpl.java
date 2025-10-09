package org.example.repositories;

import org.example.entities.Medicao;
import org.example.interfaces.MedicaoRepository;
import org.example.models.MedicaoModel;

import java.util.ArrayList;
import java.util.List;

public class MedicaoRepositoryImpl implements MedicaoRepository {
    private final List<MedicaoModel> MedicaoModels = new ArrayList<>();

    public List<MedicaoModel> buscarTodos() { return MedicaoModels; }

    public MedicaoModel buscarPorId(int id) {
        return MedicaoModels
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar(MedicaoModel medicaoModel) {
        this.MedicaoModels.add(medicaoModel);
    }

    public void atualizar(int id, MedicaoModel medicao) {
        MedicaoModel medExist = buscarPorId(id);
        if (medExist != null){
            medExist.setData_hora(medExist.getData_hora());
        }
    }

    public void excluir(int id) {
        this.MedicaoModels.removeIf(l -> l.getId() == id);
    }

    public void atualizar(int id, Medicao medicao) {
        MedicaoModel medicaoInMemory = buscarPorId(id);
        medicaoInMemory.setData_hora(medicao.getData_hora());
    }

}
