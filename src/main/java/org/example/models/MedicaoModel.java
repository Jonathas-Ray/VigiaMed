package org.example.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medicao")
public class MedicaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String descricao;
    private String dataHora;

    @Column(name = "paciente_id")
    private int pacienteId;
    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PacienteModel paciente;

    @Column(name = "dispositivo_id")
    private int dispositivoId;
    @ManyToOne
    @JoinColumn(name = "dispositivo_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DispositivoModel dispositivo;

    @OneToMany(mappedBy = "medicaoModel", cascade = CascadeType.ALL)
    private List<MedicaoListaModel> medicoesLista;

    public MedicaoModel() {}

    public MedicaoModel( String descricao, String dataHora) {
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

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }

    public int getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(int dispositivoId) {
        this.dispositivoId = dispositivoId;
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
