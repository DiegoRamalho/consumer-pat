package br.com.alelo.consumer.consumerpat.core.adpter;

import br.com.alelo.consumer.consumerpat.core.entity.Extract;
import br.com.alelo.consumer.consumerpat.core.usecase.transaction.create.dto.CreateTransactionResponse;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;

public final class ExtractAdapter {
    private ExtractAdapter() {
    }

    public static CreateTransactionResponse getCreateTransactionResponseFrom(Extract extract) {
        return ObjectUtils.copyTo(extract, CreateTransactionResponse.class);
    }
}
