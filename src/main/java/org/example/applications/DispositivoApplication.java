package org.example.applications;

import org.apache.catalina.filters.RemoteIpFilter;
import org.example.entities.Dispositivo;
import org.example.interfaces.DispositivoRepository;
import org.example.models.DispositivoModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DispositivoApplication {
    private DispositivoRepository dispositivoRepository;

    public DispositivoApplication(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    public List<Dispositivo> buscarTodos() {
        List<DispositivoModel> modelList = this.dispositivoRepository.buscarTodos();
        List<Dispositivo> entitieList = new ArrayList<>();
        for(DispositivoModel dispositivoModel : modelList) {
            entitieList.add(new Dispositivo().fromModel(dispositivoModel));
        }
        return entitieList;
    }

    public Dispositivo buscarPorId(int id) {
        Dispositivo dispositivo = new Dispositivo().fromModel(this.dispositivoRepository.buscarPorId(id));
        return dispositivo;
    }

    public void adicionar(Dispositivo dispositivo) {
        DispositivoModel dispositivoModel = dispositivo.toModel();
        this.dispositivoRepository.adicionar(dispositivoModel);
    }

    public void excluir(int id) {
        this.dispositivoRepository.excluir(id);
    }

    public void atualizar(int id, Dispositivo dispositivo){
        DispositivoModel dispositivoModel = dispositivo.toModel();
        this.dispositivoRepository.atualizar(id, dispositivoModel);
    }
}
