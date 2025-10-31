package org.example.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Sensor")
public class SensorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String unidadeMedida;

    // Um sensor pode ter várias medições
    @OneToMany(mappedBy = "sensorModel", cascade = CascadeType.ALL)
    private List<MedicaoListaModel> medicoes;

    public SensorModel() {}

    public SensorModel(int id, String nome, String unidadeMedida) {
        this.id = id;
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public List<MedicaoListaModel> getMedicoes() { return medicoes; }
    public void setMedicoes(List<MedicaoListaModel> medicoes) { this.medicoes = medicoes; }
}
