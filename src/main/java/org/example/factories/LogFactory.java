package org.example.factories;

import org.example.entities.Log;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class LogFactory {
    private final AtomicInteger contador = new AtomicInteger(0);

    public LogFactory() {}

    private int getNextID() {
        return contador.incrementAndGet();
    }

    public Log criarLog(String acao, String descricao, Date data) {
        return new Log(getNextID(), acao, descricao, data);
    }
}