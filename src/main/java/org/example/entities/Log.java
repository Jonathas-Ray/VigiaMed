package org.example.entities;

import java.util.Date;

public class Log {
    private long id;
    private String acao;
    private String descricao;
    private Date data;

    public Log(long id, String acao, String descricao, Date data) {
        this.id = id;
        this.acao = acao;
        this.descricao = descricao;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public String getAcao() {
        return acao;
    }
    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
}
