package br.com.alelo.consumer.consumerpat.core.usecase.consumer.update.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Command;
import lombok.Builder;

public class UpdateConsumerDTO extends Command<UpdateConsumerRequest, UpdateConsumerResponse> {

    @Builder
    public UpdateConsumerDTO(UpdateConsumerRequest request, UpdateConsumerResponse response) {
        super(request, response);
    }
}
