package org.example.facades;

import org.example.applications.StatusDispositivoApplication;
import org.example.entities.StatusDispositivo;
import org.example.entities.Unidade;

import java.util.List;

public class StatusDispositivoFacade {
    private final StatusDispositivoApplication statusDispositivoApplication;

    public StatusDispositivoFacade(StatusDispositivoApplication statusDispositivoApplication) {
        this.statusDispositivoApplication = statusDispositivoApplication;
    }

    public List<StatusDispositivo> buscarTodos() {
        return this.statusDispositivoApplication.buscarTodos();
    }

    public StatusDispositivo buscarPorId(int id) {
        return this.statusDispositivoApplication.buscarPorId(id);
    }

    public void adicionar(StatusDispositivo statusDispositivo) {
        this.statusDispositivoApplication.adicionar(statusDispositivo);
    }

    public void excluir(int id) {
        this.statusDispositivoApplication.excluir(id);
    }

    public void atualizar(int id, StatusDispositivo statusDispositivo) {
        this.statusDispositivoApplication.atualizar(id, statusDispositivo);
    }
}