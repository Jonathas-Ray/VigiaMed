package org.example.entities;

import org.example.models.SensorModel;

public class Sensor {
    private int id;
    private String nome;
    private String unidadeMedida;

    public Sensor(){}

    public Sensor(String nome, String unidadeMedida) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
    }

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
        SensorModel model = new SensorModel();
        model.setId(this.getId());
        model.setNome(this.getNome());
        model.setUnidadeMedida(this.getUnidadeMedida());
        return model;
    }

    public static Sensor fromModel(SensorModel model) {
        Sensor sensor = new Sensor(
                model.getNome(),
                model.getUnidadeMedida()
        );
        sensor.setId(model.getId());
        return sensor;
    }
}