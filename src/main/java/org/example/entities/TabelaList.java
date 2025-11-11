package org.example.entities;

import org.example.models.TabelaListModel;

public class TabelaList {
    private int id;
    private String nome;

    public TabelaList() {}

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
        return new TabelaListModel(
                this.getNome()
        );
    }

    public static TabelaList fromModel(TabelaListModel model) {
        return new TabelaList(
                model.getId(),
                model.getNome()
        );
    }
}