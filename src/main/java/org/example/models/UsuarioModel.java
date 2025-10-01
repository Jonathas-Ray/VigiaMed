package org.example.models;

import jakarta.persistence.*;
import org.example.entities.Usuario;

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
    private int unidade;

    public UsuarioModel(){}

    public UsuarioModel(int id, String nome, String tipo, String email, String senha, int unidade) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.senha = senha;
        this.unidade = unidade;
    }

    public static long getId() {
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

    public static int getUnidade() {
        return unidade;
    }

    public void setUnidade(int unidade) {
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
}
