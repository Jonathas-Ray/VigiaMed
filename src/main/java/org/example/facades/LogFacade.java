package org.example.facades;

import org.example.applications.LogApplication;
import org.example.entities.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogFacade {
    private final LogApplication logApplication;

    public LogFacade(LogApplication logApplication){
        this.logApplication = logApplication;
    }

    public List<Log> buscarTodos(){
        return this.logApplication.buscarTodos();
    }

    public Log buscarPorId(int id){
        return this.logApplication.buscarPorId(id);
    }

    public void adicionar(Log log){
        this.logApplication.adicionar(log);
    }

    public void excluir(int id){
        this.logApplication.excluir(id);
    }

    public void atualizar(int id, Log log){
        this.logApplication.atualizar(id, log);
    }
}