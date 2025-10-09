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

    public int getId() { return id; }

    public void setId(int id){this.id = id;}

    public String getData_hora() {
        return this.data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }


    @Override
    public String toString() {
        return "MedicaoModel{" +
                "id=" + id +
                ", data_hora='" + data_hora + '\'' +
                '}';
    }
}
