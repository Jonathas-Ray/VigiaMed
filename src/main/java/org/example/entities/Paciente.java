package org.example.entities;

import org.example.models.PacienteModel;

public class Paciente {
    private int id;
    private String nome;
    private String referencia;

    public Paciente(){}

    public Paciente( int id, String nome, String referencia) {
        this.nome = nome;
        this.referencia = referencia;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    public PacienteModel toModel() {
        return new PacienteModel(
                this.getNome(),
                this.getReferencia()
        );
    }

    public static Paciente fromModel(PacienteModel model) {
        return new Paciente(
                model.getId(),
                model.getNome(),
                model.getReferencia()
        );
    }
}