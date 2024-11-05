package com.mirante.avaliacao.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@ToString
@AllArgsConstructor
public enum StatusCapital {

    SIM(1, true),
    NAO(0, false);

    int valor;
    boolean valorSaida;

    public static StatusCapital get(boolean valor){
        return valor == true ? SIM : NAO;
    }

    public static StatusCapital get(int valor){
        return valor == 1 ? SIM : NAO;
    }

    @Converter(autoApply = true)
    public static class CapitalConverter implements AttributeConverter<StatusCapital, Integer> {

        @Override
        public Integer convertToDatabaseColumn(StatusCapital capital) {
            return Optional.ofNullable(capital)
                    .map(StatusCapital::getValor)
                    .orElse(null);
        }

        @Override
        public StatusCapital convertToEntityAttribute(Integer valor) {
            return valor == 1 ? SIM : NAO;
        }

    }

}
