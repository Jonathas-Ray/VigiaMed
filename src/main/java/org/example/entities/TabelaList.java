package org.example.entities;

import org.example.models.TabelaListModel;

public class TabelaList {
    private int id;
    private String nome;

    public TabelaList() {}

    public TabelaList(String nome) {
        this.nome = nome;
    }

    public TabelaList(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TabelaListModel toModel() {
        TabelaListModel model = new TabelaListModel();
        model.setId(this.getId());
        model.setNome(this.getNome());
        return model;
    }

    public static TabelaList fromModel(TabelaListModel model) {
        TabelaList tabelaList = new TabelaList(
                model.getNome()
        );
        tabelaList.setId(model.getId());
        return tabelaList;
    }
}