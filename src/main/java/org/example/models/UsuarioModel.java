package org.example.models;

import jakarta.persistence.*;
import org.example.entities.Dispositivo;
import org.example.entities.Log;
import org.example.entities.Unidade;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "unidade_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "unidade_id"))
    private Unidade unidade;



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

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", unidade=" + unidade +
                '}';
    }

    @OneToMany(mappedBy = "usuario")
    private List<LogModel> logModel = new ArrayList<>();


}
