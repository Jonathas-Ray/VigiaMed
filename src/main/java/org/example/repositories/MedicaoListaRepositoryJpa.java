package org.example.repositories;

import org.example.interfaces.MedicaoListaModelRepositoryJpa;
import org.example.interfaces.MedicaoListaRepository;
import org.example.models.MedicaoListaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicaoListaRepositoryJpa implements MedicaoListaRepository {

    private final MedicaoListaModelRepositoryJpa medicaoListaModelRepositoryJpa;

    @Autowired
    public MedicaoListaRepositoryJpa(MedicaoListaModelRepositoryJpa medicaoListaModelRepositoryJpa) {
        this.medicaoListaModelRepositoryJpa = medicaoListaModelRepositoryJpa;
    }

    @Override
    public List<MedicaoListaModel> buscarTodos() {
        return this.medicaoListaModelRepositoryJpa.findAll();
    }

    @Override
    public MedicaoListaModel buscarPorId(int id) {
        return this.medicaoListaModelRepositoryJpa.findById(id).orElse(null);
    }

    @Override
    public void adicionar(MedicaoListaModel listaMedicao) {
        this.medicaoListaModelRepositoryJpa.save(listaMedicao);
    }

    @Override
    public void excluir(int id) {
        this.medicaoListaModelRepositoryJpa.deleteById(id);
    }

    @Override
    public void atualizar(int id, MedicaoListaModel listaMedicao) {
        listaMedicao.setId(id);
        this.medicaoListaModelRepositoryJpa.save(listaMedicao);
    }

    // retorna o mais recente
    @Override
    public Double findUltimoResultado() {
        List<Double> lista = this.medicaoListaModelRepositoryJpa.findUltimoResultadoList();
        return lista.isEmpty() ? null : lista.get(0);
    }
}