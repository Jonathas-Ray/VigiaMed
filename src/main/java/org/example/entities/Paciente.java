package org.example.entities;

import org.example.models.PacienteModel;

public class Paciente {
    private int id;
    private String nome;
    private String referencia;

    public Paciente(){}

    public Paciente(String nome, String referencia) {
        this.nome = nome;
        this.referencia = referencia;
    }

    public Paciente(int id, String nome, String referencia) {
        this.id = id;
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
        PacienteModel model = new PacienteModel();
        model.setId(this.getId());
        model.setNome(this.getNome());
        model.setReferencia(this.getReferencia());
        return model;
    }

    public static Paciente fromModel(PacienteModel model) {
        Paciente paciente = new Paciente(
                model.getNome(),
                model.getReferencia()
        );
        paciente.setId(model.getId());
        return paciente;
    }
}