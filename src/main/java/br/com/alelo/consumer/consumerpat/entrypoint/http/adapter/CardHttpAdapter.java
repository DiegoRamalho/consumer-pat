package br.com.alelo.consumer.consumerpat.entrypoint.http.adapter;

import br.com.alelo.consumer.consumerpat.core.usecase.transaction.create.dto.CreateTransactionResponse;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.CreateTransactionHttpResponse;

public final class CardHttpAdapter {
    private CardHttpAdapter() {
    }

    public static CreateTransactionHttpResponse from(CreateTransactionResponse dto) {
        return ObjectUtils.copyTo(dto, CreateTransactionHttpResponse.class);
    }
}
