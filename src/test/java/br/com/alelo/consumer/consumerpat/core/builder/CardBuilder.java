package br.com.alelo.consumer.consumerpat.core.builder;


import br.com.alelo.consumer.consumerpat.core.entity.Card;
import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;

public final class CardBuilder {
    private CardBuilder() {
    }

    public static Card validCard() {
        final Card card = new Card();
        card.setNumber("number");
        return card;
    }

    public static Card emptyCard() {
        return new Card();
    }

    public static Card foodCard() {
        final Card card = new Card();
        card.setId(1L);
        card.setNumber("#1");
        card.setBalance(BigDecimal.ONE);
        card.setTypes(new HashSet<>(Collections.singletonList(CardType.FOOD_CARD)));
        return card;
    }

    public static Card drugstoreCard() {
        final Card card = new Card();
        card.setId(2L);
        card.setNumber("#2");
        card.setBalance(BigDecimal.TEN);
        card.setTypes(new HashSet<>(Collections.singletonList(CardType.DRUGSTORE_CARD)));
        return card;
    }

    public static Card fuelCard() {
        final Card card = new Card();
        card.setId(3L);
        card.setNumber("#3");
        card.setBalance(BigDecimal.TEN);
        card.setTypes(new HashSet<>(Collections.singletonList(CardType.FUEL_CARD)));
        return card;
    }
}
