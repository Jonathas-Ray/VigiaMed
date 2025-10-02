package org.example.repositories;

import org.example.entities.Medicao;
import org.example.interfaces.MedicaoRepository;
import org.example.models.MedicaoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
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

    @Override
    public void adicionar(MedicaoModel medicao) { }

    @Override
    public void atualizar(int id, MedicaoModel medicao) { }

    public void adicionar(Medicao medicao) { }

    public void excluir(int id) {
        this.MedicaoModels.removeIf(l -> l.getId() == id);
    }

    public void atualizar(int id, Medicao medicao) {
        MedicaoModel medicaoInMemory = buscarPorId(id);
        medicaoInMemory.setData_hora(medicao.getData_hora());
    }

}
