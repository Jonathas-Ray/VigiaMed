package org.example.repositories;

import org.example.models.LogModel;
import org.example.interfaces.LogRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class LogRepositoryImpl implements LogRepository {
    private final List<LogModel> log = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);
    
    public List<LogModel> buscarTodos(){
        return log;
    }

    public LogModel buscarPorId(int id){
        for (LogModel logModel : log){
            if (logModel.getId() == id){
                return logModel;
            }
        }
        return null;
    }

    public void adicionar(LogModel log){
        if (log.getId() == 0){
            log.setId(idCounter.getAndIncrement());
        }
        this.log.add(log);
    }

    public void excluir(int id) {
        LogModel logParaRemover = null;
        for (LogModel log : log) {
            if (log.getId() == id) {
                logParaRemover = log;
                break;
            }
        }
        if (logParaRemover != null) {
            log.remove(logParaRemover);
        }
    }
    
    public void atualizar(int id, LogModel log){
        LogModel logExist = buscarPorId(id);
        if (logExist != null) {
            logExist.setAcao(log.getAcao());
            logExist.setDescricao(log.getDescricao());
            logExist.setData(log.getData());
        }
    }
}
