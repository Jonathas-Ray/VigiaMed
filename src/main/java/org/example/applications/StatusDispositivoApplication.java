package org.example.applications;

import org.example.interfaces.StatusDispositivoRepository;
import org.example.models.StatusDispositivoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusDispositivoApplication {
    private StatusDispositivoRepository statusDispositivoRepository;

    public StatusDispositivoApplication(StatusDispositivoRepository statusDispositivoRepository){
        this.statusDispositivoRepository = statusDispositivoRepository;
    }

    public List<StatusDispositivoModel> buscarTodos(){
        return this.statusDispositivoRepository.buscarTodos();
    }

    public StatusDispositivoModel buscarPorId(int id) {
        return this.statusDispositivoRepository.buscarPorId(id);
    }

    public void adicionar(StatusDispositivoModel statusDispositivoModel){
        this.statusDispositivoRepository.adicionar(statusDispositivoModel);
    }

    public void excluir(int id){
        this.statusDispositivoRepository.excluir(id);
    }

    public void atualizar(int id, StatusDispositivoModel statusDispositivoModel) {
        this.statusDispositivoRepository.atualizar(id, statusDispositivoModel);
    }
}
