package org.example.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String tipo;
    private String email;
    private String senha;

    @Column(name = "unidade_id")
    public int unidadeId;

    @ManyToOne
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UnidadeModel unidadeModel;

    public UsuarioModel() {
    }

    public UsuarioModel(int id, String nome, String tipo, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.senha = senha;
    }

    public long getId() {
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
}