package org.example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String tipo;
    private String email;
    private String senha;

    @Column(name = "unidade_id")
    private int unidadeId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", insertable = false, updatable = false)
    private UnidadeModel unidade;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<LogModel> logs;

    public UsuarioModel() {}

    public UsuarioModel( String nome, String tipo, String email, String senha, int unidadeId,  UnidadeModel unidade, List<LogModel> logs) {
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.senha = senha;
        this.unidadeId = unidadeId;
        this.unidade = unidade;
        this.logs = logs;
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

    public void setUnidade(UnidadeModel unidade) {
        this.unidade = unidade;
    }

    public List<LogModel> getLogs() {
        return logs;
    }

    public void setLogs(List<LogModel> logs) {
        this.logs = logs;
    }
}