package org.example.applications;

import org.example.entities.Medicao;
import org.example.interfaces.MedicaoRepository;
import org.example.models.MedicaoModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicaoApplication {
    private final MedicaoRepository medicaoRepository;

    public MedicaoApplication(MedicaoRepository medicaoRepository) {
        this.medicaoRepository = medicaoRepository;
    }

    public List<Medicao> buscarTodos() {
        List<MedicaoModel> modelList = this.medicaoRepository.buscarTodos();
        List<Medicao> entitieList = new ArrayList<>();
        for(MedicaoModel medicaoModel : modelList) {
            entitieList.add(new Medicao().fromModel(medicaoModel));
        }
        return entitieList;
    }

    public Medicao buscarPorId(int id) {
        Medicao medicao = new Medicao().fromModel(this.medicaoRepository.buscarPorId(id));
        return medicao;
    }

    public void adicionar(Medicao medicao) {
        MedicaoModel medicaoModel = medicao.toModel();
        this.medicaoRepository.adicionar(medicaoModel);
    }

    public void excluir(int id) {
        this.medicaoRepository.excluir(id);
    }

    public void atualizar(int id, Medicao medicao){
        MedicaoModel medicaoModel = medicao.toModel();
        this.medicaoRepository.atualizar(id, medicaoModel);
    }
}