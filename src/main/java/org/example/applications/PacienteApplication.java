package org.example.applications;

import org.example.interfaces.PacienteRepository;
import org.example.models.PacienteModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.List;

@Service
public class PacienteApplication {
    private final PacienteRepository pacienteRepository;

    public PacienteApplication(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteModel> buscarTodos() {
        return this.pacienteRepository.buscarTodos();
    }

    public PacienteModel buscarPorId(int id) {
        return this.pacienteRepository.buscarPorId(id);
    }

<<<<<<< HEAD
    public void adicionar(PacienteModel paciente) {
        this.pacienteRepository.adicionar(paciente);
=======
    public void adicionar(PacienteModel pacienteModel) {
        this.pacienteRepository.adicionar(pacienteModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.pacienteRepository.excluir(id);
    }

<<<<<<< HEAD
    public void atualizar(int id, PacienteModel paciente) {
        this.pacienteRepository.atualizar(id, paciente);
=======
    public void atualizar(int id, PacienteModel pacienteModel) {
        this.pacienteRepository.atualizar(id, pacienteModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}
