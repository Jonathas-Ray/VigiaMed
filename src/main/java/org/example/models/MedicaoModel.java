package org.example.models;

public class MedicaoModel {
    private int id;
    private String data_hora;

    public MedicaoModel(int id, String data_hora) {
        this.id = id;
        this.data_hora = data_hora;
    }

    public String getData_hora() {
        return data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }

    public int getId() {
        return id;
    }

}
