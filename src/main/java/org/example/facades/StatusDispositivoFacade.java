package org.example.facades;

import org.example.applications.StatusDispositivoApplication;
import org.example.entities.StatusDispositivo;
import org.example.entities.Unidade;
import org.example.models.StatusDispositivoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusDispositivoFacade {
    private final StatusDispositivoApplication statusDispositivoApplication;

    public StatusDispositivoFacade(StatusDispositivoApplication statusDispositivoApplication) {
        this.statusDispositivoApplication = statusDispositivoApplication;
    }

    public List<StatusDispositivoModel> buscarTodos() {
        return this.statusDispositivoApplication.buscarTodos();
    }

    public StatusDispositivoModel buscarPorId(int id) {
        return this.statusDispositivoApplication.buscarPorId(id);
    }

    public void adicionar(StatusDispositivoModel statusDispositivoModel) {
        this.statusDispositivoApplication.adicionar(statusDispositivoModel);
    }

    public void excluir(int id) {
        this.statusDispositivoApplication.excluir(id);
    }

    public void atualizar(int id, StatusDispositivoModel statusDispositivoModel) {
        this.statusDispositivoApplication.atualizar(id, statusDispositivoModel);
    }
}