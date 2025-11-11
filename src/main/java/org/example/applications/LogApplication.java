package org.example.applications;

import org.example.entities.Log;
import org.example.interfaces.LogRepository;
import org.example.models.LogModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.ArrayList;
import java.util.List;

@Service
public class LogApplication {
    private final LogRepository logRepository;

    public LogApplication(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public List<LogModel> buscarTodos(){
        return this.logRepository.buscarTodos();
    }

    public LogModel buscarPorId(int id){
        return this.logRepository.buscarPorId(id);
    }

    public void adicionar(LogModel log){
        this.logRepository.adicionar(log);
=======
    public List<LogModel> buscarTodos() {
        return this.logRepository.buscarTodos();
=======
    public List<Log> buscarTodos() {
        List<LogModel> modelList = this.logRepository.buscarTodos();
        List<Log> entitieList = new ArrayList<>();
        for(LogModel logModel : modelList) {
            entitieList.add(new Log().fromModel(logModel));
        }
        return entitieList;
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
    }

    public Log buscarPorId(int id) {
        Log log = new Log().fromModel(this.logRepository.buscarPorId(id));
        return log;
    }

    public void adicionar(Log log) {
        LogModel logModel = log.toModel();
        this.logRepository.adicionar(logModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.logRepository.excluir(id);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void atualizar(int id, LogModel log){
        this.logRepository.atualizar(id, log);
=======
    public void atualizar(int id, LogModel logModel) {
=======
    public void atualizar(int id, Log log){
        LogModel logModel = log.toModel();
>>>>>>> 2857621346484ba555ef36c741558c8d17b482d9
        this.logRepository.atualizar(id, logModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}