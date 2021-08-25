package br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Response;
import lombok.Data;

@Data
public class CreateConsumerResponse implements Response {
    private Long id;
}
