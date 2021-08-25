package br.com.alelo.consumer.consumerpat.core.usecase.transaction.create.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Response;
import lombok.Data;

@Data
public class CreateTransactionResponse implements Response {

    private Long id;
}