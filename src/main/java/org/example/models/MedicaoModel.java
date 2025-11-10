package org.example.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medicao")
public class MedicaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private long id;
    private String data_hora;

    public MedicaoModel(long id, String data_hora) {
=======
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

    public MedicaoModel(int id, String descricao, String dataHora) {
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public long getId() {
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
