package com.example.agendatelefonica.controller;

import com.example.agendatelefonica.model.Contato;
import com.example.agendatelefonica.service.ContatoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class ContatoControllerTests {

    private MockMvc mockMvc;

    @Mock
    private ContatoService contatoService;

    @InjectMocks
    private ContatoController contatoController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(contatoController)
                .build();
    }

    @Test
    public void quandoListarTodosContatos_entaoRetornarJsonArray() throws Exception {
        Contato contato = new Contato();
        contato.setContatoId(1L);
        contato.setContatoNome("Teste");
        contato.setContatoCelular("12345678901");

        List<Contato> todosContatos = Arrays.asList(contato);

        given(contatoService.listarTodos()).willReturn(todosContatos);

        mockMvc.perform(get("/api/contatos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].contatoNome", is(contato.getContatoNome())));
    }

    @Test
    public void quandoAdicionarContato_entaoRetornarContatoSalvo() throws Exception {
        Contato contato = new Contato();
        contato.setContatoNome("Teste");
        contato.setContatoCelular("12345678901");

        given(contatoService.salvarContato((Contato) any(Contato.class))).willReturn(contato);

        mockMvc.perform(post("/api/contatos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contato)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.contatoNome", is(contato.getContatoNome())));
    }
}
