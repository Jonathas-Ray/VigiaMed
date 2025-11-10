package org.example.facades;

import org.example.applications.PacienteApplication;
<<<<<<< HEAD
<<<<<<< HEAD
import org.example.entities.Paciente;
import org.example.models.PacienteModel;
=======
import org.example.models.PacienteModel;
=======
import org.example.entities.Paciente;
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.List;

@Service
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

<<<<<<< HEAD
<<<<<<< HEAD
    public void adicionar(PacienteModel paciente) {
        this.pacienteApplication.adicionar(paciente);
=======
    public void adicionar(PacienteModel pacienteModel) {
        this.pacienteApplication.adicionar(pacienteModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
=======
    public void adicionar(Paciente paciente) {
        this.pacienteApplication.adicionar(paciente);
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
    }

    public void excluir(int id) {
        this.pacienteApplication.excluir(id);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void atualizar(int id, PacienteModel paciente) {
        this.pacienteApplication.atualizar(id, paciente);
=======
    public void atualizar(int id, PacienteModel pacienteModel) {
        this.pacienteApplication.atualizar(id, pacienteModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
=======
    public void atualizar(int id, Paciente paciente) {
        this.pacienteApplication.atualizar(id, paciente);
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
    }
}