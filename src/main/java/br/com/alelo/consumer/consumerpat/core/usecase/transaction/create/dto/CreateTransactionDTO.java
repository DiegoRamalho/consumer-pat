package br.com.alelo.consumer.consumerpat.core.usecase.transaction.create.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Command;
import lombok.Builder;

public class CreateTransactionDTO extends Command<CreateTransactionRequest, CreateTransactionResponse> {

    @Builder
    public CreateTransactionDTO(CreateTransactionRequest request, CreateTransactionResponse response) {
        super(request, response);
    }
}
