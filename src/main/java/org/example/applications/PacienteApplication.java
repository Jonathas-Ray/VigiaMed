package org.example.applications;

import org.example.entities.Paciente;
import org.example.interfaces.PacienteRepository;
import org.example.models.PacienteModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteApplication {
    private final PacienteRepository pacienteRepository;

    public PacienteApplication(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<Paciente> buscarTodos() {
        List<PacienteModel> modelList = this.pacienteRepository.buscarTodos();
        List<Paciente> entitieList = new ArrayList<>();
        for(PacienteModel pacienteModel : modelList) {
            entitieList.add(new Paciente().fromModel(pacienteModel));
        }
        return entitieList;
    }

    public Paciente buscarPorId(int id) {
        Paciente paciente = new Paciente().fromModel(this.pacienteRepository.buscarPorId(id));
        return paciente;
    }

    public void adicionar(Paciente paciente) {
        PacienteModel pacienteModel = paciente.toModel();
        this.pacienteRepository.adicionar(pacienteModel);
    }

    public void excluir(int id) {
        this.pacienteRepository.excluir(id);
    }

    public void atualizar(int id, Paciente paciente){
        PacienteModel pacienteModel = paciente.toModel();
        this.pacienteRepository.atualizar(id, pacienteModel);
    }
}