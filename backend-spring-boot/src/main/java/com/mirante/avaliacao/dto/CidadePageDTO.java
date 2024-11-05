package com.mirante.avaliacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CidadePageDTO {

    private List<CidadeDTO> cidades;
    private long totalElements;
    private int totalPages;

}
