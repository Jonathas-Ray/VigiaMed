package org.example.facades;

import org.example.applications.LogApplication;
import org.example.models.LogModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

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

<<<<<<< HEAD
    public void adicionar(LogModel log){
        this.logApplication.adicionar(log);
=======
    public void adicionar(LogModel logModel){
        this.logApplication.adicionar(logModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id){
        this.logApplication.excluir(id);
    }

<<<<<<< HEAD
    public void atualizar(int id, LogModel log){
        this.logApplication.atualizar(id, log);
=======
    public void atualizar(int id, LogModel logModel){
        this.logApplication.atualizar(id, logModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}
