package org.example.applications;

import org.example.interfaces.MedicaoListaRepository;
import org.example.models.MedicaoListaModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.List;

@Service
public class MedicaoListaApplication {
    private final MedicaoListaRepository medicaoListaRepository;

    public MedicaoListaApplication(MedicaoListaRepository medicaoListaRepository) {
        this.medicaoListaRepository = medicaoListaRepository;
    }

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
    }

    public MedicaoListaModel buscarPorId(int id) {
        return this.medicaoListaRepository.buscarPorId(id);
    }

    public void adicionar(MedicaoListaModel medicaoListaModel) {
        this.medicaoListaRepository.adicionar(medicaoListaModel);
    }

    public void excluir(int id) {
        this.medicaoListaRepository.excluir(id);
    }

    public void atualizar(int id, MedicaoListaModel medicaoListaModel) {
        this.medicaoListaRepository.atualizar(id, medicaoListaModel);
    }
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
}
