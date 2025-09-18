package org.example.entities;

public class Paciente {
    private int id;
    private String nome;
    private String referencia;

    public Paciente(int id, String nome, String referencia) {
        this.id = id;
        this.nome = nome;
        this.referencia = referencia;
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

    public String getReferencia() {
        return referencia;
    }
    
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}
