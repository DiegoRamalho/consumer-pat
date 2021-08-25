package br.com.alelo.consumer.consumerpat.gateway.jpa;

import br.com.alelo.consumer.consumerpat.core.entity.Card;
import br.com.alelo.consumer.consumerpat.core.gateway.CardGateway;
import br.com.alelo.consumer.consumerpat.gateway.jpa.adpter.CardJpaAdapter;
import br.com.alelo.consumer.consumerpat.gateway.jpa.respository.CardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.gateway.jpa.adpter.CardJpaAdapter.fromCard;
import static br.com.alelo.consumer.consumerpat.gateway.jpa.adpter.CardJpaAdapter.toCard;

@Component
@RequiredArgsConstructor
public class CardGatewayImpl implements CardGateway {

    private final CardJpaRepository repository;

    @Override
    public Optional<Card> findOneByNumber(String number) {
        return repository.findOneByNumber(number)
                .map(CardJpaAdapter::toCard);
    }

    @Override
    public Card save(Card card) {
        return toCard(repository.save(fromCard(card)));
    }
}
