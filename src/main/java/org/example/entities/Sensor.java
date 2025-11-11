package org.example.entities;

import org.example.models.SensorModel;

public class Sensor {
    private int id;
    private String nome;
    private String unidadeMedida;

    public Sensor(){}

    public Sensor(int id, String nome, String unidadeMedida) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public SensorModel toModel() {
        return new SensorModel(
                this.getNome(),
                this.getUnidadeMedida()
        );
    }

    public static Sensor fromModel(SensorModel model) {
        return new Sensor(
                model.getId(),
                model.getNome(),
                model.getUnidadeMedida()
        );
    }
}