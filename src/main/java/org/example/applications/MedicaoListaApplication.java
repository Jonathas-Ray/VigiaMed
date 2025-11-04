package org.example.applications;

import org.example.entities.MedicaoLista;
import org.example.interfaces.MedicaoListaRepository;
import org.example.models.MedicaoListaModel;

import java.util.List;

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

        public void inserir(MedicaoListaModel medicaoLista){
            medicaoListaRepository.adicionar(medicaoLista);
        }

        public void excluir(int id){
            medicaoListaRepository.excluir(id);
        }

        public void atualizar(long id, MedicaoListaModel medicaoLista){ medicaoListaRepository.atualizar(medicaoLista.getId(), medicaoLista); }
}
