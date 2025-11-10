package org.example.applications;

import org.example.entities.Paciente;
import org.example.interfaces.PacienteRepository;
import org.example.models.PacienteModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

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

<<<<<<< HEAD
<<<<<<< HEAD
    public void adicionar(PacienteModel paciente) {
        this.pacienteRepository.adicionar(paciente);
=======
    public void adicionar(PacienteModel pacienteModel) {
=======
    public void adicionar(Paciente paciente) {
        PacienteModel pacienteModel = paciente.toModel();
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
        this.pacienteRepository.adicionar(pacienteModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.pacienteRepository.excluir(id);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void atualizar(int id, PacienteModel paciente) {
        this.pacienteRepository.atualizar(id, paciente);
=======
    public void atualizar(int id, PacienteModel pacienteModel) {
=======
    public void atualizar(int id, Paciente paciente){
        PacienteModel pacienteModel = paciente.toModel();
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
        this.pacienteRepository.atualizar(id, pacienteModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}