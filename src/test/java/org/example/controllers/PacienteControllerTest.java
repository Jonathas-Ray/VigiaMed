package org.example.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import org.example.entities.Paciente;
import org.example.facades.PacienteFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PacienteController.class)
class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PacienteFacade pacienteFacade;

    @Test
    void deveRetornarListaDePacientes() throws Exception {
        when(pacienteFacade.buscarTodos()).thenReturn(Arrays.asList(new Paciente()));

        mockMvc.perform(get("/api/paciente"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void deveRetornarPacientePorId() throws Exception {
        Paciente p = new Paciente();
        p.setId(1);
        when(pacienteFacade.buscarPorId(1)).thenReturn(p);

        mockMvc.perform(get("/api/paciente/1"))
               .andExpect(status().isOk());
    }

    @Test
    void deveRetornar404QuandoPacienteNaoExistir() throws Exception {
        when(pacienteFacade.buscarPorId(99)).thenReturn(null);

        mockMvc.perform(get("/api/paciente/99"))
               .andExpect(status().isNotFound());
    }
}