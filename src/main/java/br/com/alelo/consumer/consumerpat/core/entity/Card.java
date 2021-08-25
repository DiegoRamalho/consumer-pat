package br.com.alelo.consumer.consumerpat.core.entity;

import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class Card implements Entity<Long> {
    private Long id;
    private String number;
    private BigDecimal balance;
    private Set<CardType> types;
    private Consumer consumer;
}
