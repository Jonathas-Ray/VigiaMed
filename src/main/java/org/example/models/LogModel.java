package org.example.models;

import jakarta.persistence.*;
import org.example.entities.Medicao;
import org.example.entities.MedicaoLista;
import org.example.entities.TabelaList;
import org.example.entities.Usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Log")
public class LogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String acao;
    private String descricao;
    private Date data;

    @ManyToOne
    @JoinColumn(name = "tabelaList_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "mtabelaList_id"), insertable = false, updatable = false)
    private TabelaListModel tabelaListModel;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "usuario_id"), insertable = false, updatable = false)
    private UsuarioModel usuarioModel;

    public LogModel(){}

    public LogModel(int id, String acao, String descricao, Date data) {
        this.id = id;
        this.acao = acao;
        this.descricao = descricao;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){this.id = id;}

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
