package org.example.models;

public class MedicaoListaModel {
    private int id;
    private double resultado;
    private String tipoMedicao;
    private String data_hora;

    public MedicaoListaModel(int id, double resultado, String tipoMedicao, String data_hora) {
        this.id = id;
        this.resultado = resultado;
        this.tipoMedicao = tipoMedicao;
        this.data_hora = data_hora;
    }

    public int getId() {
        return id;
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
