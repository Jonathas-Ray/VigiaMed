package org.example.models;


import jakarta.persistence.*;

@Entity
@Table(name = "Sensor")
public class SensorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String unidadeMedida;

    public SensorModel(long id, String nome, String unidadeMedida) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
    }

    public SensorModel(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return "SensorModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", unidadeMedida='" + unidadeMedida + '\'' +
                '}';
    }
}