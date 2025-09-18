package org.example.repositories;

import org.example.entities.MedicaoLista;

import java.util.ArrayList;
import java.util.List;

public class MedicaoListaRepositoryImpl {
        private List<MedicaoLista> listaMedicao = new ArrayList<>();

        public List<MedicaoLista> buscarTodos() {
            return listaMedicao;
        }

        public MedicaoLista buscarPorId(int id) {
            return listaMedicao
                    .stream()
                    .filter(l -> l.getId() == id)
                    .findFirst()
                    .get();
        }

        public void adicionar(MedicaoLista listaMedicao) {
            this.listaMedicao.add(listaMedicao);
        }

        public void excluir(int id) {
            this.listaMedicao.removeIf(l -> l.getId() == id);
        }

        public void atualizar(int id, MedicaoLista medicao) {
            MedicaoLista medListaInMemory = buscarPorId(id);

            medListaInMemory.setData_hora(medicao.getData_hora());
        }

}
