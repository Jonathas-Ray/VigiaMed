package org.example.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
public class PacienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String referencia;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<MedicaoModel> medicoes;

    public PacienteModel() {}

    public PacienteModel( String nome, String referencia, List<MedicaoModel> medicoes) {
        this.nome = nome;
        this.referencia = referencia;
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
