package org.example.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Dispositivo")
public class DispositivoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String modelo;
    private String numeroSerie;
    private Date dataAquisicao;

    @ManyToOne
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UnidadeModel unidade;

    @OneToOne
    @JoinColumn(name = "status_dispositivo_id", referencedColumnName = "id", insertable = false, updatable = false)
    private StatusDispositivoModel status;

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

    public UnidadeModel getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeModel unidade) {
        this.unidade = unidade;
    }

    public StatusDispositivoModel getStatus() {
        return status;
    }

    public void setStatus(StatusDispositivoModel status) {
        this.status = status;
    }

    public List<MedicaoModel> getMedicoes() {
        return medicoes;
    }

    public void setMedicoes(List<MedicaoModel> medicoes) {
        this.medicoes = medicoes;
    }
}
