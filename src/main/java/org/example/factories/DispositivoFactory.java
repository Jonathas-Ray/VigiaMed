package org.example.factories;

import org.example.entities.Dispositivo;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class DispositivoFactory {
    private final AtomicInteger contador = new AtomicInteger(0);

    public DispositivoFactory() {}

    private int getNextID() {
        return contador.incrementAndGet();
    }

    public Dispositivo criarDispositivo(String modelo, String numeroSerie, Date dataAquisicao) {
        Dispositivo dispositivo = new Dispositivo(modelo, numeroSerie, dataAquisicao);
        dispositivo.setId(getNextID());
        return dispositivo;
    }
}