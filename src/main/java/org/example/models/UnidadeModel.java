package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unidade")
public class UnidadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String endereco;
    private String telefone;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<DispositivoModel> dispositivos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<UsuarioModel> usuarios = new ArrayList<>();;

    public UnidadeModel() {}

    public UnidadeModel(int id, String nome, String endereco, String telefone, String email, List<UsuarioModel> usuarios, List<DispositivoModel> dispositivos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.usuarios = usuarios;
        this.dispositivos = dispositivos;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DispositivoModel> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<DispositivoModel> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public List<UsuarioModel> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioModel> usuarios) {
        this.usuarios = usuarios;
    }
}