package org.example.interfaces;

import org.example.entities.MedicaoLista;

import java.util.List;

public interface MedicaoListaRepository {

        List<MedicaoLista> buscarTodos();
        MedicaoLista buscarPorId(int id);
        void adicionar(MedicaoLista listaMedicao);
        void excluir(int id);
        void atualizar(int id, MedicaoLista listaMedicao);


}
