package org.example.entities;

import org.example.models.MedicaoListaModel;
import org.example.models.MedicaoModel;

import java.util.ArrayList;
import java.util.List;

public class Medicao {
    private int id;
    private String descricao;
    private String dataHora;
    private int pacienteId;
    private int dispositivoId;
    private List<MedicaoListaModel> medicoesLista;

    public Medicao(){}

    public Medicao(int id, String descricao, String dataHora, int pacienteId, int dispositivoId, List<MedicaoListaModel> medicoesLista) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.pacienteId = pacienteId;
        this.dispositivoId = dispositivoId;
        medicoesLista = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public int getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(int dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public List<MedicaoListaModel> getMedicoesLista() {
        return medicoesLista;
    }

    public void setMedicoesLista(List<MedicaoListaModel> medicoesLista) {
        this.medicoesLista = medicoesLista;
    }

    public MedicaoModel toModel() {
        return new MedicaoModel(
                this.getDescricao(),
                this.getDataHora(),
                this.pacienteId,
                this.dispositivoId,
                this.medicoesLista
        );
    }

    public static Medicao fromModel(MedicaoModel model) {
        return new Medicao(
                model.getId(),
                model.getDescricao(),
                model.getDataHora(),
                model.getPacienteId(),
                model.getDispositivoId(),
                model.getMedicoesLista()
        );
    }
}