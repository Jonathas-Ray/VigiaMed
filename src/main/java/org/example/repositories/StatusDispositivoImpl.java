package org.example.repositories;

import org.example.interfaces.StatusDispositivoRepository;
import org.example.models.StatusDispositivoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//
public class StatusDispositivoImpl implements StatusDispositivoRepository {
    private final List<StatusDispositivoModel> statusDispositivoList = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<StatusDispositivoModel> buscarTodos(){
        return statusDispositivoList;
    }

    public StatusDispositivoModel buscarPorId(int id) {
        for(StatusDispositivoModel statusDispositivoModel : statusDispositivoList){
           if(statusDispositivoModel.getId() == id){
               return statusDispositivoModel;
           }
        }
        return null;
    }

    public void adicionar(StatusDispositivoModel statusDispositivo) {
        if(statusDispositivo.getId() == 0){
            statusDispositivo.setId(idCounter.getAndIncrement());
        }
        this.statusDispositivoList.add(statusDispositivo);
    }

    public void excluir(int id) {
        for(StatusDispositivoModel statusDispositivoModel : statusDispositivoList){
            if (statusDispositivoModel.getId() == id){
                statusDispositivoList.remove(statusDispositivoModel);
            }
        }
    }

    public void atualizar(int id, StatusDispositivoModel statusDispositivoModel) {
        StatusDispositivoModel statuInMemory = buscarPorId(id);

        statuInMemory.setEstado(statusDispositivoModel.getEstado());
    }
}
