package org.example.entities;

import org.example.models.DispositivoModel;
import org.example.models.UnidadeModel;
import org.example.models.UsuarioModel;

import java.util.ArrayList;
import java.util.List;

public class Unidade {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private List<UsuarioModel> usuarios;
    private List<DispositivoModel> dispositivos;

    public Unidade(){}

    public Unidade(int id, String nome, String endereco, String telefone, String email, List<UsuarioModel> usuarios, List<DispositivoModel> dispositivos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        usuarios = new ArrayList<>();
        dispositivos = new ArrayList<>();
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
        return new UnidadeModel(
                this.getNome(),
                this.getEndereco(),
                this.getTelefone(),
                this.getEmail(),
                this.usuarios,
                this.dispositivos
        );
    }

    public static Unidade fromModel(UnidadeModel model) {
        return new Unidade(
                model.getId(),
                model.getNome(),
                model.getEndereco(),
                model.getTelefone(),
                model.getEmail(),
                model.getUsuarios(),
                model.getDispositivos()
        );
    }
}