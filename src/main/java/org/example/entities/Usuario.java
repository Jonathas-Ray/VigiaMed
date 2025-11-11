package org.example.entities;

import org.example.models.UsuarioModel;

public class Usuario {
    private int id;
    private String nome;
    private String tipo;
    private String email;
    private String senha;

    public Usuario(){}

    public Usuario(int id, String nome, String tipo, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.senha = senha;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public UsuarioModel toModel() {
        return new UsuarioModel(
                this.getNome(),
                this.getTipo(),
                this.getEmail(),
                this.getSenha()
        );
    }

    public static Usuario fromModel(UsuarioModel model) {
        return new Usuario(
                model.getId(),
                model.getNome(),
                model.getTipo(),
                model.getEmail(),
                model.getSenha()
        );
    }
}