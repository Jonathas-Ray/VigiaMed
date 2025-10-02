package org.example.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Log")
public class LogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String acao;
    private String descricao;
    private Date data;

    public LogModel(){}

    public LogModel(long id, String acao, String descricao, Date data) {
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

    @Override
    public String toString() {
        return "LogModel{" +
                "id=" + id +
                ", acao='" + acao + '\'' +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                '}';
    }
}
