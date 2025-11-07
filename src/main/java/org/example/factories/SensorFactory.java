package org.example.factories;

import org.example.entities.Sensor;
import java.util.concurrent.atomic.AtomicInteger;

public class SensorFactory {
    private final AtomicInteger contador = new AtomicInteger(0);

    public SensorFactory() {}

    private int getNextID() {
        return contador.incrementAndGet();
    }

    public Sensor criarSensor(String nome, String unidadeMedida) {
        Sensor sensor = new Sensor(nome, unidadeMedida);
        sensor.setId(getNextID());
        return sensor;
    }
}