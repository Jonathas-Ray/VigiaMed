package org.example.models;

import java.util.Date;

public class DispositivoModel {

    private long id;
    private String modelo;
    private String numeroSerie;
    private Date dataAquisicao;
    // Aqui entraria as FK`s de status e unidade com statusId; e unidadeId;

    public DispositivoModel(){}

    public DispositivoModel (String modelo, String numeroSerie, Date dataAquisicao){
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

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }
}
