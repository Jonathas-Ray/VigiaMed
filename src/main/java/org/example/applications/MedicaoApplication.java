package org.example.applications;

import org.example.interfaces.MedicaoRepository;
import org.example.models.MedicaoModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.List;

@Service
public class MedicaoApplication {
    private final MedicaoRepository medicaoRepository;

    public MedicaoApplication(MedicaoRepository medicaoRepository) {
        this.medicaoRepository = medicaoRepository;
    }

<<<<<<< HEAD
    public List<MedicaoModel> buscarTodos(){
        return medicaoRepository.buscarTodos();
    }

    public MedicaoModel buscarPorId(int id){
        return medicaoRepository.buscarPorId(id);
    }

    public void inserir(MedicaoModel medicao){
        medicaoRepository.adicionar(medicao);
=======
    public List<MedicaoModel> buscarTodos() {
        return this.medicaoRepository.buscarTodos();
    }

    public MedicaoModel buscarPorId(int id) {
        return this.medicaoRepository.buscarPorId(id);
    }

    public void adicionar(MedicaoModel medicaoModel) {
        this.medicaoRepository.adicionar(medicaoModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.medicaoRepository.excluir(id);
    }

<<<<<<< HEAD
    public void atualizar(int id, MedicaoModel medicao){
        medicaoRepository.atualizar(medicao.getId(), medicao);
=======
    public void atualizar(int id, MedicaoModel medicaoModel) {
        this.medicaoRepository.atualizar(id, medicaoModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}
