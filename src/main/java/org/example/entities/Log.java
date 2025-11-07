package org.example.entities;

import java.util.Date;
import org.example.models.LogModel;

public class Log {
    private int id;
    private String acao;
    private String descricao;
    private Date data;

    public Log(){}

    public Log(String acao, String descricao, Date data) {
        this.acao = acao;
        this.descricao = descricao;
        this.data = data;
    }

    public Log(int id, String acao, String descricao, Date data) {
        this.id = id;
        this.acao = acao;
        this.descricao = descricao;
        this.data = data;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getAcao() { return acao; }
    public void setAcao(String acao) { this.acao = acao; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public LogModel toModel() {
        LogModel model = new LogModel();
        model.setId(this.getId());
        model.setAcao(this.getAcao());
        model.setDescricao(this.getDescricao());
        model.setData(this.getData());
        return model;
    }

    public static Log fromModel(LogModel model) {
        Log log = new Log(
                model.getId(),
                model.getAcao(),
                model.getDescricao(),
                model.getData()
        );
        return log;
    }
}