package org.example.applications;

import org.example.models.MedicaoModel;
import org.example.interfaces.MedicaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicaoApplication {
    private MedicaoRepository medicaoRepository;

    public MedicaoApplication(MedicaoRepository medicaoRepository) {
        this.medicaoRepository = medicaoRepository;
    }

    public List<MedicaoModel> buscarTodos(){
        return medicaoRepository.buscarTodos();
    }

    public MedicaoModel buscarPorId(int id){
        return medicaoRepository.buscarPorId(id);
    }

    public void inserir(MedicaoModel medicaoModel){
        medicaoRepository.adicionar(medicaoModel);
    }

    public void excluir(int id){
        medicaoRepository.excluir(id);
    }

    public void atualizar(int id, MedicaoModel medicaoModel){
        medicaoRepository.atualizar(medicaoModel.getId(), medicaoModel);
    }

}
