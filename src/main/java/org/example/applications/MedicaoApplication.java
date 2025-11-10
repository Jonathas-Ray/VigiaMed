package org.example.applications;

import org.example.entities.Medicao;
import org.example.interfaces.MedicaoRepository;
import org.example.models.MedicaoModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicaoApplication {
    private final MedicaoRepository medicaoRepository;

    public MedicaoApplication(MedicaoRepository medicaoRepository) {
        this.medicaoRepository = medicaoRepository;
    }

<<<<<<< HEAD
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
=======
    public List<Medicao> buscarTodos() {
        List<MedicaoModel> modelList = this.medicaoRepository.buscarTodos();
        List<Medicao> entitieList = new ArrayList<>();
        for(MedicaoModel medicaoModel : modelList) {
            entitieList.add(new Medicao().fromModel(medicaoModel));
        }
        return entitieList;
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
    }

    public Medicao buscarPorId(int id) {
        Medicao medicao = new Medicao().fromModel(this.medicaoRepository.buscarPorId(id));
        return medicao;
    }

    public void adicionar(Medicao medicao) {
        MedicaoModel medicaoModel = medicao.toModel();
        this.medicaoRepository.adicionar(medicaoModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.medicaoRepository.excluir(id);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void atualizar(int id, MedicaoModel medicao){
        medicaoRepository.atualizar(medicao.getId(), medicao);
=======
    public void atualizar(int id, MedicaoModel medicaoModel) {
=======
    public void atualizar(int id, Medicao medicao){
        MedicaoModel medicaoModel = medicao.toModel();
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
        this.medicaoRepository.atualizar(id, medicaoModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}