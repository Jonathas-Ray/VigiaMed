package org.example.facades;

import org.example.applications.PacienteApplication;
import org.example.entities.Paciente;

import java.util.List;

public class PacienteFacade {
    private final PacienteApplication pacienteApplication;

    public PacienteFacade(PacienteApplication pacienteApplication) {
        this.pacienteApplication = pacienteApplication;
    }

    public List<Paciente> buscarTodos() {
        return this.pacienteApplication.buscarTodos();
    }

    public Paciente buscarPorId(int id) {
        return this.pacienteApplication.buscarPorId(id);
    }

    public void adicionar(Paciente paciente) {
        this.pacienteApplication.adicionar(paciente);
    }

    public void excluir(int id) {
        this.pacienteApplication.excluir(id);
    }

    public void atualizar(int id, Paciente paciente) {
        this.pacienteApplication.atualizar(id, paciente);
    }
}
