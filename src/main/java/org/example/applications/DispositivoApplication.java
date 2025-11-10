package org.example.applications;

import org.example.interfaces.DispositivoRepository;
import org.example.models.DispositivoModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoApplication {
    private DispositivoRepository dispositivoRepository;

    public DispositivoApplication(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    public List<DispositivoModel> buscarTodos() {
        return this.dispositivoRepository.buscarTodos();
    }

    public DispositivoModel buscarPorId(int id) {
        return this.dispositivoRepository.buscarPorId(id);
    }

    public void adicionar(DispositivoModel dispositivoModel) {
        this.dispositivoRepository.adicionar(dispositivoModel);
    }

    public void excluir(int id) {
        this.dispositivoRepository.excluir(id);
    }

    public void atualizar(int id, DispositivoModel dispositivoModel){
        this.dispositivoRepository.atualizar(id, dispositivoModel);
    }
}
