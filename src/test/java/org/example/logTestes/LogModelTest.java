package org.example.logTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

import org.example.models.LogModel;
import org.example.models.TabelaListModel;
import org.example.models.UsuarioModel;
import org.junit.Before;
import org.junit.Test;


/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre os logs, realizadas pela classe.
 *
 * @Heitor Trindade
 * @10/03/2026
 */
public class LogModelTest {

    private LogModel model;

    @Before
    public void setUp() {
        model = new LogModel();
    }

    @Test
    public void testSetId() {
        int valor = 1;
        model.setId(valor);
        assertThat(model.getId(), is(valor));
    }

    @Test
    public void testSetAcao() {
        String valor = "INSERT";
        model.setAcao(valor);
        assertThat(model.getAcao(), is(valor));
    }

    @Test
    public void testSetDescricao() {
        String valor = "Novo dispositivo adicionado";
        model.setDescricao(valor);
        assertThat(model.getDescricao(), is(valor));
    }

    @Test
    public void testSetData() {
        Date valor = new Date();
        model.setData(valor);
        assertThat(model.getData(), is(valor));
    }

    @Test
    public void testSetTabelaListId() {
        int valor = 10;
        model.setTabelaListId(valor);
        assertThat(model.getTabelaListId(), is(valor));
    }

    @Test
    public void testSetTabelaList() {
        TabelaListModel valor = new TabelaListModel();
        model.setTabelaList(valor);
        assertThat(model.getTabelaList(), is(valor));
    }

    @Test
    public void testSetUsuarioId() {
        int valor = 5;
        model.setUsuarioId(valor);
        assertThat(model.getUsuarioId(), is(valor));
    }

    @Test
    public void testSetUsuario() {
        UsuarioModel valor = new UsuarioModel();
        model.setUsuario(valor);
        assertThat(model.getUsuario(), is(valor));
    }

    @Test
    public void testConstrutorComParametros() {
        String acao = "UPDATE";
        String descricao = "Desc";
        Date data = new Date();
        int tabelaId = 1;
        int usuarioId = 2;
        TabelaListModel tabela = new TabelaListModel();
        UsuarioModel usuario = new UsuarioModel();

        LogModel novoModel = new LogModel(acao, descricao, data, tabelaId, tabela, usuarioId, usuario);

        assertThat(novoModel.getAcao(), is(acao));
        assertThat(novoModel.getDescricao(), is(descricao));
        assertThat(novoModel.getData(), is(data));
        assertThat(novoModel.getTabelaListId(), is(tabelaId));
        assertThat(novoModel.getTabelaList(), is(tabela));
        assertThat(novoModel.getUsuarioId(), is(usuarioId));
        assertThat(novoModel.getUsuario(), is(usuario));
    }
}