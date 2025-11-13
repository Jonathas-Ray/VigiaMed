package org.example.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
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
    @JsonManagedReference
    private List<MedicaoListaModel> medicoes;

    public SensorModel() {}

    public SensorModel( String nome, String unidadeMedida, List<MedicaoListaModel> medicoes) {
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
}
