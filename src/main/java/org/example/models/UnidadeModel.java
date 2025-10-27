package org.example.models; // repository, application, facade de cada classe

import jakarta.persistence.*;
import org.example.entities.Dispositivo;
import org.example.entities.MedicaoLista;
import org.example.entities.Usuario;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Unidade")
public class UnidadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String endereço;
    private String telefone;
    private String email;

    public UnidadeModel(){}

    public UnidadeModel(int id, String nome, String endereço, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.endereço = endereço;
        this.telefone = telefone;
        this.email = email;
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

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
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

    @Override
    public String toString() {
        return "UnidadeModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereço='" + endereço + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "unidadeModel")
    private List<DispositivoModel> dispositivoModels = new ArrayList<>();
}