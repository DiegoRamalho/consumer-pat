package br.com.alelo.consumer.consumerpat.core.gateway;

import br.com.alelo.consumer.consumerpat.core.entity.Card;
import br.com.alelo.consumer.consumerpat.core.entity.Consumer;

import java.util.List;
import java.util.Optional;

public interface CardGateway {

    Optional<Card> findOneByNumber(String number);

    Card save(Card card);
}
