package org.example.applications;

import org.example.entities.StatusDispositivo;
import org.example.interfaces.StatusDispositivoRepository;
import org.example.models.StatusDispositivoModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusDispositivoApplication {
    private StatusDispositivoRepository statusDispositivoRepository;

    public StatusDispositivoApplication(StatusDispositivoRepository statusDispositivoRepository){
        this.statusDispositivoRepository = statusDispositivoRepository;
    }

    public List<StatusDispositivo> buscarTodos(){
        List<StatusDispositivoModel> modelList = this.statusDispositivoRepository.buscarTodos();
        List<StatusDispositivo> entitieList = new ArrayList<>();
        for(StatusDispositivoModel statusDispositivoModel : modelList) {
            entitieList.add(new StatusDispositivo().fromModel(statusDispositivoModel));
        }
        return entitieList;
    }

    public StatusDispositivo buscarPorId(int id) {
        StatusDispositivo statusDispositivo = new StatusDispositivo().fromModel(this.statusDispositivoRepository.buscarPorId(id));
        return statusDispositivo;
    }

    public void adicionar(StatusDispositivo statusDispositivo){
        StatusDispositivoModel statusDispositivoModel = statusDispositivo.toModel();
        this.statusDispositivoRepository.adicionar(statusDispositivoModel);
    }

    public void excluir(int id){
        this.statusDispositivoRepository.excluir(id);
    }

    public void atualizar(int id, StatusDispositivo statusDispositivo) {
        StatusDispositivoModel statusDispositivoModel = statusDispositivo.toModel();
        this.statusDispositivoRepository.atualizar(id, statusDispositivoModel);
    }
}