package org.example.repositories;

import org.example.interfaces.PacienteModelRepositoryJpa;
import org.example.interfaces.PacienteRepository;
import org.example.models.PacienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacienteRepositoryJpa implements PacienteRepository {

    private final PacienteModelRepositoryJpa pacienteModelRepositoryJpa;

    @Autowired
    public PacienteRepositoryJpa(PacienteModelRepositoryJpa pacienteModelRepositoryJpa, PacienteModelRepositoryJpa pacienteModelRepositoryJpa1){
        this.pacienteModelRepositoryJpa = pacienteModelRepositoryJpa1;
    }

    @Override
    public List<PacienteModel> buscarTodos() {
        return this.pacienteModelRepositoryJpa.findAll();
    }

    @Override
    public PacienteModel buscarPorId(int id) {
        return this.pacienteModelRepositoryJpa.findById(id).get();
    }

    @Override
    public void adicionar(PacienteModel paciente) {
        this.pacienteModelRepositoryJpa.save(paciente);
    }

    @Override
    public void excluir(int id) {
        this.pacienteModelRepositoryJpa.deleteById(id);
    }

    @Override
    public void atualizar(int id, PacienteModel paciente) {
        paciente.setId(id);
        this.pacienteModelRepositoryJpa.save(paciente);
    }
}
