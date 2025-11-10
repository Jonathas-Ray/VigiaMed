package org.example.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sensor")
public class SensorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String unidadeMedida;

    @OneToMany(mappedBy = "sensorModel", cascade = CascadeType.ALL)
    private List<MedicaoListaModel> medicoes;

    public SensorModel() {}

    public SensorModel(int id, String nome, String unidadeMedida) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
    }

<<<<<<< HEAD
    public SensorModel(){}

    public long getId() {return id;}
=======
    public int getId() {
        return id;
    }
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

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

    public void setMedicoes(List<MedicaoListaModel> medicoes) {
        this.medicoes = medicoes;
    }
}
