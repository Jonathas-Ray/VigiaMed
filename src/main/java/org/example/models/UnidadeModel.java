package org.example.models;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL)
    private List<DispositivoModel> dispositivos;

<<<<<<< HEAD
    public UnidadeModel(long id, String nome, String endereço, String telefone, String email) {
=======
    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL) // 🔹 agora compatível com UsuarioModel
    private List<UsuarioModel> usuarios;

    public UnidadeModel() {}

    public UnidadeModel(int id, String nome, String endereco, String telefone, String email) {
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
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
