package com.mirante.avaliacao.service;

import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.dto.CidadePageDTO;
import com.mirante.avaliacao.dto.CidadeRequestDTO;
import com.mirante.avaliacao.dto.mapper.CidadeMapper;
import com.mirante.avaliacao.exception.RecordNotFoundException;
import com.mirante.avaliacao.model.Cidade;
import com.mirante.avaliacao.model.enums.StatusCapital;
import com.mirante.avaliacao.repository.CidadeRepository;
import com.mirante.avaliacao.util.CidadeCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjetoServiceTest {

    @Mock
    private CidadeRepository repository;

    @Mock
    private CidadeMapper cidadeMapper;

    @InjectMocks
    private ProjetoService projetoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListCidades() {
        Cidade cidade = new Cidade();
        CidadeDTO cidadeDTO = new CidadeDTO();
        when(repository.findAll()).thenReturn(List.of(cidade));
        when(cidadeMapper.toDTO(cidade)).thenReturn(cidadeDTO);

        List<CidadeDTO> result = projetoService.listCidades();

        assertEquals(1, result.size());
        assertEquals(cidadeDTO, result.get(0));
        verify(repository, times(1)).findAll();
    }

    @Test
    void testBuscarCidade_Success() {
        Long cidadeId = 1L;
        Cidade cidade = new Cidade();
        CidadeDTO cidadeDTO = new CidadeDTO();
        when(repository.findById(cidadeId)).thenReturn(Optional.of(cidade));
        when(cidadeMapper.toDTO(cidade)).thenReturn(cidadeDTO);

        CidadeDTO result = projetoService.buscarCidade(cidadeId);

        assertEquals(cidadeDTO, result);
        verify(repository, times(1)).findById(cidadeId);
    }

    @Test
    void testBuscarCidade_NotFound() {
        Long cidadeId = 1L;
        when(repository.findById(cidadeId)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> projetoService.buscarCidade(cidadeId));
        verify(repository, times(1)).findById(cidadeId);
    }

    @Test
    void testPesquisarCidades() {
        int page = 0;
        int pageSize = 10;
        Cidade cidade = new Cidade();
        CidadeDTO cidadeDTO = new CidadeDTO();
        Page<Cidade> pageCidade = new PageImpl<>(List.of(cidade));
        when(repository.findAll(PageRequest.of(page, pageSize))).thenReturn(pageCidade);
        when(cidadeMapper.toDTO(cidade)).thenReturn(cidadeDTO);

        CidadePageDTO result = projetoService.pesquisarCidades(page, pageSize);

        assertEquals(1, result.getCidades().size());
        assertEquals(cidadeDTO, result.getCidades().get(0));
        verify(repository, times(1)).findAll(PageRequest.of(page, pageSize));
    }

    @Test
    void testIncluirCidade() {
        CidadeRequestDTO dto = new CidadeRequestDTO();
        Cidade cidade = new Cidade();
        when(cidadeMapper.toEntity(dto)).thenReturn(cidade);

        projetoService.incluirCidade(dto);

        verify(repository, times(1)).save(cidade);
    }

    @Test
    void testAlterarCidade_Success() {
        Long cidadeId = 1L;
        CidadeRequestDTO dto = CidadeCreator.criadorCidadeResquestDTO();

        Cidade existingCidade = new Cidade();
        when(repository.findById(cidadeId)).thenReturn(Optional.of(existingCidade));

        Cidade updatedCidade = new Cidade();
        when(cidadeMapper.toEntity(dto)).thenReturn(updatedCidade);
        when(repository.save(existingCidade)).thenReturn(existingCidade);
        when(cidadeMapper.toDTO(existingCidade)).thenReturn(new CidadeDTO());

        projetoService.alterarCidade(cidadeId, dto);

        assertEquals("Brasilia", existingCidade.getNome());
        assertEquals("DF", existingCidade.getUf());
        assertEquals(StatusCapital.SIM, existingCidade.getCapital());
        verify(repository, times(1)).findById(cidadeId);
        verify(repository, times(1)).save(existingCidade);
    }

    @Test
    void testAlterarCidade_NotFound() {
        Long cidadeId = 1L;
        CidadeRequestDTO dto = new CidadeRequestDTO();
        when(repository.findById(cidadeId)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> projetoService.alterarCidade(cidadeId, dto));
        verify(repository, times(1)).findById(cidadeId);
    }

    @Test
    void testExcluirCidade_Success() {
        Long cidadeId = 1L;
        Cidade cidade = new Cidade();
        when(repository.findById(cidadeId)).thenReturn(Optional.of(cidade));

        projetoService.excluirCidade(cidadeId);

        verify(repository, times(1)).delete(cidade);
    }

    @Test
    void testExcluirCidade_NotFound() {
        Long cidadeId = 1L;
        when(repository.findById(cidadeId)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> projetoService.excluirCidade(cidadeId));
        verify(repository, times(1)).findById(cidadeId);
    }
}