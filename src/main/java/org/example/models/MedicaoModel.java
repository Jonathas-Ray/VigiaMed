package org.example.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Medicao")
public class MedicaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descricao;
    private String dataHora;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteModel paciente;

    @ManyToOne
    @JoinColumn(name = "dispositivo_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DispositivoModel dispositivo;

    @OneToMany(mappedBy = "medicaoModel", cascade = CascadeType.ALL)
    private List<MedicaoListaModel> medicoesLista;

    public MedicaoModel() {}

    public MedicaoModel(int id, String descricao, String dataHora) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }

    public DispositivoModel getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(DispositivoModel dispositivo) {
        this.dispositivo = dispositivo;
    }

    public List<MedicaoListaModel> getMedicoesLista() {
        return medicoesLista;
    }

    public void setMedicoesLista(List<MedicaoListaModel> medicoesLista) {
        this.medicoesLista = medicoesLista;
    }
}
