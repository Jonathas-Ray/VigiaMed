package org.example.facades;

import org.example.applications.PacienteApplication;
<<<<<<< HEAD
import org.example.entities.Paciente;
import org.example.models.PacienteModel;
=======
import org.example.models.PacienteModel;
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.List;

@Service
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

<<<<<<< HEAD
    public void adicionar(PacienteModel paciente) {
        this.pacienteApplication.adicionar(paciente);
=======
    public void adicionar(PacienteModel pacienteModel) {
        this.pacienteApplication.adicionar(pacienteModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.pacienteApplication.excluir(id);
    }

<<<<<<< HEAD
    public void atualizar(int id, PacienteModel paciente) {
        this.pacienteApplication.atualizar(id, paciente);
=======
    public void atualizar(int id, PacienteModel pacienteModel) {
        this.pacienteApplication.atualizar(id, pacienteModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}
