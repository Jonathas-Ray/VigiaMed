package org.example.facades;

import org.example.applications.LogApplication;
<<<<<<< HEAD
import org.example.models.LogModel;
<<<<<<< HEAD
=======
=======
import org.example.entities.Log;
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

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

<<<<<<< HEAD
<<<<<<< HEAD
    public void adicionar(LogModel log){
        this.logApplication.adicionar(log);
=======
    public void adicionar(LogModel logModel){
        this.logApplication.adicionar(logModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
=======
    public void adicionar(Log log){
        this.logApplication.adicionar(log);
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
    }

    public void excluir(int id){
        this.logApplication.excluir(id);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void atualizar(int id, LogModel log){
        this.logApplication.atualizar(id, log);
=======
    public void atualizar(int id, LogModel logModel){
        this.logApplication.atualizar(id, logModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
=======
    public void atualizar(int id, Log log){
        this.logApplication.atualizar(id, log);
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
    }
}