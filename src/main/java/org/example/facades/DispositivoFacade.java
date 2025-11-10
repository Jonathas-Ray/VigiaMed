package org.example.facades;

import org.example.applications.DispositivoApplication;
import org.example.models.DispositivoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoFacade {
    private final DispositivoApplication dispositivoApplication;

    public DispositivoFacade(DispositivoApplication dispositivoApplication) {
        this.dispositivoApplication = dispositivoApplication;
    }

    public List<DispositivoModel> buscarTodos() {
        return this.dispositivoApplication.buscarTodos();
    }

    public DispositivoModel buscarPorId(int id) {
        return this.dispositivoApplication.buscarPorId(id);
    }

    public void adicionar(DispositivoModel dispositivoModel) {
        this.dispositivoApplication.adicionar(dispositivoModel);
    }

    public void excluir(int id) {
        this.dispositivoApplication.excluir(id);
    }

    public void atualizar(int id, DispositivoModel dispositivoModel){
        this.dispositivoApplication.atualizar(id, dispositivoModel);
    }
}
