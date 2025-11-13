package org.example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "dispositivo")
public class DispositivoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String modelo;
    private String numeroSerie;
    private Date dataAquisicao;

    @Column(name = "unidade_id")
    private int unidadeId;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UnidadeModel unidade;

    @Column(name = "statusDispositivo_id")
    private int statusDispositivoId;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "statusDispositivo_id", referencedColumnName = "id", insertable = false, updatable = false)
    private StatusDispositivoModel statusDispositivoModel;

    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MedicaoModel> medicoes = new ArrayList<>();;

    public DispositivoModel() {}

    public DispositivoModel(String modelo, String numeroSerie, Date dataAquisicao, int unidadeId, UnidadeModel unidade, int statusDispositivoId, StatusDispositivoModel statusDispositivoModel, List<MedicaoModel> medicoes) {
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.dataAquisicao = dataAquisicao;
        this.unidadeId = unidadeId;
        this.statusDispositivoId = statusDispositivoId;
        this.medicoes = medicoes;
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

    public UnidadeModel getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeModel unidade) {
        this.unidade = unidade;
    }

    public int getStatusDispositivoId() {
        return statusDispositivoId;
    }

    public void setStatusDispositivoId(int statusDispositivoId) {
        this.statusDispositivoId = statusDispositivoId;
    }

    public StatusDispositivoModel getStatusDispositivoModel() {
        return statusDispositivoModel;
    }

    public void setStatusDispositivoModel(StatusDispositivoModel statusDispositivoModel) {
        this.statusDispositivoModel = statusDispositivoModel;
    }

    public List<MedicaoModel> getMedicoes() {
        return medicoes;
    }

    public void setMedicoes(List<MedicaoModel> medicoes) {
        this.medicoes = medicoes;
    }
}
