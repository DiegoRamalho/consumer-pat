package br.com.alelo.consumer.consumerpat.gateway.jpa.adpter;

import br.com.alelo.consumer.consumerpat.core.entity.Card;
import br.com.alelo.consumer.consumerpat.core.entity.Consumer;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;
import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.CardJpa;
import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.ConsumerJpa;

public final class ConsumerJpaAdapter {
    private ConsumerJpaAdapter() {
    }

    public static Consumer toConsumer(ConsumerJpa entity) {
        final Consumer result = ObjectUtils.copyTo(entity, Consumer.class);
        result.setCards(ObjectUtils.copyListTo(entity.getCards(), Card.class));
        return result;
    }

    public static ConsumerJpa fromConsumer(Consumer consumer) {
        final ConsumerJpa result = ObjectUtils.copyTo(consumer, ConsumerJpa.class);
        result.setCards(ObjectUtils.copyListTo(consumer.getCards(), CardJpa.class));
        result.getCards().forEach(it -> it.setConsumer(result));
        return result;
    }
}
