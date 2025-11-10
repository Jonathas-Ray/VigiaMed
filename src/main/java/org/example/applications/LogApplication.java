package org.example.applications;

import org.example.interfaces.LogRepository;
import org.example.models.LogModel;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Service;
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e

import java.util.List;

@Service
public class LogApplication {
    private final LogRepository logRepository;

    public LogApplication(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

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
    }

    public LogModel buscarPorId(int id) {
        return this.logRepository.buscarPorId(id);
    }

    public void adicionar(LogModel logModel) {
        this.logRepository.adicionar(logModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }

    public void excluir(int id) {
        this.logRepository.excluir(id);
    }

<<<<<<< HEAD
    public void atualizar(int id, LogModel log){
        this.logRepository.atualizar(id, log);
=======
    public void atualizar(int id, LogModel logModel) {
        this.logRepository.atualizar(id, logModel);
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
    }
}
