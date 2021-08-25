package br.com.alelo.consumer.consumerpat.core.usecase.consumer.find.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Command;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.VoidParameter;
import lombok.Builder;

import java.util.List;

public class ListConsumerDTO extends Command<VoidParameter, List<ConsumerResponse>> {
    @Builder
    public ListConsumerDTO(VoidParameter request, List<ConsumerResponse> response) {
        super(request, response);
    }
}