package br.com.alelo.consumer.consumerpat.core.usecase.card.find.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Command;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.CardNumberRequest;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.CardResponse;
import lombok.Builder;

public class FindCardByNumberDTO extends Command<CardNumberRequest, CardResponse> {
    @Builder
    public FindCardByNumberDTO(CardNumberRequest request, CardResponse response) {
        super(request, response);
    }
}