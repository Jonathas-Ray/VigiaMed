package org.example.repositories;

import org.example.entities.Dispositivo;
import org.example.interfaces.DispositivoRepository;

import java.util.ArrayList;
import java.util.List;

public class DispositivoRepositorylmpl implements DispositivoRepository {
    private List<Dispositivo> dispositivos = new ArrayList<>();

    public List<Dispositivo> buscarTodos() {
        return dispositivos;
    }

    public Dispositivo buscarPorId(int id) {
        return dispositivos
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar(Dispositivo dispositivo) {
        this.dispositivos.add(dispositivo);
    }

    public void excluir(int id) {
        this.dispositivos.removeIf(l -> l.getId() == id);
    }
}
