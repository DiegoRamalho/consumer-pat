package br.com.alelo.consumer.consumerpat.core.usecase.consumer.update.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Response;
import lombok.Data;

@Data
public class UpdateConsumerResponse implements Response {

    private Long id;
}
