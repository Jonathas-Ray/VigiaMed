package org.example.applications;

import org.example.entities.Log;
import org.example.interfaces.LogRepository;
import org.example.models.LogModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogApplication {
    private final LogRepository logRepository;

    public LogApplication(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<Log> buscarTodos() {
        List<LogModel> modelList = this.logRepository.buscarTodos();
        List<Log> entitieList = new ArrayList<>();
        for(LogModel logModel : modelList) {
            entitieList.add(new Log().fromModel(logModel));
        }
        return entitieList;
    }

    public Log buscarPorId(int id) {
        Log log = new Log().fromModel(this.logRepository.buscarPorId(id));
        return log;
    }

    public void adicionar(Log log) {
        LogModel logModel = log.toModel();
        this.logRepository.adicionar(logModel);
    }

    public void excluir(int id) {
        this.logRepository.excluir(id);
    }

    public void atualizar(int id, Log log){
        LogModel logModel = log.toModel();
        this.logRepository.atualizar(id, logModel);
    }
}