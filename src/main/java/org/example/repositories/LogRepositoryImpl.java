package org.example.repositories;

import org.example.models.LogModel;
import org.example.interfaces.LogRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LogRepositoryImpl implements LogRepository {
    private List<LogModel> log = new ArrayList<>();

    public List<LogModel> buscarTodos(){
        return log;
    }

    public LogModel buscarPorId(int id){
        return log
                .stream()
                .filter(l->l.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar(LogModel log){
        this.log.add(log);
    }

    public void excluir(int id){
        this.log.removeIf(l -> l.getId() == id);
    }

    public void atualizar(int id, LogModel log){
        LogModel logExist = buscarPorId(id);
        if (logExist != null) {
            logExist.setAcao(logExist.getAcao());
            logExist.setDescricao(logExist.getDescricao());
            logExist.setData(logExist.getData());
        }
    }
}
