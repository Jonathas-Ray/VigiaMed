package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Medicao-Lista")

public class MedicaoListaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double resultado;
    private String tipoMedicao;
    private String data_hora;

    @Column(name = "medicao_id")
    private int medicoId;

    @Column(name = "sensor_id")
    private int sensorId;


    @ManyToOne
    @JoinColumn(name = "medicao_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MedicaoModel medicaoModel;

    @OneToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SensorModel sensorModel;

    public MedicaoListaModel(int id, double resultado, String tipoMedicao, String data_hora) {
        this.id = id;
        this.resultado = resultado;
        this.tipoMedicao = tipoMedicao;
        this.data_hora = data_hora;
    }

    public MedicaoListaModel() {
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
}
