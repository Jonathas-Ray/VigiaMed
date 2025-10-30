package org.example.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String tipo;
    private String email;
    private String senha;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "unidade_id"), insertable = false, updatable = false)
    private UnidadeModel unidadeModel;

    @OneToMany(mappedBy = "usuarioModel")
    private List<LogModel> logModels = new ArrayList<>();

    public UsuarioModel(){}

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

    public void setId(long id) {
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

    public UnidadeModel getUnidadeModel() {
        return unidadeModel;
    }

    public void setUnidadeModel(UnidadeModel unidadeModel) {
        this.unidadeModel = unidadeModel;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", unidade=" + unidadeModel +
                '}';
    }

    @OneToMany(mappedBy = "usuarioModel")
    private List<LogModel> logModel = new ArrayList<>();


}