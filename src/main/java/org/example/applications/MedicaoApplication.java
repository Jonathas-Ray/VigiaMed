package org.example.applications;

import org.example.entities.Medicao;
import org.example.interfaces.MedicaoRepository;

import java.util.List;

public class MedicaoApplication {
    private MedicaoRepository medicaoRepository;

    public MedicaoApplication(MedicaoRepository medicaoRepository) {
        this.medicaoRepository = medicaoRepository;
    }

    public List<Medicao> buscarTodos(){
        return medicaoRepository.buscarTodos();
    }

    public Medicao buscarPorId(int id){
        return medicaoRepository.buscarPorId(id);
    }

    public void inserir(Medicao medicao){
        medicaoRepository.adicionar(medicao);
    }

    public void excluir(int id){
        medicaoRepository.excluir(id);
    }

    public void atualizar(int id, Medicao medicao){
        medicaoRepository.atualizar(medicao.getId(), medicao);
    }

}
