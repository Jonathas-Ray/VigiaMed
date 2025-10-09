package org.example.facades;

import org.example.applications.LogApplication;
import org.example.models.LogModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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

    public void adicionar(LogModel logModel){
        this.logApplication.adicionar(logModel);
    }

    public void excluir(int id){
        this.logApplication.excluir(id);
    }

    public void atualizar(int id, LogModel logModel){
        this.logApplication.atualizar(id, logModel);
    }
}
