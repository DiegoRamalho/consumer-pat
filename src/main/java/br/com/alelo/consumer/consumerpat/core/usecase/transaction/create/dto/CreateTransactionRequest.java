package br.com.alelo.consumer.consumerpat.core.usecase.transaction.create.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.CardNumberRequest;

import java.math.BigDecimal;

public interface CreateTransactionRequest extends CardNumberRequest {

    Integer getEstablishmentType();

    String getEstablishmentName();

    String getProductDescription();

    BigDecimal getValue();
}
