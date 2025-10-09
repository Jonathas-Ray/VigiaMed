package org.example.applications;

import org.example.interfaces.MedicaoListaRepository;
import org.example.models.MedicaoListaModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicaoListaApplication {
    private final MedicaoListaRepository medicaoListaRepository;

    public MedicaoListaApplication(MedicaoListaRepository medicaoListaRepository) {
        this.medicaoListaRepository = medicaoListaRepository;
    }

    public List<MedicaoListaModel> buscarTodos() {
        return this.medicaoListaRepository.buscarTodos();
    }

    public MedicaoListaModel buscarPorId(int id) {
        return this.medicaoListaRepository.buscarPorId(id);
    }

    public void adicionar(MedicaoListaModel medicaoListaModel) {
        this.medicaoListaRepository.adicionar(medicaoListaModel);
    }

    public void excluir(int id) {
        this.medicaoListaRepository.excluir(id);
    }

    public void atualizar(int id, MedicaoListaModel medicaoListaModel) {
        this.medicaoListaRepository.atualizar(id, medicaoListaModel);
    }
}
