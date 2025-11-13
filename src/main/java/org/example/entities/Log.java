package org.example.entities;

import java.util.Date;
import org.example.models.LogModel;
import org.example.models.TabelaListModel;
import org.example.models.UsuarioModel;

public class Log {
    private int id;
    private String acao;
    private String descricao;
    private Date data;
    private int tabelaListId;
    private TabelaListModel tabelaList;
    private int usuarioId;
    private UsuarioModel usuario;

    public Log(){}

    public Log(int id, String acao, String descricao, Date data, int tabelaListId, TabelaListModel tabelaList, int usuarioId, UsuarioModel usuario) {
        this.acao = acao;
        this.descricao = descricao;
        this.data = data;
        this.tabelaListId = tabelaListId;
        this.tabelaList = tabelaList;
        this.usuarioId = usuarioId;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getTabelaListId() {
        return tabelaListId;
    }

    public void setTabelaListId(int tabelaListId) {
        this.tabelaListId = tabelaListId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LogModel toModel() {
        return new LogModel(
                this.getAcao(),
                this.getDescricao(),
                this.getData(),
                this.tabelaListId,
                null,
                this.usuarioId,
                null
        );
    }

    public static Log fromModel(LogModel model) {
        return new Log(
                model.getId(),
                model.getAcao(),
                model.getDescricao(),
                model.getData(),
                model.getTabelaListId(),
                null,
                model.getUsuarioId(),
                null
        );
    }
}