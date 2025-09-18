package org.example.interfaces;

import org.example.entities.Paciente;

import java.util.List;

public interface PacienteRepository {
    public List<Paciente> buscarTodos();
    public Paciente buscarPorId(int id);
    public void adicionar(Paciente Paciente);
    public void excluir(int id);
    public void atualizar(int id, Paciente Paciente);
}