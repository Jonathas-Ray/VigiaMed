package org.example.models;


import jakarta.persistence.*;

@Entity
@Table(name = "Tabela-Lista")
public class TabelaListModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;

    public TabelaListModel() {
    }

    public TabelaListModel(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "TabelaListModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
