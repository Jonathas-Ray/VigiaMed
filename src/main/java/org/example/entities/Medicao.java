package org.example.entities;

public class Medicao {
    private int id;
    private String data_hora;

    public Medicao(int id, String data_hora) {
        this.id = id;
        this.data_hora = data_hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){ this.id = id;}

    public String getData_hora() {
        return data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }
}
