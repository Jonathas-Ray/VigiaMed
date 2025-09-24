package org.example.models;

public class StatusDispositivoModel {
    private long id;
    private String estado;

    public StatusDispositivoModel(){}

    public StatusDispositivoModel(String estado){
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
