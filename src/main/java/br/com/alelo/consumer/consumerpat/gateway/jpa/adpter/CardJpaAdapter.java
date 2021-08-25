package br.com.alelo.consumer.consumerpat.gateway.jpa.adpter;

import br.com.alelo.consumer.consumerpat.core.entity.Card;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;
import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.CardJpa;

public final class CardJpaAdapter {
    private CardJpaAdapter() {
    }

    public static Card toCard(CardJpa entity) {
        return ObjectUtils.copyTo(entity, Card.class);
    }

    public static CardJpa fromCard(Card card) {
        return ObjectUtils.copyTo(card, CardJpa.class);
    }
}
