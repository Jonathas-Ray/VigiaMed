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

    // 🔹 Relação com PacienteModel (muitas medições podem ser de um paciente)
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteModel paciente;

    // 🔹 Relação com DispositivoModel (muitas medições podem vir de um dispositivo)
    @ManyToOne
    @JoinColumn(name = "dispositivo_id")
    private DispositivoModel dispositivo;

    // 🔹 Relação com MedicaoListaModel (1 medição pode ter várias medições detalhadas)
    @OneToMany(mappedBy = "medicaoModel", cascade = CascadeType.ALL)
    private List<MedicaoListaModel> medicoesLista;

    public MedicaoModel() {}

    public MedicaoModel(int id, String descricao, String dataHora) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }

    public PacienteModel getPaciente() { return paciente; }
    public void setPaciente(PacienteModel paciente) { this.paciente = paciente; }

    public DispositivoModel getDispositivo() { return dispositivo; }
    public void setDispositivo(DispositivoModel dispositivo) { this.dispositivo = dispositivo; }

    public List<MedicaoListaModel> getMedicoesLista() { return medicoesLista; }
    public void setMedicoesLista(List<MedicaoListaModel> medicoesLista) { this.medicoesLista = medicoesLista; }
}
