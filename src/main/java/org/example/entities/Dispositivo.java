package org.example.entities;

import java.util.Date;

public class Dispositivo {
    private int id;
    private String modelo;
    private String numeroSerie;
    private Date dataAquisicao;
    // Aqui entraria as FK`s de status e unidade com statusId; e unidadeId;

    public Dispositivo(){}

    public Dispositivo (String modelo, String numeroSerie, Date dataAquisicao){
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.dataAquisicao = dataAquisicao;
    }

    public long getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

}
