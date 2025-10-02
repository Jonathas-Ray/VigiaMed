package org.example.applications;

import org.example.models.LogModel;
import org.example.interfaces.LogRepository;

import java.util.List;

public class LogApplication {
    private final LogRepository logRepository;

    public LogApplication(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    public List<LogModel> buscarTodos(){
        return this.logRepository.buscarTodos();
    }

    public LogModel buscarPorId(int id){
        return this.logRepository.buscarPorId(id);
    }

    public void adicionar(LogModel logModel){
        this.logRepository.adicionar(logModel);
    }

    public void excluir(int id){
        this.logRepository.excluir(id);
    }

    public void atualizar(int id, LogModel logModel){
        this.logRepository.atualizar(id, logModel);
    }
}
