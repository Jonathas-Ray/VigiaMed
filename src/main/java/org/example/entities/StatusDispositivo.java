package org.example.entities;

import org.example.models.StatusDispositivoModel;

public class StatusDispositivo {
    private int id;
    private String estado;

    public StatusDispositivo(){}

    public StatusDispositivo(int id, String estado){
        this.id = id;
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

    public StatusDispositivoModel toModel() {
        return new StatusDispositivoModel(
                this.getEstado()
        );
    }

    public static StatusDispositivo fromModel(StatusDispositivoModel model) {
        return new StatusDispositivo(
                model.getId(),
                model.getEstado()
        );
    }
}