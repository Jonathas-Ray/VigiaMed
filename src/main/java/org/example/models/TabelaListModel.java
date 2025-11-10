package org.example.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tabelaLista")
public class TabelaListModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    @OneToMany(mappedBy = "tabelaListModel")
    private List<LogModel> log = new ArrayList<>();

    public TabelaListModel() {
    }

    public TabelaListModel(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

<<<<<<< HEAD
    public long getId() {
=======
    public int getId() {
>>>>>>> d19d9cafe26fb1354eb778f2bf8bac639262281e
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

    public List<LogModel> getLog() {
        return log;
    }

    public void setLog(List<LogModel> log) {
        this.log = log;
    }
}
