package org.example.entities;

import java.util.Date;
import org.example.models.DispositivoModel;

public class Dispositivo {
    private int id;
    private String modelo;
    private String numeroSerie;
    private Date dataAquisicao;

    public Dispositivo(){}

    public Dispositivo(String modelo, String numeroSerie, Date dataAquisicao) {
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.dataAquisicao = dataAquisicao;
    }

    public Dispositivo(int id, String modelo, String numeroSerie, Date dataAquisicao) {
        this.id = id;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.dataAquisicao = dataAquisicao;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }
    public Date getDataAquisicao() { return dataAquisicao; }
    public void setDataAquisicao(Date dataAquisicao) { this.dataAquisicao = dataAquisicao; }

    public DispositivoModel toModel() {
        DispositivoModel model = new DispositivoModel(
                this.getModelo(),
                this.getNumeroSerie(),
                this.getDataAquisicao()
        );
        model.setId(this.getId());
        return model;
    }

    public static Dispositivo fromModel(DispositivoModel model) {
        Dispositivo dispositivo = new Dispositivo(
                model.getModelo(),
                model.getNumeroSerie(),
                model.getDataAquisicao()
        );
        dispositivo.setId(model.getId());
        return dispositivo;
    }
}