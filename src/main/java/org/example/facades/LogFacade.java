package org.example.facades;

import org.example.applications.LogApplication;
import org.example.models.LogModel;

import java.util.List;

public class LogFacade {
    private final LogApplication logApplication;

    public LogFacade(LogApplication logApplication){
        this.logApplication = logApplication;
    }

    public List<LogModel> buscarTodos(){
        return this.logApplication.buscarTodos();
    }

    public LogModel buscarPorId(int id){
        return this.logApplication.buscarPorId(id);
    }

    public void adicionar(LogModel log){
        this.logApplication.adicionar(log);
    }

    public void excluir(int id){
        this.logApplication.excluir(id);
    }

    public void atualizar(int id, LogModel log){
        this.logApplication.atualizar(id, log);
    }
}
