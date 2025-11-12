package org.example.entities;

import org.example.models.MedicaoListaModel;

public class MedicaoLista {
    private int id;
    private double resultado;
    private String tipoMedicao;
    private String dataHora;
    private int medicaoId;
    private int sensorId;

    public MedicaoLista(){}

    public MedicaoLista(int id, double resultado, String tipoMedicao, String dataHora, int medicaoId, int sensorId) {
        this.id = id;
        this.resultado = resultado;
        this.tipoMedicao = tipoMedicao;
        this.dataHora = dataHora;
        this.medicaoId = medicaoId;
        this.sensorId = sensorId;
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

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public int getMedicaoId() {
        return medicaoId;
    }

    public void setMedicaoId(int medicaoId) {
        this.medicaoId = medicaoId;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public MedicaoListaModel toModel() {
        return new MedicaoListaModel(
                this.getResultado(),
                this.getTipoMedicao(),
                this.getDataHora(),
                this.medicaoId,
                this.sensorId
        );
    }

    public static MedicaoLista fromModel(MedicaoListaModel model) {
        return new MedicaoLista(
                model.getId(),
                model.getResultado(),
                model.getTipoMedicao(),
                model.getData_hora(),
                model.getMedicaoId(),
                model.getSensorId()
        );
    }
}