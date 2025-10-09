package org.example.repositories;

import org.example.interfaces.PacienteRepository;
import org.example.models.PacienteModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

public class PacienteRepositoryImpl implements PacienteRepository {
    private List<PacienteModel> pacienteModels = new ArrayList<>();

    public List<PacienteModel> buscarTodos() {
        return pacienteModels;
    }

    public PacienteModel buscarPorId(int id) {
        return pacienteModels
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar(PacienteModel pacienteModel) {
        this.pacienteModels.add(pacienteModel);
    }

    public void excluir(int id) {
        this.pacienteModels.removeIf(l -> l.getId() == id);
    }

    public void atualizar(int id, PacienteModel pacienteModel) {
        PacienteModel pacienteInMemory = buscarPorId(id);
        pacienteInMemory.setNome(pacienteModel.getNome());
        pacienteInMemory.setReferencia(pacienteModel.getReferencia());
    }
}