package br.com.alelo.consumer.consumerpat.core.adpter;

import br.com.alelo.consumer.consumerpat.core.entity.Card;
import br.com.alelo.consumer.consumerpat.core.entity.Consumer;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto.CreateConsumerRequest;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto.CreateConsumerResponse;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.update.dto.UpdateConsumerRequest;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.update.dto.UpdateConsumerResponse;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.CardResponse;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.ConsumerResponse;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;

public final class ConsumerAdapter {
    private ConsumerAdapter() {
    }

    public static Consumer toConsumer(CreateConsumerRequest dto) {
        final Consumer result = ObjectUtils.copyTo(dto, Consumer.class);
        result.setCards(ObjectUtils.copyListTo(dto.getCards(), Card.class));
        return result;
    }

    public static Consumer toConsumer(ConsumerResponse dto) {
        final Consumer result = ObjectUtils.copyTo(dto, Consumer.class);
        result.setCards(ObjectUtils.copyListTo(dto.getCards(), Card.class));
        return result;
    }

    public static Consumer toConsumer(UpdateConsumerRequest dto) {
        final Consumer result = ObjectUtils.copyTo(dto, Consumer.class);
        result.setCards(ObjectUtils.copyListTo(dto.getCards(), Card.class));
        return result;
    }

    public static CreateConsumerResponse getCreateConsumerResponseFrom(Consumer entity) {
        return ObjectUtils.copyTo(entity, CreateConsumerResponse.class);
    }

    public static UpdateConsumerResponse getUpdateConsumerResponseFrom(Consumer entity) {
        return ObjectUtils.copyTo(entity, UpdateConsumerResponse.class);
    }

    public static ConsumerResponse getConsumerResponseFrom(Consumer entity) {
        final ConsumerResponse result = ObjectUtils.copyTo(entity, ConsumerResponse.class);
        result.setCards(ObjectUtils.copyListTo(entity.getCards(), CardResponse.class));
        return result;
    }
}
