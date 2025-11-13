package org.example.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.example.models.DispositivoModel;
import org.example.models.MedicaoModel;
import org.example.models.StatusDispositivoModel;
import org.example.models.UnidadeModel;

public class Dispositivo {
    private int id;
    private String modelo;
    private String numeroSerie;
    private Date dataAquisicao;
    private int unidadeId;
    private UnidadeModel unidade;
    private int statusDispositivoId;
    private StatusDispositivoModel statusDispositivo;
    private List<MedicaoModel> medicoes = new ArrayList<>();;

    public Dispositivo(){}

    public Dispositivo(int id, String modelo, String numeroSerie, Date dataAquisicao, int unidadeId, UnidadeModel unidade, int statusDispositivoId, StatusDispositivoModel statusDispositivo, List<MedicaoModel> medicoes) {
        this.id = id;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.dataAquisicao = dataAquisicao;
        this.unidadeId = unidadeId;
        this.unidade = unidade;
        this.statusDispositivoId = statusDispositivoId;
        this.statusDispositivo = statusDispositivo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(int unidadeId) {
        this.unidadeId = unidadeId;
    }

    public int getStatusDispositivoId() {
        return statusDispositivoId;
    }

    public void setStatusDispositivoId(int statusDispositivoId) {
        this.statusDispositivoId = statusDispositivoId;
    }

    public List<MedicaoModel> getMedicoes() {
        return medicoes;
    }

    public void setMedicoes(List<MedicaoModel> medicoes) {
        this.medicoes = medicoes;
    }

    public DispositivoModel toModel() {
        return new DispositivoModel(
                this.getModelo(),
                this.getNumeroSerie(),
                this.getDataAquisicao(),
                this.unidadeId,
                null,
                this.statusDispositivoId,
                null,
                this.medicoes
        );
    }

    public static Dispositivo fromModel(DispositivoModel model) {
        return new Dispositivo(
                model.getId(),
                model.getModelo(),
                model.getNumeroSerie(),
                model.getDataAquisicao(),
                model.getUnidadeId(),
                null,
                model.getStatusDispositivoId(),
                null,
                model.getMedicoes()
        );
    }
}