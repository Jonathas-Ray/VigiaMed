package org.example.logTestes;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;

import org.example.entities.Log;
import org.example.models.LogModel;
import org.junit.Before;
import org.junit.Test;


/**
 * Classe de teste criada para garantir o funcionamento das principais opera��es
 * sobre os logs, realizadas pela classe.
 *
 * @Heitor Trindade
 * @10/03/2026
 */
public class LogEntityTest {

    private Log log;

    @Before
    public void setUp() {
        log = new Log();
    }

    @Test
    public void testSetId() {
        int valor = 100;
        log.setId(valor);
        assertThat(log.getId(), is(valor));
    }

    @Test
    public void testSetAcao() {
        String valor = "DELETE";
        log.setAcao(valor);
        assertThat(log.getAcao(), is(valor));
    }

    @Test
    public void testSetDescricao() {
        String valor = "Remoção de registro";
        log.setDescricao(valor);
        assertThat(log.getDescricao(), is(valor));
    }

    @Test
    public void testSetData() {
        Date valor = new Date();
        log.setData(valor);
        assertThat(log.getData(), is(valor));
    }

    @Test
    public void testSetTabelaListId() {
        int valor = 20;
        log.setTabelaListId(valor);
        assertThat(log.getTabelaListId(), is(valor));
    }

    @Test
    public void testSetUsuarioId() {
        int valor = 7;
        log.setUsuarioId(valor);
        assertThat(log.getUsuarioId(), is(valor));
    }

    @Test
    public void testToModelMapeamento() {
        log.setAcao("LOGIN");
        log.setDescricao("Usuario logou");
        log.setData(new Date());
        log.setTabelaListId(1);
        log.setUsuarioId(2);

        LogModel model = log.toModel();

        assertThat(model.getAcao(), is(log.getAcao()));
        assertThat(model.getDescricao(), is(log.getDescricao()));
        assertThat(model.getTabelaListId(), is(log.getTabelaListId()));
        assertThat(model.getUsuarioId(), is(log.getUsuarioId()));
    }

    @Test
    public void testConstrutorCompleto() {
        Date data = new Date();
        Log novoLog = new Log(1, "TEST", "Desc", data, 10, null, 5, null);

        assertThat(novoLog.getAcao(), is("TEST"));
        assertThat(novoLog.getUsuarioId(), is(5));
        assertThat(novoLog.getTabelaListId(), is(10));
    }
}