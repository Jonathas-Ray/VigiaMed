package org.example.interfaces;

import org.example.entities.MedicaoLista;

import java.util.List;

public interface MedicaoListaRepository {

        public List<MedicaoLista> buscarTodos();
        public MedicaoLista buscarPorId(int id);
        public void adicionar(MedicaoLista listaMedicao);
        public void excluir(int id);
        public void atualizar(int id, MedicaoLista listaMedicao);


}
