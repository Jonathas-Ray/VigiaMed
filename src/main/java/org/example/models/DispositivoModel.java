package org.example.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Dispositivo")
public class DispositivoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String modelo;

    private String numeroSerie;

    private Date dataAquisicao;

    public DispositivoModel(){}

    public DispositivoModel (long id, String modelo, String numeroSerie, Date dataAquisicao){
        this.id = id;
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

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "DispositivoModel{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", dataAquisicao=" + dataAquisicao +
                '}';
    }
}
