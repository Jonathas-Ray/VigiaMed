package org.example.repositories;

import org.example.interfaces.DispositivoRepository;
import org.example.models.DispositivoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DispositivoRepositoryImpl implements DispositivoRepository {
    private List<DispositivoModel> dispositivos = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);


    public List<DispositivoModel> buscarTodos() {
        return dispositivos;
    }

    public DispositivoModel buscarPorId(int id) {
        for(DispositivoModel dispositivoModel: dispositivos){
            if(dispositivoModel.getId() == id){
                return dispositivoModel;
            }
        }
        return null;
    }

    public void adicionar(DispositivoModel dispositivoModel) {
        if(dispositivoModel.getId() == 0){
            dispositivoModel.setId(idCounter.getAndIncrement());
        }
        this.dispositivos.add(dispositivoModel);
    }

    public void excluir(int id) {
        for(DispositivoModel dispositivoModel : dispositivos){
            if (dispositivoModel.getId() == id){
                dispositivos.remove(dispositivoModel);
            }
        }
    }

    @Override
    public void atualizar(int id, DispositivoModel dispositivoModel) {
        DispositivoModel dispositivoExiste = buscarPorId(id);
        if(dispositivoExiste != null){
            dispositivoExiste.setModelo(dispositivoModel.getModelo());
            dispositivoExiste.setDataAquisicao(dispositivoModel.getDataAquisicao());
            dispositivoExiste.setNumeroSerie(dispositivoModel.getNumeroSerie());
        }
    }
}
