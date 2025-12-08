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

    public Medicao adicionar(Medicao medicao) {
        MedicaoModel medicaoModel = medicao.toModel();
        MedicaoModel savedModel = this.medicaoRepository.adicionar(medicaoModel);

        // 2. Converta o modelo salvo de volta para a entidade
        Medicao novaMedicao = new Medicao().fromModel(savedModel);

        // 3. Retorne a entidade que AGORA possui o ID
        return novaMedicao;
    }

    public void excluir(int id) {
        this.medicaoRepository.excluir(id);
    }

    public void atualizar(int id, Medicao medicao){
        MedicaoModel medicaoModel = medicao.toModel();
        this.medicaoRepository.atualizar(id, medicaoModel);
    }
}