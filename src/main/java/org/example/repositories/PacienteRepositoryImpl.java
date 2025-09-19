package org.example.repositories;

import org.example.entities.Paciente;
import org.example.interfaces.PacienteRepository;

import java.util.List;
import java.util.ArrayList;

public class PacienteRepositoryImpl implements PacienteRepository {
    private List<Paciente> pacientes = new ArrayList<>();

    public List<Paciente> buscarTodos() {
        return pacientes;
    }

    public Paciente buscarPorId(int id) {
        return pacientes
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar(Paciente paciente) {
        this.pacientes.add(paciente);
    }

    public void excluir(int id) {
        this.pacientes.removeIf(l -> l.getId() == id);
    }

    public void atualizar(int id, Paciente paciente) {
        Paciente pacienteInMemory = buscarPorId(id);

        pacienteInMemory.setNome(paciente.getNome());
        pacienteInMemory.setReferencia(paciente.getReferencia());
    }
}