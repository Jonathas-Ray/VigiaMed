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

    // 🔹 Unidade (muitos dispositivos pertencem a uma unidade)
    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private UnidadeModel unidade;

    // 🔹 Status (1:1)
    @OneToOne
    @JoinColumn(name = "status_dispositivo_id")
    private StatusDispositivoModel status;

    // 🔹 Medições (1 dispositivo tem várias medições)
    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL)
    private List<MedicaoModel> medicoes;

    public DispositivoModel() {}

    public DispositivoModel(String modelo, String numeroSerie, Date dataAquisicao) {
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.dataAquisicao = dataAquisicao;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }

    public Date getDataAquisicao() { return dataAquisicao; }
    public void setDataAquisicao(Date dataAquisicao) { this.dataAquisicao = dataAquisicao; }

    public UnidadeModel getUnidade() { return unidade; }
    public void setUnidade(UnidadeModel unidade) { this.unidade = unidade; }

    public StatusDispositivoModel getStatus() { return status; }
    public void setStatus(StatusDispositivoModel status) { this.status = status; }

    public List<MedicaoModel> getMedicoes() { return medicoes; }
    public void setMedicoes(List<MedicaoModel> medicoes) { this.medicoes = medicoes; }
}
