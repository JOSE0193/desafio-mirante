package com.mirante.avaliacao.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CidadeRequestDTO {

    private String nome;
    private String uf;
    private Boolean capital;

}
