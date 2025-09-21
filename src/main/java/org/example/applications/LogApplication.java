package org.example.applications;

import org.example.entities.Log;
import org.example.interfaces.LogRepository;

import java.util.List;

public class LogApplication {
    private final LogRepository logRepository;

    public LogApplication(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    public List<Log> buscarTodos(){
        return this.logRepository.buscarTodos();
    }

    public Log buscarPorId(int id){
        return this.logRepository.buscarPorId(id);
    }

    public void adicionar(Log log){
        this.logRepository.adicionar(log);
    }

    public void excluir(int id){
        this.logRepository.excluir(id);
    }

    public void atualizar(int id, Log log){
        this.logRepository.atualizar(id, log);
    }
}
