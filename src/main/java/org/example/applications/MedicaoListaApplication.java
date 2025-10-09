package org.example.applications;

import org.example.models.MedicaoListaModel;
import org.example.interfaces.MedicaoListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicaoListaApplication {
    private MedicaoListaRepository medicaoListaRepository;

    public MedicaoListaApplication(MedicaoListaRepository medicaoListaRepository) {
        this.medicaoListaRepository = medicaoListaRepository;
    }

    public List<MedicaoListaModel> buscarTodos(){
        return medicaoListaRepository.buscarTodos();
    }

    public MedicaoListaModel buscarPorId(int id){
        return medicaoListaRepository.buscarPorId(id);
    }

    public void inserir(MedicaoListaModel medicaoListaModel){
        medicaoListaRepository.adicionar(medicaoListaModel);
    }

    public void excluir(int id){
        medicaoListaRepository.excluir(id);
    }

    public void atualizar(long id, MedicaoListaModel medicaoListaModel){
        medicaoListaRepository.atualizar(medicaoListaModel.getId(), medicaoListaModel);
    }
}
