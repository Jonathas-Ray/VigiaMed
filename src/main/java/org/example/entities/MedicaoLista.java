package org.example.entities;

import org.example.models.MedicaoListaModel;

public class MedicaoLista {
    private int id;
    private double resultado;
    private String tipoMedicao;
    private String data_hora;

    public MedicaoLista(){}

    public MedicaoLista(double resultado, String tipoMedicao, String data_hora) {
        this.resultado = resultado;
        this.tipoMedicao = tipoMedicao;
        this.data_hora = data_hora;
    }

    public MedicaoLista(int id, double resultado, String tipoMedicao, String data_hora) {
        this.id = id;
        this.resultado = resultado;
        this.tipoMedicao = tipoMedicao;
        this.data_hora = data_hora;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getResultado() { return resultado; }
    public void setResultado(double resultado) { this.resultado = resultado; }
    public String getTipoMedicao() { return tipoMedicao; }
    public void setTipoMedicao(String tipoMedicao) { this.tipoMedicao = tipoMedicao; }
    public String getData_hora() { return data_hora; }
    public void setData_hora(String data_hora) { this.data_hora = data_hora; }

    public MedicaoListaModel toModel() {
        MedicaoListaModel model = new MedicaoListaModel();
        model.setId(this.getId());
        model.setResultado(this.getResultado());
        model.setTipoMedicao(this.getTipoMedicao());
        model.setData_hora(this.getData_hora());
        return model;
    }

    public static MedicaoLista fromModel(MedicaoListaModel model) {
        MedicaoLista medicaoLista = new MedicaoLista(
                model.getResultado(),
                model.getTipoMedicao(),
                model.getData_hora()
        );
        medicaoLista.setId(model.getId());
        return medicaoLista;
    }
}