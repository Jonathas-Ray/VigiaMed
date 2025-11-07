package org.example.factories;

import org.example.entities.StatusDispositivo;
import java.util.concurrent.atomic.AtomicInteger;

public class StatusDispositivoFactory {
    private final AtomicInteger contador = new AtomicInteger(0);

    public StatusDispositivoFactory() {}

    private int getNextID() {
        return contador.incrementAndGet();
    }

    public StatusDispositivo criarStatusDispositivo(String estado) {
        StatusDispositivo status = new StatusDispositivo(estado);
        status.setId(getNextID());
        return status;
    }
}