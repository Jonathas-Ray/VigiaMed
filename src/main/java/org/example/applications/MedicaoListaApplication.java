package org.example.applications;

import org.example.entities.MedicaoLista;
import org.example.interfaces.MedicaoListaRepository;
import org.example.models.MedicaoListaModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicaoListaApplication {
    private final MedicaoListaRepository medicaoListaRepository;

    public MedicaoListaApplication(MedicaoListaRepository medicaoListaRepository) {
        this.medicaoListaRepository = medicaoListaRepository;
    }

<<<<<<< HEAD
<<<<<<< HEAD
        public List<MedicaoListaModel> buscarTodos(){
            return medicaoListaRepository.buscarTodos();
        }

        public MedicaoListaModel buscarPorId(int id){
            return medicaoListaRepository.buscarPorId(id);
        }

        public void inserir(MedicaoListaModel medicaoLista){
            medicaoListaRepository.adicionar(medicaoLista);
        }

        public void excluir(int id){
            medicaoListaRepository.excluir(id);
        }

        public void atualizar(long id, MedicaoListaModel medicaoLista){ medicaoListaRepository.atualizar(medicaoLista.getId(), medicaoLista); }
=======
    public List<MedicaoListaModel> buscarTodos() {
        return this.medicaoListaRepository.buscarTodos();
=======
    public List<MedicaoLista> buscarTodos() {
        List<MedicaoListaModel> modelList = this.medicaoListaRepository.buscarTodos();
        List<MedicaoLista> entitieList = new ArrayList<>();
        for(MedicaoListaModel medicaoListaModel : modelList) {
            entitieList.add(new MedicaoLista().fromModel(medicaoListaModel));
        }
        return entitieList;
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
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
<<<<<<< HEAD
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
}
=======
}
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
