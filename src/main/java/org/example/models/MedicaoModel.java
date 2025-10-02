package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Medição")
public class MedicaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data_hora;

    public MedicaoModel(int id, String data_hora) {
        this.id = id;
        this.data_hora = data_hora;
    }

    public MedicaoModel(){}

    public String getData_hora() {
        return data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MedicaoModel{" +
                "id=" + id +
                ", data_hora='" + data_hora + '\'' +
                '}';
    }
}
