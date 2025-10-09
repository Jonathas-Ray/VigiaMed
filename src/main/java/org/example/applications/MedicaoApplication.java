package org.example.applications;

import org.example.interfaces.MedicaoRepository;
import org.example.models.MedicaoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicaoApplication {
    private final MedicaoRepository medicaoRepository;

    public MedicaoApplication(MedicaoRepository medicaoRepository) {
        this.medicaoRepository = medicaoRepository;
    }

    public List<MedicaoModel> buscarTodos() {
        return this.medicaoRepository.buscarTodos();
    }

    public MedicaoModel buscarPorId(int id) {
        return this.medicaoRepository.buscarPorId(id);
    }

    public void adicionar(MedicaoModel medicaoModel) {
        this.medicaoRepository.adicionar(medicaoModel);
    }

    public void excluir(int id) {
        this.medicaoRepository.excluir(id);
    }

    public void atualizar(int id, MedicaoModel medicaoModel) {
        this.medicaoRepository.atualizar(id, medicaoModel);
    }
}
