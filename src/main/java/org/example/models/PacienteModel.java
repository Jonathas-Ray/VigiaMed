package org.example.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Paciente")
public class PacienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String referencia;

    public PacienteModel(int id, String nome, String referencia) {
        this.id = id;
        this.nome = nome;
        this.referencia = referencia;
    }

    public PacienteModel(){}

    public long getId() { return id; }

    public void setId(int id) { this.id = id; }

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
}