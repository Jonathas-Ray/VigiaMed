package org.example.factories;

import org.example.entities.TabelaList;
import java.util.concurrent.atomic.AtomicInteger;

public class TabelaListFactory {
    private final AtomicInteger contador = new AtomicInteger(0);

    public TabelaListFactory() {}

    private int getNextID() {
        return contador.incrementAndGet();
    }

    public TabelaList criarTabelaList(String nome) {
        TabelaList tabelaList = new TabelaList(nome);
        tabelaList.setId(getNextID());
        return tabelaList;
    }
}