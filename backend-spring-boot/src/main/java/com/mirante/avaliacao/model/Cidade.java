package com.mirante.avaliacao.model;

import com.mirante.avaliacao.model.enums.StatusCapital;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//-------------------------------------------------
/** Entidade que guarda os dados de uma cidade */
//-------------------------------------------------
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "UF", length = 100, nullable = false)
    private String uf;

    @NotNull
    @Convert(converter = StatusCapital.CapitalConverter.class)
    @Column(name = "capital", nullable = false)
    private StatusCapital capital;

}