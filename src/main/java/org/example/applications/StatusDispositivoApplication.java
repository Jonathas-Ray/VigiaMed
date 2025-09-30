package org.example.applications;

import org.example.entities.StatusDispositivo;
import org.example.interfaces.StatusDispositivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusDispositivoApplication {
    private StatusDispositivoRepository statusDispositivoRepository;

    public StatusDispositivoApplication(StatusDispositivoRepository statusDispositivoRepository){
        this.statusDispositivoRepository = statusDispositivoRepository;
    }

    public List<StatusDispositivo> buscarTodos(){
        return this.statusDispositivoRepository.buscarTodos();
    }

    public StatusDispositivo buscarPorId(int id) {
        return this.statusDispositivoRepository.buscarPorId(id);
    }

    public void adicionar(StatusDispositivo statusDispositivo){
        this.statusDispositivoRepository.adicionar(statusDispositivo);
    }

    public void excluir(int id){
        this.statusDispositivoRepository.excluir(id);
    }

    public void atualizar(int id, StatusDispositivo statusDispositivo) {
        this.statusDispositivoRepository.atualizar(id, statusDispositivo);
    }
}
