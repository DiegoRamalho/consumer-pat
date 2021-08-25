package br.com.alelo.consumer.consumerpat.core.usecase.dto;

import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CardResponse implements Response {
    private Long id;
    private String number;
    private BigDecimal balance;
    private Set<CardType> types;
}
