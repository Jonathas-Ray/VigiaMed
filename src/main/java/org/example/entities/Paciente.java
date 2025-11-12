package org.example.entities;

import org.example.models.MedicaoModel;
import org.example.models.PacienteModel;
import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private int id;
    private String nome;
    private String referencia;
    private List<MedicaoModel> medicoes;

    public Paciente(){}

    public Paciente( int id, String nome, String referencia, List<MedicaoModel> medicoes) {
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

    public PacienteModel toModel() {
        return new PacienteModel(
                this.getNome(),
                this.getReferencia(),
                this.medicoes
        );
    }

    public static Paciente fromModel(PacienteModel model) {
        return new Paciente(
                model.getId(),
                model.getNome(),
                model.getReferencia(),
                model.getMedicoes()
        );
    }
}