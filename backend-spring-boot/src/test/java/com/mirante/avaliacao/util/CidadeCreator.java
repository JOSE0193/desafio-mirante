package com.mirante.avaliacao.util;

import com.mirante.avaliacao.dto.CidadeDTO;
import com.mirante.avaliacao.dto.CidadeRequestDTO;
import com.mirante.avaliacao.model.Cidade;
import com.mirante.avaliacao.model.enums.StatusCapital;

public class CidadeCreator {

    public static Cidade criadorCidadeParaSalvar(){
        return Cidade.builder()
                .nome("Brasilia")
                .uf("DF")
                .capital(StatusCapital.SIM)
                .build();
    }

    public static CidadeDTO criadorCidadeDTO(){
        CidadeDTO dto = new CidadeDTO();
        dto.setId(1L);
        dto.setNome("Brasilia");
        dto.setUf("DF");
        dto.setCapital(StatusCapital.SIM.isValorSaida());
        return dto;
    }

    public static Cidade criadorCidadeValida(){
        return Cidade.builder()
                .nome("Brasilia")
                .uf("DF")
                .capital(StatusCapital.SIM)
                .build();
    }

    public static CidadeRequestDTO criadorCidadeResquestDTO(){
        CidadeRequestDTO cidadeRequestDTO = new CidadeRequestDTO();
        cidadeRequestDTO.setNome("Brasilia");
        cidadeRequestDTO.setUf("DF");
        cidadeRequestDTO.setCapital(StatusCapital.SIM.isValorSaida());
        return cidadeRequestDTO;
    }

}
