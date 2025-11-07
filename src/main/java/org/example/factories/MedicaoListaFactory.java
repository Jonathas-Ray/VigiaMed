package org.example.factories;

import org.example.entities.MedicaoLista;
import java.util.concurrent.atomic.AtomicInteger;

public class MedicaoListaFactory {
    private final AtomicInteger contador = new AtomicInteger(0);

    public MedicaoListaFactory() {}

    private int getNextID() {
        return contador.incrementAndGet();
    }

    public MedicaoLista criarMedicaoLista(double resultado, String tipoMedicao, String data_hora) {
        MedicaoLista medicaoLista = new MedicaoLista(resultado, tipoMedicao, data_hora);
        medicaoLista.setId(getNextID());
        return medicaoLista;
    }
}