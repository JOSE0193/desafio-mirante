package com.mirante.avaliacao.controller;

import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.dto.CidadePageDTO;
import com.mirante.avaliacao.dto.CidadeRequestDTO;
import com.mirante.avaliacao.service.ProjetoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//--------------------------------------------------
/** Endpoint para consultar e manter cidades */
//--------------------------------------------------
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/cidades")
public class CidadeController {

	private final ProjetoService service ;
	
	//----------------------------------------------------------
	/** Endpoint que retorna todas as cidades cadastradas */
	//----------------------------------------------------------
	@Operation(summary = "Pesquisar", description = "Retorna a lista de cidades paginadas.")
	@GetMapping
	public CidadePageDTO pesquisarCidades(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
										 @Positive @Max(100) @RequestParam(defaultValue = "10") int pageSize) {
		return service.pesquisarCidades(page, pageSize);
	}

	@Operation(summary = "Buscar", description = "Busca uma cidade específica.")
	@GetMapping("/{id}")
	public CidadeDTO buscarCidade(@PathVariable(required = true) @NotNull @Positive Long id) {
		return service.buscarCidade(id);
	}
	
	//----------------------------------------------------------
	/** Endpoint para incluir nova cidade */
	//----------------------------------------------------------
	@Operation(summary = "Salvar", description = "Salvar uma cidade .")
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void incluirCidade(@RequestBody(required = true) CidadeRequestDTO cidade) {
		service.incluirCidade(cidade);
	}	
	
	//----------------------------------------------------------
	/** Endpoint para alterar cidade */
	//----------------------------------------------------------
	@Operation(summary = "Atualizar", description = "Atualizar uma cidade.")
	@PutMapping("/{id}")
	public void alterarCidade(@PathVariable(required = true) @NotNull @Positive Long id,
							  @RequestBody(required = true) CidadeRequestDTO cidade) {
		service.alterarCidade(id, cidade);
	}		
	
	//----------------------------------------------------------
	/** Endpoint para excluir uma cidade */
	//----------------------------------------------------------
	@Operation(summary = "Remover", description = "Remover uma cidade.")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void excluirCidade(@PathVariable(required = true) @NotNull @Positive Long id) {
		service.excluirCidade(id);
	}

}
