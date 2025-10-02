package org.example.interfaces;

import org.example.models.MedicaoListaModel;

import java.util.List;

public interface MedicaoListaRepository {
        List<MedicaoListaModel> buscarTodos();
        MedicaoListaModel buscarPorId(int id);
        void adicionar(MedicaoListaModel listaMedicao);
        void excluir(int id);
        void atualizar(int id, MedicaoListaModel listaMedicao);
}
