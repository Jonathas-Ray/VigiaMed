package org.example.factories;

import org.example.entities.Unidade;
import java.util.concurrent.atomic.AtomicInteger;

public class UnidadeFactory {
    private final AtomicInteger contador = new AtomicInteger(0);

    public UnidadeFactory() {}

    private int getNextID() {
        return contador.incrementAndGet();
    }

    public Unidade criarUnidade(String nome, String endereco, String telefone, String email) {
        Unidade unidade = new Unidade(nome, endereco, telefone, email);
        unidade.setId(getNextID());
        return unidade;
    }
}