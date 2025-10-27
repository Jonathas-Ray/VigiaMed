package org.example.models;

import jakarta.persistence.*;
import org.example.entities.Dispositivo;
import org.example.entities.Log;
import org.example.entities.MedicaoLista;
import org.example.entities.Paciente;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medição")
public class MedicaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String data_hora;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dispositivo_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "dispositivo_id_fk"))
    private DispositivoModel dispositivoModel;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "paciente_id_fk"))
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
