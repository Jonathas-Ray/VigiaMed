package org.example.interfaces;

import org.example.models.PacienteModel;

import java.util.List;

public interface PacienteRepository {
    public List<PacienteModel> buscarTodos();
    public PacienteModel buscarPorId(int id);
    public void adicionar(PacienteModel Paciente);
    public void excluir(int id);
    public void atualizar(int id, PacienteModel Paciente);
}