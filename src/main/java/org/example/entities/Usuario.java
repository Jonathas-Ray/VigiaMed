package org.example.entities;

import org.example.models.LogModel;
import org.example.models.UnidadeModel;
import org.example.models.UsuarioModel;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nome;
    private String tipo;
    private String email;
    private String senha;
    private int unidadeId;
    private UnidadeModel unidade;
    private List<LogModel> log = new ArrayList<>();


    public Usuario(){}

    public Usuario(int id, String nome, String tipo, String email, String senha, int unidadeId,  UnidadeModel unidade, List<LogModel> logs) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.senha = senha;
        this.unidadeId = unidadeId;
        this.unidade = unidade;
        this. log = logs;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(int unidadeId) {
        this.unidadeId = unidadeId;
    }

    public UnidadeModel getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeModel unidade) {
        this.unidade = unidade;
    }

    public List<LogModel> getLog() {
        return log;
    }

    public void setLog(List<LogModel> log) {
        this.log = log;
    }

    public UsuarioModel toModel() {
        return new UsuarioModel(
                this.getNome(),
                this.getTipo(),
                this.getEmail(),
                this.getSenha(),
                this.getUnidadeId(),
                null,
                this.getLog()
        );
    }

    public static Usuario fromModel(UsuarioModel model) {
        return new Usuario(
                model.getId(),
                model.getNome(),
                model.getTipo(),
                model.getEmail(),
                model.getSenha(),
                model.getUnidadeId(),
                null,
                model.getLogs()
        );
    }
}