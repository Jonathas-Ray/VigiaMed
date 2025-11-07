package org.example.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "statusDispositivo")
public class StatusDispositivoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String estado;

    @OneToMany(mappedBy = "statusDispositivoModel")
    private List<DispositivoModel> dispositivo = new ArrayList<>();

    public StatusDispositivoModel(){}

    public StatusDispositivoModel(String estado){
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}