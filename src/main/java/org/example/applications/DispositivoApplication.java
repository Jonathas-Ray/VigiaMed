package org.example.applications;

import org.example.interfaces.DispositivoRepository;
import org.example.entities.Dispositivo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoApplication {
    private DispositivoRepository dispositivoRepository;

    public DispositivoApplication(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    public List<Dispositivo> buscarTodos() {
        return this.dispositivoRepository.buscarTodos();
    }

    public Dispositivo buscarPorId(int id) {
        return this.dispositivoRepository.buscarPorId(id);
    }

    public void adicionar(Dispositivo dispositivo) {
        this.dispositivoRepository.adicionar(dispositivo);
    }

    public void excluir(int id) {
        this.dispositivoRepository.excluir(id);
    }
}
