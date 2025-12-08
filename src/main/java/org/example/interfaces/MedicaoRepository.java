package org.example.interfaces;

import org.example.models.MedicaoModel;

import java.util.List;

public interface MedicaoRepository {
        public List<MedicaoModel> buscarTodos();
        public MedicaoModel buscarPorId(int id);
        public MedicaoModel adicionar(MedicaoModel medicao);
        public void excluir(int id);
        public void atualizar(int id, MedicaoModel medicao);
}
