package org.example.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medicao")
public class MedicaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data_hora;

    @Column(name = "dispositivo_id")
    private int dispositivoId;

    @Column(name = "paciente_id")
    private int pacienteId;

    @OneToOne
    @JoinColumn(name = "dispositivo_id", referencedColumnName = "id", insertable = false, updatable = false)
    private DispositivoModel dispositivoModel;

    @OneToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PacienteModel pacienteModel;


    public MedicaoModel(int id, String data_hora) {
        this.id = id;
        this.data_hora = data_hora;
    }

    public MedicaoModel(){}

    public int getId() { return id; }

    public void setId(int id){this.id = id;}

    public String getData_hora() {
        return this.data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }


    @Override
    public String toString() {
        return "MedicaoModel{" +
                "id=" + id +
                ", data_hora='"  + data_hora + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "medicaoModel")
    private List<MedicaoListaModel> medicaoListaModel = new ArrayList<>();
}
