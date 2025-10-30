package org.example.models;

import jakarta.persistence.*;

@Entity
@Table (name = "StatusDispositivo")
public class StatusDispositivoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String estado;

    public StatusDispositivoModel(){}

    public StatusDispositivoModel(String estado){
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "StatusDispositivoModel{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                '}';
    }


}
