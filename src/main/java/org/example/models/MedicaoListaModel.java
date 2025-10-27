package org.example.models;

import jakarta.persistence.*;
import org.example.entities.Medicao;
import org.example.entities.Sensor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medição-Lista")

public class MedicaoListaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double resultado;
    private String tipoMedicao;
    private String data_hora;

    @ManyToOne
    @JoinColumn(name = "medicao_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "medicao_id"), insertable = false, updatable = false)
    private Medicao medicao;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "sensor_id"))
    private Sensor sensor;

    public MedicaoListaModel(int id, double resultado, String tipoMedicao, String data_hora) {
        this.id = id;
        this.resultado = resultado;
        this.tipoMedicao = tipoMedicao;
        this.data_hora = data_hora;
    }

    public MedicaoListaModel(){}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public String getTipoMedicao() {
        return tipoMedicao;
    }

    public void setTipoMedicao(String tipoMedicao) {
        this.tipoMedicao = tipoMedicao;
    }

    public String getData_hora() {
        return data_hora;
    }

    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }

    @Override
    public String toString() {
        return "MedicaoListaModel{" +
                "id=" + id +
                ", resultado=" + resultado +
                ", tipoMedicao='" + tipoMedicao + '\'' +
                ", data_hora='" + data_hora + '\'' +
                '}';
    }
}
