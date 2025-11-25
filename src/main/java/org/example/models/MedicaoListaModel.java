package org.example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "medicaoLista")
public class MedicaoListaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double resultado;
    private String tipoMedicao;
    private String data_hora;

    @Column(name = "medicao_id")
    private int medicaoId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "medicao_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MedicaoModel medicaoModel;

    @Column(name = "sensor_id")
    private int sensorId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sensor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SensorModel sensorModel;

    public MedicaoListaModel() {}

    public MedicaoListaModel( double resultado, String tipoMedicao, String data_hora, int medicaoId, MedicaoModel medicao, int sensorId, SensorModel sensor) {
        this.resultado = resultado;
        this.tipoMedicao = tipoMedicao;
        this.data_hora = data_hora;
        this.medicaoId = medicaoId;
        this.medicaoModel = medicao;
        this.sensorId = sensorId;
        this.sensorModel = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getMedicaoId() {
        return medicaoId;
    }

    public void setMedicaoId(int medicaoId) {
        this.medicaoId = medicaoId;
    }

    public MedicaoModel getMedicaoModel() {
        return medicaoModel;
    }

    public void setMedicaoModel(MedicaoModel medicaoModel) {
        this.medicaoModel = medicaoModel;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public SensorModel getSensorModel() {
        return sensorModel;
    }

    public void setSensorModel(SensorModel sensorModel) {
        this.sensorModel = sensorModel;
    }
}
