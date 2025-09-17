package org.example.entities;

import java.util.Date;

public class Dispositivo {
    private long id;
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

    /*acho que nao precisa de nem um dos 'SET' nessa "tabela"
     *por não ter a necessidade de mudar nem um deles.   */

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }
    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }
}
