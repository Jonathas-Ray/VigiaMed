package org.example.facades;


import org.example.applications.MedicaoApplication;
import org.example.entities.Medicao;

import java.util.List;

public class MedicaoFacade {
        private final MedicaoApplication medicaoApplication;

        public MedicaoFacade(MedicaoApplication medicaoApplication) {
            this.medicaoApplication = medicaoApplication;
        }

        public List<Medicao> buscarTodos() {
            return this.medicaoApplication.buscarTodos();
        }

        public Medicao buscarPorId(int id) {
            return this.medicaoApplication.buscarPorId(id);
        }

        public void adicionar(Medicao medicao) {
            this.medicaoApplication.inserir(medicao);
        }

        public void excluir(int id) {
            this.medicaoApplication.excluir(id);
        }

        public void atualizar(int id, Medicao medicao) {
            this.medicaoApplication.atualizar(id, medicao);
        }
}
