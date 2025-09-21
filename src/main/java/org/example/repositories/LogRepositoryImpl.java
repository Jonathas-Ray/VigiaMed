package org.example.repositories;

import org.example.entities.Log;
import org.example.interfaces.LogRepository;

import java.util.ArrayList;
import java.util.List;

public class LogRepositoryImpl implements LogRepository {
    private List<Log> log = new ArrayList<>();

    public List<Log> buscarTodos(){
        return log;
    }

    public Log buscarPorId(int id){
        return log
                .stream()
                .filter(l->l.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar(Log log){
        this.log.add(log);
    }

    public void excluir(int id){
        this.log.removeIf(l -> l.getId() == id);
    }

    public void atualizar(int id, Log log){
        Log logExist = buscarPorId(id);
        if (logExist != null) {
            logExist.setAcao(logExist.getAcao());
            logExist.setDescricao(logExist.getDescricao());
            logExist.setData(logExist.getData());
        }
    }
}
