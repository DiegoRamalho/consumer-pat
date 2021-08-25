package br.com.alelo.consumer.consumerpat.core.usecase.card.balance.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.CardNumberRequest;

import java.math.BigDecimal;

public interface IncreaseCardBalanceRequest extends CardNumberRequest {
    BigDecimal getValue();
}
