package br.com.alelo.consumer.consumerpat.core.usecase.consumer.update.dto;

import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.CardIdRequest;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.CardNumberRequest;

import java.util.Set;

public interface UpdateCardRequest extends CardIdRequest, CardNumberRequest {

    Set<CardType> getTypes();
}
