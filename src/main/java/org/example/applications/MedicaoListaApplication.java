package org.example.applications;

import org.example.entities.MedicaoLista;
import org.example.interfaces.MedicaoListaRepository;

import java.util.List;

public class MedicaoListaApplication {
        private MedicaoListaRepository medicaoListaRepository;

        public MedicaoListaApplication(MedicaoListaRepository medicaoListaRepository) {
            this.medicaoListaRepository = medicaoListaRepository;
        }

        public List<MedicaoLista> buscarTodos(){
            return medicaoListaRepository.buscarTodos();
        }

        public MedicaoLista buscarPorId(int id){
            return medicaoListaRepository.buscarPorId(id);
        }

        public void inserir(MedicaoLista medicaoLista){
            medicaoListaRepository.adicionar(medicaoLista);
        }

        public void excluir(int id){
            medicaoListaRepository.excluir(id);
        }

        public void atualizar(int id, MedicaoLista medicaoLista){
            medicaoListaRepository.atualizar(medicaoLista.getId(), medicaoLista);
        }


}
