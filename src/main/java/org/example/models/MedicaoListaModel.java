package org.example.models;

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
    @JoinColumn(name = "medicao_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MedicaoModel medicaoModel;

    @Column(name = "sensor_id")
    private int sensorId;
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SensorModel sensorModel;

    public MedicaoListaModel() {}

    public MedicaoListaModel(int id, double resultado, String tipoMedicao, String data_hora) {
        this.id = id;
        this.resultado = resultado;
        this.tipoMedicao = tipoMedicao;
        this.data_hora = data_hora;
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

    public void setMedicaoModel(MedicaoModel medicaoModel) {
        this.medicaoModel = medicaoModel;
    }

    public void setSensorModel(SensorModel sensorModel) {
        this.sensorModel = sensorModel;
    }
    public void setMedicaoId(int medicaoId) {
        this.medicaoId = medicaoId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }


    public MedicaoModel getMedicao() {
        return medicaoModel;
    }

    public SensorModel getSensor() {
        return sensorModel;
    }

}
