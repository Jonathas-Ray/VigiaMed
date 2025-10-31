package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "MedicaoLista")
public class MedicaoListaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double resultado;
    private String tipoMedicao;
    private String data_hora;

    @ManyToOne
    @JoinColumn(name = "medicao_id")
    private MedicaoModel medicaoModel;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private SensorModel sensorModel;

    public MedicaoListaModel() {}

    public MedicaoListaModel(int id, double resultado, String tipoMedicao, String data_hora) {
        this.id = id;
        this.resultado = resultado;
        this.tipoMedicao = tipoMedicao;
        this.data_hora = data_hora;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public double getResultado() { return resultado; }
    public void setResultado(double resultado) { this.resultado = resultado; }

    public String getTipoMedicao() { return tipoMedicao; }
    public void setTipoMedicao(String tipoMedicao) { this.tipoMedicao = tipoMedicao; }

    public String getData_hora() { return data_hora; }
    public void setData_hora(String data_hora) { this.data_hora = data_hora; }

    public MedicaoModel getMedicaoModel() { return medicaoModel; }
    public void setMedicaoModel(MedicaoModel medicaoModel) { this.medicaoModel = medicaoModel; }

    public SensorModel getSensorModel() { return sensorModel; }
    public void setSensorModel(SensorModel sensorModel) { this.sensorModel = sensorModel; }
}
