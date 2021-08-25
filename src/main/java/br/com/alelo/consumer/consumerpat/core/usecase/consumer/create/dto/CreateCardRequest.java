package br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto;

import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.CardNumberRequest;

import java.math.BigDecimal;
import java.util.Set;

public interface CreateCardRequest extends CardNumberRequest {
    BigDecimal getBalance();

    Set<CardType> getTypes();
}
