package org.example.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log")
public class LogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String acao;
    private String descricao;
    private Date data;

    @Column(name = "tabelaList_id")
    private int tabelaListId;
    @ManyToOne
    @JoinColumn(name = "tabelaList_id", referencedColumnName = "id", insertable = false, updatable = false)
    private TabelaListModel tabelaListModel;

    @Column(name = "usuario_id")
    private int usuarioId;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UsuarioModel usuario;

    public LogModel() {}

    public LogModel(String acao, String descricao, Date data) {
        this.acao = acao;
        this.descricao = descricao;
        this.data = data;
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

    public TabelaListModel getTabelaListModel() {
        return tabelaListModel;
    }

    public void setTabelaListModel(TabelaListModel tabelaListModel) {
        this.tabelaListModel = tabelaListModel;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
