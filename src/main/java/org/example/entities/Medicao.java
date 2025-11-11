package org.example.entities;

import org.example.models.MedicaoModel;

public class Medicao {
    private int id;
    private String descricao;
    private String dataHora;

    public Medicao(){}

    public Medicao(int id, String descricao, String dataHora) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }

    public MedicaoModel toModel() {
        return new MedicaoModel(
                this.getDescricao(),
                this.getDataHora()
        );
    }

    public static Medicao fromModel(MedicaoModel model) {
        return new Medicao(
                model.getId(),
                model.getDescricao(),
                model.getDataHora()
        );
    }
}