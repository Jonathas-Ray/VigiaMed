package org.example.applications;

import org.example.entities.Medicao;
import org.example.interfaces.MedicaoRepository;
import org.example.models.MedicaoModel;

import java.util.List;

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

    public void inserir(MedicaoModel medicao){
        medicaoRepository.adicionar(medicao);
    }

    public void excluir(int id){
        medicaoRepository.excluir(id);
    }

    public void atualizar(int id, MedicaoModel medicao){
        medicaoRepository.atualizar(medicao.getId(), medicao);
    }

}
