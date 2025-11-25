package org.example.entities;

import org.example.models.MedicaoListaModel;
import org.example.models.SensorModel;

import java.util.ArrayList;
import java.util.List;

public class Sensor {
    private int id;
    private String nome;
    private String unidadeMedida;
    private List<MedicaoListaModel> medicoes;

    public Sensor(){}

    public Sensor(int id, String nome, String unidadeMedida, List<MedicaoListaModel> medicoes) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        medicoes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public List<MedicaoListaModel> getMedicoes() {
        return medicoes;
    }

    public void setMedicoes(List<MedicaoListaModel> medicoes) {
        this.medicoes = medicoes;
    }

    public SensorModel toModel() {
        return new SensorModel(
                this.getNome(),
                this.getUnidadeMedida(),
                this.medicoes
        );
    }

    public static Sensor fromModel(SensorModel model) {
        return new Sensor(
                model.getId(),
                model.getNome(),
                model.getUnidadeMedida(),
                model.getMedicoes()
        );
    }
}