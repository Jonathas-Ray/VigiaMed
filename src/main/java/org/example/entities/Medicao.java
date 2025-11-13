package org.example.entities;

import org.example.models.DispositivoModel;
import org.example.models.MedicaoListaModel;
import org.example.models.MedicaoModel;
import org.example.models.PacienteModel;

import java.util.ArrayList;
import java.util.List;

public class Medicao {
    private int id;
    private String descricao;
    private String dataHora;
    private int pacienteId;
    private PacienteModel paciente;
    private int dispositivoId;
    private DispositivoModel dispositivo;
    private List<MedicaoListaModel> medicoesLista = new ArrayList<>();

    public Medicao(){}

    public Medicao(int id, String descricao, String dataHora, int pacienteId, PacienteModel paciente, int dispositivoId, DispositivoModel dispositivo, List<MedicaoListaModel> medicoesLista) {
        this.id = id;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.pacienteId = pacienteId;
        this.paciente = paciente;
        this.dispositivoId = dispositivoId;
        this.dispositivo = dispositivo;
        this.medicoesLista = medicoesLista;
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
                null,
                this.dispositivoId,
                null,
                this.medicoesLista
        );
    }

    public static Medicao fromModel(MedicaoModel model) {
        return new Medicao(
                model.getId(),
                model.getDescricao(),
                model.getDataHora(),
                model.getPacienteId(),
                null,
                model.getDispositivoId(),
                null,
                model.getMedicoesLista()
        );
    }
}