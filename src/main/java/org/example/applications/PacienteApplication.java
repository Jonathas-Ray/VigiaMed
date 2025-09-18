package org.example.applications;

import org.example.interfaces.PacienteRepository;
import org.example.entities.Paciente;

import java.util.List;

public class PacienteApplication {
    private PacienteRepository pacienteRepository;

    public PacienteApplication(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> buscarTodos() {
        return this.pacienteRepository.buscarTodos();
    }

    public Paciente buscarPorId(int id) {
        return this.pacienteRepository.buscarPorId(id);
    }

    public void adicionar(Paciente paciente) {
        this.pacienteRepository.adicionar(paciente);
    }

    public void excluir(int id) {
        this.pacienteRepository.excluir(id);
    }

    public void atualizar(int id, Paciente paciente) {
        this.pacienteRepository.atualizar(id, paciente);
    }
}
