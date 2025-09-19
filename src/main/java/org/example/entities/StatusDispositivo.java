package org.example.entities;

public class StatusDispositivo {
    private long id;
    private String estado;

    public StatusDispositivo(){}

    public StatusDispositivo(String estado){
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
