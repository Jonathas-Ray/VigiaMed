package org.example.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Dispositivo")
public class DispositivoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String modelo;

    private String numeroSerie;

    private Date dataAquisicao;

    @OneToOne
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UnidadeModel unidadeModel;

    @OneToOne
    @JoinColumn(name = "statusDispositivo_id", referencedColumnName = "id", insertable = false, updatable = false)
    private StatusDispositivoModel statusDispositivoModel;

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
}
