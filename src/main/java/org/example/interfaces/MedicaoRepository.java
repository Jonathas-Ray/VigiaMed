package org.example.interfaces;

import org.example.entities.Medicao;

import java.util.List;

public interface MedicaoRepository {
        public List<Medicao> buscarTodos();
        public Medicao buscarPorId(int id);
        public void adicionar(Medicao medicao);
        public void excluir(int id);
        public void atualizar(int id, Medicao medicao);
}
