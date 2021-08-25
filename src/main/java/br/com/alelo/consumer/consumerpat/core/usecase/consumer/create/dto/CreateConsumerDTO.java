package br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Command;
import lombok.Builder;

public class CreateConsumerDTO extends Command<CreateConsumerRequest, CreateConsumerResponse> {
    @Builder
    public CreateConsumerDTO(CreateConsumerRequest request, CreateConsumerResponse response) {
        super(request, response);
    }
}

