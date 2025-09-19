package org.example.facades;

import org.example.applications.DispositivoApplication;
import org.example.entities.Dispositivo;

import java.util.List;

public class DispositivoFacade {
    private final DispositivoApplication dispositivoApplication;

    public DispositivoFacade(DispositivoApplication dispositivoApplication) {
        this.dispositivoApplication = dispositivoApplication;
    }

    public List<Dispositivo> buscarTodos() {
        return this.dispositivoApplication.buscarTodos();
    }

    public Dispositivo buscarPorId(int id) {
        return this.dispositivoApplication.buscarPorId(id);
    }

    public void adicionar(Dispositivo dispositivo) {
        this.dispositivoApplication.adicionar(dispositivo);
    }

    public void excluir(int id) {
        this.dispositivoApplication.excluir(id);
    }
}
