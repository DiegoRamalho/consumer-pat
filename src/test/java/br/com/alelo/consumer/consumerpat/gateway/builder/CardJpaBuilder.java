package br.com.alelo.consumer.consumerpat.gateway.builder;


import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.CardJpa;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

public final class CardJpaBuilder {
    private CardJpaBuilder() {
    }

    public static CardJpa validCardJpa() {
        final CardJpa card = new CardJpa();
        card.setNumber("number");
        return card;
    }

    public static CardJpa emptyCardJpa() {
        return new CardJpa();
    }

    public static CardJpa foodCardJpa() {
        final CardJpa card = new CardJpa();
        card.setId(1L);
        card.setNumber("#1");
        card.setBalance(BigDecimal.ONE);
        card.setTypes(new HashSet<>(Collections.singletonList(CardType.FOOD_CARD)));
        return card;
    }

    public static CardJpa drugstoreCardJpa() {
        final CardJpa card = new CardJpa();
        card.setId(2L);
        card.setNumber("#2");
        card.setBalance(BigDecimal.TEN);
        card.setTypes(new HashSet<>(Collections.singletonList(CardType.DRUGSTORE_CARD)));
        return card;
    }

    public static CardJpa fuelCardJpa() {
        final CardJpa card = new CardJpa();
        card.setId(3L);
        card.setNumber("#3");
        card.setBalance(BigDecimal.TEN);
        card.setTypes(new HashSet<>(Collections.singletonList(CardType.FUEL_CARD)));
        return card;
    }
}
