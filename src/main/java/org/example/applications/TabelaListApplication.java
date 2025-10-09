package org.example.applications;

import org.example.interfaces.TabelaListRepository;
import org.example.models.TabelaListModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabelaListApplication {
    private final TabelaListRepository tabelaListRepository;

    public TabelaListApplication(TabelaListRepository tabelaListRepository) {
        this.tabelaListRepository = tabelaListRepository;
    }

    public List<TabelaListModel> buscarTodos() {
        return this.tabelaListRepository.buscarTodos();
    }

    public TabelaListModel buscarPorId(int id) {
        return this.tabelaListRepository.buscarPorId(id);
    }

    public void adicionar(TabelaListModel tabelaListModel) {
        this.tabelaListRepository.adicionar(tabelaListModel);
    }

    public void excluir(int id) {
        this.tabelaListRepository.excluir(id);
    }

    public void atualizar(int id, TabelaListModel tabelaListModel) {
        this.tabelaListRepository.atualizar(id, tabelaListModel);
    }
}
