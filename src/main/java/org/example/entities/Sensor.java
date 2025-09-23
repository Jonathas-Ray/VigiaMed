package org.example.entities;

public class Sensor {
    private int id;
    private String nome;
    private String unidadeMedida;

    public Sensor(int id, String nome, String unidadeMedida) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
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

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}