package org.example.repositories;

import org.example.interfaces.PacienteRepository;
import org.example.models.PacienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacienteRepositoryJpa implements PacienteRepository {

    private final PacienteRepositoryJpa pacienteRepositoryJpa;

    @Autowired
    public PacienteRepositoryJpa(PacienteRepositoryJpa pacienteRepositoryJpa) {
        this.pacienteRepositoryJpa = pacienteRepositoryJpa;
    }
    
    @Override
    public List<PacienteModel> buscarTodos() { return List.of(); }

    @Override
    public PacienteModel buscarPorId(int id) { return null; }

    @Override
    public void adicionar(PacienteModel Paciente) { }

    @Override
    public void excluir(int id) { }

    @Override
    public void atualizar(int id, PacienteModel Paciente) { }
}
