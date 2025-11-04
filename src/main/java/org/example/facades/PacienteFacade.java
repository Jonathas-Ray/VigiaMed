package org.example.facades;

import org.example.applications.PacienteApplication;
import org.example.entities.Paciente;
import org.example.models.PacienteModel;

import java.util.List;

public class PacienteFacade {
    private final PacienteApplication pacienteApplication;

    public PacienteFacade(PacienteApplication pacienteApplication) {
        this.pacienteApplication = pacienteApplication;
    }

    public List<PacienteModel> buscarTodos() {
        return this.pacienteApplication.buscarTodos();
    }

    public PacienteModel buscarPorId(int id) {
        return this.pacienteApplication.buscarPorId(id);
    }

    public void adicionar(PacienteModel paciente) {
        this.pacienteApplication.adicionar(paciente);
    }

    public void excluir(int id) {
        this.pacienteApplication.excluir(id);
    }

    public void atualizar(int id, PacienteModel paciente) {
        this.pacienteApplication.atualizar(id, paciente);
    }
}
