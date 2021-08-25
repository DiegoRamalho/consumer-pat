package br.com.alelo.consumer.consumerpat.core.usecase.consumer.find.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.ConsumerIdRequest;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.Command;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.ConsumerResponse;
import lombok.Builder;

public class FindConsumerDTO extends Command<ConsumerIdRequest, ConsumerResponse> {
    @Builder
    public FindConsumerDTO(ConsumerIdRequest request, ConsumerResponse response) {
        super(request, response);
    }
}