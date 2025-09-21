package org.example.entities;

public class TabelaList {
    private long id;
    private String nome;

    public TabelaList() {
    }

    public TabelaList(long id, String nome) {
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
