package org.example.repositories;

import org.example.entities.Paciente;
import org.example.entities.StatusDispositivo;
import org.example.entities.Unidade;
import org.example.entities.Usuario;
import org.example.interfaces.StatusDispositivoRepository;

import java.util.ArrayList;
import java.util.List;

public class StatusDispositivolmpl implements StatusDispositivoRepository {
    private List<StatusDispositivo> statusDispositivoList = new ArrayList<>();

    public List<StatusDispositivo> buscarTodos(){
        return statusDispositivoList;
    }

    public StatusDispositivo buscarPorId(int id) {
        return statusDispositivoList
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .get();
    }

    public void adicionar(StatusDispositivo statusDispositivo) {
        this.statusDispositivoList.add(statusDispositivo);
    }

    public void excluir(int id) {
        this.statusDispositivoList.removeIf(l -> l.getId() == id);
    }

    public void atualizar(int id, StatusDispositivo statusDispositivo) {
        StatusDispositivo statuInMemory = buscarPorId(id);

        statuInMemory.setEstado(statusDispositivo.getEstado());
    }
}
