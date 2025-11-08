package org.example.entities;

import org.example.models.UnidadeModel;

public class Unidade {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    public Unidade(){}

    public Unidade(String nome, String endereco, String telefone, String email) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Unidade(int id, String nome, String endereco, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public UnidadeModel toModel() {
        UnidadeModel model = new UnidadeModel();
        model.setId(this.getId());
        model.setNome(this.getNome());
        model.setEndereco(this.getEndereco());
        model.setTelefone(this.getTelefone());
        model.setEmail(this.getEmail());
        return model;
    }

    public static Unidade fromModel(UnidadeModel model) {
        Unidade unidade = new Unidade(
                model.getNome(),
                model.getEndereco(),
                model.getTelefone(),
                model.getEmail()
        );
        unidade.setId(model.getId());
        return unidade;
    }
}