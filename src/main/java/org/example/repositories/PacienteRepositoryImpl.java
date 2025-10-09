package org.example.repositories;

import org.example.interfaces.PacienteRepository;
import org.example.models.PacienteModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class PacienteRepositoryImpl implements PacienteRepository {
    private final List<PacienteModel> pacientes = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<PacienteModel> buscarTodos() {
        return pacientes;
    }

    public PacienteModel buscarPorId(int id) {
        for (PacienteModel paciente : pacientes) {
            if (paciente.getId() == id) {
                return paciente;
            }
        }
        return null;
    }

    public void adicionar(PacienteModel paciente) {
        if (paciente.getId() == 0) {
            paciente.setId(idCounter.getAndIncrement());
        }
        pacientes.add(paciente);
    }

    public void excluir(int id) {
        PacienteModel pacienteRemover = buscarPorId(id);
        if (pacienteRemover != null) {
            pacientes.remove(pacienteRemover);
        }
    }

    public void atualizar(int id, PacienteModel paciente) {
        PacienteModel existente = buscarPorId(id);
        if (existente != null) {
            existente.setNome(paciente.getNome());
            existente.setReferencia(paciente.getReferencia());
        }
    }
}
