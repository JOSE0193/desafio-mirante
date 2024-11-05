package com.mirante.avaliacao.service;

import java.util.List;
import java.util.stream.Collectors;

import com.mirante.avaliacao.dto.mapper.CidadeMapper;
import com.mirante.avaliacao.dto.CidadePageDTO;
import com.mirante.avaliacao.dto.CidadeRequestDTO;
import com.mirante.avaliacao.exception.RecordNotFoundException;
import com.mirante.avaliacao.model.Cidade;
import com.mirante.avaliacao.model.enums.StatusCapital;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.repository.CidadeRepository;
import org.springframework.validation.annotation.Validated;

//------------------------------------------------------------------
/** Service usado para acessar os repositórios da aplicação */
//------------------------------------------------------------------
@Service
@Validated
@RequiredArgsConstructor
public class ProjetoService {

	private final CidadeRepository repository;
	private final CidadeMapper cidadeMapper;
	
	//---------------------------------------------------------
	/** Método que retorna todas as cidades cadastradas */
	//---------------------------------------------------------
	public List<CidadeDTO> listCidades() {
		return repository.findAll().stream()
				.map(cidadeMapper::toDTO)
				.toList();
	}

	public CidadeDTO buscarCidade(@NotNull @Positive Long id) {
		return repository.findById(id).map(cidadeMapper::toDTO).orElseThrow(() -> new RecordNotFoundException(id));
	}

	public CidadePageDTO pesquisarCidades(@PositiveOrZero int page, @Positive @Max(100) int pageSize) {
		Page<Cidade> pageCidade = repository.findAll(PageRequest.of(page, pageSize));
		List<CidadeDTO> courses = pageCidade.get().map(cidadeMapper::toDTO).collect(Collectors.toList());
		return new CidadePageDTO(courses, pageCidade.getTotalElements(), pageCidade.getTotalPages());
	}

	//----------------------------------------------------------
	/** Método chamado para incluir uma nova cidade */
	//----------------------------------------------------------	
	public void incluirCidade(CidadeRequestDTO dto) {
		repository.save(cidadeMapper.toEntity(dto));
	}

	//----------------------------------------------------------
	/** Método chamado para alterar os dados de uma cidade */
	//----------------------------------------------------------	
	public void alterarCidade(@NotNull @Positive Long id, @Valid CidadeRequestDTO dto) {
		repository.findById(id)
				.map(recordFound -> {
					Cidade cidade = cidadeMapper.toEntity(dto);
					recordFound.setNome(dto.getNome());
					recordFound.setUf(dto.getUf());
					recordFound.setCapital(StatusCapital.get(dto.getCapital()));
					return cidadeMapper.toDTO(repository.save(recordFound));
				}).orElseThrow(() -> new RecordNotFoundException(id));
				
	}

	//----------------------------------------------------------
	/** Método chamado para excluir uma cidade */
	//----------------------------------------------------------	
	public void excluirCidade(@NotNull @Positive Long idCidade) {
		repository.delete(repository.findById(idCidade)
				.orElseThrow(() -> new RecordNotFoundException(idCidade)));
	}

}
