package org.example.models;

public class TabelaListModel {
    private long id;
    private String nome;

    public TabelaListModel() {
    }

    public TabelaListModel(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
