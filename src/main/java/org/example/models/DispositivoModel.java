package org.example.models;

import jakarta.persistence.*;
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
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UnidadeModel unidade;

    @Column(name = "statusDispositivo_id")
    private int statusDispositivoId;
    @ManyToOne
    @JoinColumn(name = "statusDispositivo_id", referencedColumnName = "id", insertable = false, updatable = false)
    private StatusDispositivoModel statusDispositivoModel;

    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL)
    private List<MedicaoModel> medicoes;

    public DispositivoModel() {}

    public DispositivoModel(String modelo, String numeroSerie, Date dataAquisicao) {
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.dataAquisicao = dataAquisicao;
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


    public void setUnidade(UnidadeModel unidade) {
        this.unidade = unidade;
    }


    public void setStatus(StatusDispositivoModel statusDispositivoModel) {
        this.statusDispositivoModel = statusDispositivoModel;
    }

    public void setUnidadeId(int unidadeId) {
        this.unidadeId = unidadeId;
    }

    public void setStatusDispositivoIdId(int StatusDispositivoId) {
        this.statusDispositivoId = StatusDispositivoId;
    }
    public void setMedicoes(List<MedicaoModel> medicoes) {
        this.medicoes = medicoes;
    }
}
