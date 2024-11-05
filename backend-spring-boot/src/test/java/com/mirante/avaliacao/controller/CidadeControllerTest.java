package com.mirante.avaliacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.dto.CidadePageDTO;
import com.mirante.avaliacao.dto.CidadeRequestDTO;
import com.mirante.avaliacao.exception.RecordNotFoundException;
import com.mirante.avaliacao.service.ProjetoService;
import com.mirante.avaliacao.util.CidadeCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CidadeController.class)
class CidadeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjetoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private CidadeDTO cidadeDTO;
    private CidadeRequestDTO cidadeRequestDTO;

    @BeforeEach
    void setUp() {
        cidadeDTO = CidadeCreator.criadorCidadeDTO();
        cidadeRequestDTO = CidadeCreator.criadorCidadeResquestDTO();
    }

    @Test
    void testPesquisarCidades() throws Exception {
        CidadePageDTO cidadePageDTO = new CidadePageDTO(List.of(cidadeDTO), 1L, 1);
        when(service.pesquisarCidades(0, 10)).thenReturn(cidadePageDTO);

        mockMvc.perform(get("/cidades")
                        .param("page", "0")
                        .param("pageSize", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cidades[0].nome", is(cidadeDTO.getNome())))
                .andExpect(jsonPath("$.cidades[0].uf", is(cidadeDTO.getUf())))
                .andExpect(jsonPath("$.totalElements", is(1)))
                .andExpect(jsonPath("$.totalPages", is(1)));

        verify(service, times(1)).pesquisarCidades(0, 10);
    }

    @Test
    void testBuscarCidade_Success() throws Exception {
        when(service.buscarCidade(1L)).thenReturn(cidadeDTO);

        mockMvc.perform(get("/cidades/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(cidadeDTO.getNome())))
                .andExpect(jsonPath("$.uf", is(cidadeDTO.getUf())));

        verify(service, times(1)).buscarCidade(1L);
    }

    @Test
    void testBuscarCidade_NotFound() throws Exception {
        when(service.buscarCidade(1L)).thenThrow(new RecordNotFoundException(1L));

        mockMvc.perform(get("/cidades/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(1)).buscarCidade(1L);
    }

    @Test
    void testIncluirCidade() throws Exception {
        mockMvc.perform(post("/cidades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cidadeRequestDTO)))
                .andExpect(status().isCreated());

        verify(service, times(1)).incluirCidade(any(CidadeRequestDTO.class));
    }

    @Test
    void testAlterarCidade_Success() throws Exception {
        mockMvc.perform(put("/cidades/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cidadeRequestDTO)))
                .andExpect(status().isOk());

        verify(service, times(1)).alterarCidade(eq(1L), any(CidadeRequestDTO.class));
    }

    @Test
    void testAlterarCidade_NotFound() throws Exception {
        doThrow(new RecordNotFoundException(1L)).when(service).alterarCidade(eq(1L), any(CidadeRequestDTO.class));

        mockMvc.perform(put("/cidades/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cidadeRequestDTO)))
                .andExpect(status().isNotFound());

        verify(service, times(1)).alterarCidade(eq(1L), any(CidadeRequestDTO.class));
    }

    @Test
    void testExcluirCidade_Success() throws Exception {
        mockMvc.perform(delete("/cidades/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service, times(1)).excluirCidade(1L);
    }

    @Test
    void testExcluirCidade_NotFound() throws Exception {
        doThrow(new RecordNotFoundException(1L)).when(service).excluirCidade(1L);

        mockMvc.perform(delete("/cidades/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service, times(1)).excluirCidade(1L);
    }

}