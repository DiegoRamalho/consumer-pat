package br.com.alelo.consumer.consumerpat.entrypoint.http.adapter;

import br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto.CreateConsumerResponse;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.CardHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.ConsumerHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.CreateConsumerHttpResponse;

public final class ConsumerHttpAdapter {
    private ConsumerHttpAdapter() {
    }

    public static ConsumerHttpResponse from(ConsumerResponse dto) {
        final ConsumerHttpResponse result = ObjectUtils.copyTo(dto, ConsumerHttpResponse.class);
        result.setCards(ObjectUtils.copyListTo(dto.getCards(), CardHttpResponse.class));
        return result;
    }

    public static CreateConsumerHttpResponse from(CreateConsumerResponse dto) {
        return ObjectUtils.copyTo(dto, CreateConsumerHttpResponse.class);
    }
}
