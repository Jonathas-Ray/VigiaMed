package org.example.applications;

import org.example.entities.MedicaoLista;
import org.example.interfaces.MedicaoListaRepository;
import org.example.models.MedicaoListaModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicaoListaApplication {
    private final MedicaoListaRepository medicaoListaRepository;

    public MedicaoListaApplication(MedicaoListaRepository medicaoListaRepository) {
        this.medicaoListaRepository = medicaoListaRepository;
    }

    public List<MedicaoLista> buscarTodos() {
        List<MedicaoListaModel> modelList = this.medicaoListaRepository.buscarTodos();
        List<MedicaoLista> entitieList = new ArrayList<>();
        for(MedicaoListaModel medicaoListaModel : modelList) {
            entitieList.add(new MedicaoLista().fromModel(medicaoListaModel));
        }
        return entitieList;
    }

    public MedicaoLista buscarPorId(int id) {
        MedicaoLista medicaoLista = new MedicaoLista().fromModel(this.medicaoListaRepository.buscarPorId(id));
        return medicaoLista;
    }

    public void adicionar(MedicaoLista medicaoLista) {
        MedicaoListaModel medicaoListaModel = medicaoLista.toModel();
        this.medicaoListaRepository.adicionar(medicaoListaModel);
    }

    public void excluir(int id) {
        this.medicaoListaRepository.excluir(id);
    }

    public void atualizar(int id, MedicaoLista medicaoLista){
        MedicaoListaModel medicaoListaModel = medicaoLista.toModel();
        this.medicaoListaRepository.atualizar(id, medicaoListaModel);
    }
}