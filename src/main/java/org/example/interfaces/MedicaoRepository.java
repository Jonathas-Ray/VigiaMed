package org.example.interfaces;

import org.example.entities.Medicao;

import java.util.ArrayList;
import java.util.List;

public class MedicaoRepository {
        private List<Medicao> medicoes = new ArrayList<>();

        public List<Medicao> buscarTodos() {
            return medicoes;
        }

        public Medicao buscarPorId(int id) {
            return medicoes
                    .stream()
                    .filter(l -> l.getId() == id)
                    .findFirst()
                    .get();
        }

        public void adicionar(Medicao medicao) {
            this.medicoes.add(medicao);
        }

        public void excluir(int id) {
            this.medicoes.removeIf(l -> l.getId() == id);
        }

        public void atualizar(int id, Medicao medicao) {
            Medicao medicaoInMemory = buscarPorId(id);

            medicaoInMemory.setData_hora(medicao.getData_hora());
        }

}
