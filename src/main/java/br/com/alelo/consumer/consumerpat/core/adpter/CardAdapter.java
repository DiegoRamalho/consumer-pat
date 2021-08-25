package br.com.alelo.consumer.consumerpat.core.adpter;

import br.com.alelo.consumer.consumerpat.core.entity.Card;
import br.com.alelo.consumer.consumerpat.core.usecase.card.balance.dto.IncreaseCardBalanceRequest;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.CardResponse;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;

public final class CardAdapter {
    private CardAdapter() {
    }

    public static CardResponse getCardResponseFrom(Card card) {
        return ObjectUtils.copyTo(card, CardResponse.class);
    }

    public static Card toCard(IncreaseCardBalanceRequest request) {
        final Card card = ObjectUtils.copyTo(request, Card.class);
        card.setBalance(request.getValue());
        return card;
    }

    public static Card toCard(CardResponse response) {
        return ObjectUtils.copyTo(response, Card.class);
    }
}
