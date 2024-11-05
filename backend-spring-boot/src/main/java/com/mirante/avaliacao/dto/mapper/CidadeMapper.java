package com.mirante.avaliacao.dto.mapper;

import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.dto.CidadeRequestDTO;
import com.mirante.avaliacao.model.Cidade;
import com.mirante.avaliacao.model.enums.StatusCapital;
import org.springframework.stereotype.Component;

@Component
public class CidadeMapper {

    public Cidade toEntity(CidadeRequestDTO dto) {
        Cidade cidade = Cidade.builder()
                .nome(dto.getNome())
                .uf(dto.getUf())
                .capital(StatusCapital.get(dto.getCapital()))
                .build();
        return cidade;
    }

    //-----------------------------------------------
    /** Carrega o DTO com dados da entidade */
    //-----------------------------------------------
    public CidadeDTO toDTO(Cidade cidade) {
        CidadeDTO dto = new CidadeDTO();
        dto.setId(cidade.getId());
        dto.setNome(cidade.getNome());
        dto.setUf(cidade.getUf());
        dto.setCapital(StatusCapital.get(cidade.getCapital().getValor()).isValorSaida());
        return dto;
    }

}
