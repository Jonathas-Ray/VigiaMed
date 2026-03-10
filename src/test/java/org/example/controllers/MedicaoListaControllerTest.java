package org.example.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.example.applications.MedicaoListaApplication;
import org.example.facades.MedicaoListaFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MedicaoListaController.class)
class MedicaoListaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicaoListaFacade facade;

    @MockBean
    private MedicaoListaApplication application; // O controller usa ambos

    @Test
    void deveRetornar200AoMonitorar() throws Exception {
        var mockResultado = new MedicaoListaApplication.ResultadoValidacao(75.0, false, "Normal");
        when(application.verificarUltimoResultado()).thenReturn(mockResultado);

        mockMvc.perform(get("/api/medicao-lista/monitorar"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.resultado").value(75.0));
    }
}