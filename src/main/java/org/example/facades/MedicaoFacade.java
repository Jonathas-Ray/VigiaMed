package org.example.facades;


import org.example.applications.MedicaoApplication;
import org.example.models.MedicaoModel;

import java.util.List;

public class MedicaoFacade {
        private final MedicaoApplication medicaoApplication;

        public MedicaoFacade(MedicaoApplication medicaoApplication) {
            this.medicaoApplication = medicaoApplication;
        }

        public List<MedicaoModel> buscarTodos() {
            return this.medicaoApplication.buscarTodos();
        }

        public MedicaoModel buscarPorId(int id) {
            return this.medicaoApplication.buscarPorId(id);
        }

        public void adicionar(MedicaoModel medicaoModel) {
            this.medicaoApplication.inserir(medicaoModel);
        }

        public void excluir(int id) {
            this.medicaoApplication.excluir(id);
        }

        public void atualizar(int id, MedicaoModel medicaoModel) {
            this.medicaoApplication.atualizar(id, medicaoModel);
        }
}
