package org.example.models;


import jakarta.persistence.*;
import org.example.entities.Log;
import org.example.entities.MedicaoLista;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tabela-Lista")
public class TabelaListModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    public TabelaListModel() {
    }

    public TabelaListModel(int id, String nome) {
        this.id = id;
        this.nome = nome;
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

    @Override
    public String toString() {
        return "TabelaListModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "tabelaListModel")
    private List<LogModel> logModels = new ArrayList<>();
}
