package org.example.facades;

import org.example.applications.PacienteApplication;
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

    public void adicionar(PacienteModel pacienteModel) {
        this.pacienteApplication.adicionar(pacienteModel);
    }

    public void excluir(int id) {
        this.pacienteApplication.excluir(id);
    }

    public void atualizar(int id, PacienteModel pacienteModel) {
        this.pacienteApplication.atualizar(id, pacienteModel);
    }
}
