package org.example.applications;

import org.example.interfaces.PacienteRepository;
import org.example.models.PacienteModel;

import java.util.List;

public class PacienteApplication {
    private PacienteRepository pacienteRepository;

    public PacienteApplication(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteModel> buscarTodos() {
        return this.pacienteRepository.buscarTodos();
    }

    public PacienteModel buscarPorId(int id) {
        return this.pacienteRepository.buscarPorId(id);
    }

    public void adicionar(PacienteModel pacienteModel) {
        this.pacienteRepository.adicionar(pacienteModel);
    }

    public void excluir(int id) {
        this.pacienteRepository.excluir(id);
    }

    public void atualizar(int id, PacienteModel pacienteModel) {
        this.pacienteRepository.atualizar(id, pacienteModel);
    }
}
