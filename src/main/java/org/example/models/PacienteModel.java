package org.example.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Paciente")
public class PacienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String referencia;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<MedicaoModel> medicoes;

    public PacienteModel() {}

    public PacienteModel(int id, String nome, String referencia) {
        this.id = id;
        this.nome = nome;
        this.referencia = referencia;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public List<MedicaoModel> getMedicoes() {
        return medicoes;
    }

    public void setMedicoes(List<MedicaoModel> medicoes) {
        this.medicoes = medicoes;
    }
}
