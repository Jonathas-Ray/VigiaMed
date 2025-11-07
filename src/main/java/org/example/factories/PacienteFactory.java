package org.example.factories;

import org.example.entities.Paciente;
import java.util.concurrent.atomic.AtomicInteger;

public class PacienteFactory {
    private final AtomicInteger contador = new AtomicInteger(0);

    public PacienteFactory() {}

    private int getNextID() {
        return contador.incrementAndGet();
    }

    public Paciente criarPaciente(String nome, String referencia) {
        Paciente paciente = new Paciente(nome, referencia);
        paciente.setId(getNextID());
        return paciente;
    }
}