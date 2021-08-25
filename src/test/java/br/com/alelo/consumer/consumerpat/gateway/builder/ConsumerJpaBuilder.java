package br.com.alelo.consumer.consumerpat.gateway.builder;

import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.ConsumerJpa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static br.com.alelo.consumer.consumerpat.gateway.builder.CardJpaBuilder.*;


public final class ConsumerJpaBuilder {
    private ConsumerJpaBuilder() {
    }

    public static ConsumerJpa consumerWithValidCard() {
        final ConsumerJpa consumer = new ConsumerJpa();
        consumer.setCards(Collections.singletonList(validCardJpa()));
        return consumer;
    }

    public static ConsumerJpa consumerWithNullCard() {
        final ConsumerJpa consumer = new ConsumerJpa();
        consumer.setCards(Collections.singletonList(null));
        return consumer;
    }

    public static ConsumerJpa consumerWithEmptyCard() {
        final ConsumerJpa consumer = new ConsumerJpa();
        consumer.setCards(Collections.singletonList(emptyCardJpa()));
        return consumer;
    }

    public static ConsumerJpa consumerWith2Cards() {
        final ConsumerJpa consumer = new ConsumerJpa();
        consumer.setId(1L);
        consumer.setCards(new ArrayList<>(Arrays.asList(foodCardJpa(), fuelCardJpa())));
        return consumer;
    }

    public static ConsumerJpa emptyConsumerJpa() {
        return new ConsumerJpa();
    }
}
