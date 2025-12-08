package org.example.applications;

import org.example.entities.MedicaoLista;
import org.example.interfaces.MedicaoListaRepository;
import org.example.models.MedicaoListaModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicaoListaApplication {
    private final MedicaoListaRepository medicaoListaRepository;


    public MedicaoListaApplication(MedicaoListaRepository medicaoListaRepository) {
        this.medicaoListaRepository = medicaoListaRepository;
    }

    public List<MedicaoLista> buscarTodos() {
        List<MedicaoListaModel> modelList = this.medicaoListaRepository.buscarTodos();
        List<MedicaoLista> entitieList = new ArrayList<>();
        for(MedicaoListaModel medicaoListaModel : modelList) {
            entitieList.add(new MedicaoLista().fromModel(medicaoListaModel));
        }
        return entitieList;
    }

    public MedicaoLista buscarPorId(int id) {
        MedicaoLista medicaoLista = new MedicaoLista().fromModel(this.medicaoListaRepository.buscarPorId(id));
        return medicaoLista;
    }

    public void adicionar(MedicaoLista medicaoLista) {
        MedicaoListaModel medicaoListaModel = medicaoLista.toModel();
        this.medicaoListaRepository.adicionar(medicaoListaModel);
    }

    public void excluir(int id) {
        this.medicaoListaRepository.excluir(id);
    }

    public void atualizar(int id, MedicaoLista medicaoLista){
        MedicaoListaModel medicaoListaModel = medicaoLista.toModel();
        this.medicaoListaRepository.atualizar(id, medicaoListaModel);
    }

    // verificação em real time

    public ResultadoValidacao verificarUltimaMedicao() {
        List<MedicaoListaModel> modelList = this.medicaoListaRepository.buscarTodos();

        if (modelList == null || modelList.isEmpty()) {
            return new ResultadoValidacao(null, false, "Nenhuma medição encontrada");
        }

        MedicaoListaModel ultimaMedicao = modelList.get(modelList.size() - 1);
        Double resultado = ultimaMedicao.getResultado();

        if (resultado == null) {
            return new ResultadoValidacao(null, false, "Resultado não disponível");
        }

        String mensagem;
        boolean acimaDaNormal = false;

        // Validação usando apenas IF
        if (resultado >= 60 && resultado <= 100) {
            mensagem = "Batimentos normais: " + resultado + " bpm";
        } else if (resultado < 60) {
            mensagem = "Batimentos abaixo do normal: " + resultado + " bpm";
        } else {
            mensagem = "Batimentos acima do normal: " + resultado + " bpm";
            acimaDaNormal = true;
        }

        return new ResultadoValidacao(resultado, acimaDaNormal, mensagem);
    }


    public ResultadoValidacao verificarUltimoResultado() {
        return verificarUltimaMedicao();
    }

    public static class ResultadoValidacao {

        private Double resultado;
        private boolean acimaDaNormal;
        private String mensagem;

        public ResultadoValidacao(Double resultado, boolean acimaDaNormal, String mensagem) {
            this.resultado = resultado;
            this.acimaDaNormal = acimaDaNormal;
            this.mensagem = mensagem;
        }

        public Double getResultado() {
            return resultado;
        }

        public void setResultado(Double resultado) {
            this.resultado = resultado;
        }

        public boolean isAcimaDaNormal() {
            return acimaDaNormal;
        }

        public void setAcimaDaNormal(boolean acimaDaNormal) {
            this.acimaDaNormal = acimaDaNormal;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }

}