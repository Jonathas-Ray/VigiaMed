package org.example.entities;

import org.example.models.LogModel;
import org.example.models.TabelaListModel;

import java.util.ArrayList;
import java.util.List;

public class TabelaList {
    private int id;
    private String nome;
    private List<LogModel> log = new ArrayList<>();;

    public TabelaList() {}

    public TabelaList(int id, String nome, List<LogModel> log) {
        this.id = id;
        this.nome = nome;
        this.log = log;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<LogModel> getLog() {
        return log;
    }

    public void setLog(List<LogModel> log) {
        this.log = log;
    }

    public TabelaListModel toModel() {
        return new TabelaListModel(
                this.getNome(),
                this.log
        );
    }

    public static TabelaList fromModel(TabelaListModel model) {
        return new TabelaList(
                model.getId(),
                model.getNome(),
                model.getLog()
        );
    }
}