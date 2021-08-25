package br.com.alelo.consumer.consumerpat.core.usecase.consumer.update;

import br.com.alelo.consumer.consumerpat.core.adpter.ConsumerAdapter;
import br.com.alelo.consumer.consumerpat.core.entity.Card;
import br.com.alelo.consumer.consumerpat.core.entity.Consumer;
import br.com.alelo.consumer.consumerpat.core.gateway.ConsumerGateway;
import br.com.alelo.consumer.consumerpat.core.usecase.FindConsumerUseCase;
import br.com.alelo.consumer.consumerpat.core.usecase.UpdateConsumerUseCase;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.find.dto.FindConsumerDTO;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.update.dto.UpdateConsumerDTO;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.update.dto.UpdateConsumerRequest;
import br.com.alelo.consumer.consumerpat.core.util.ConsumerValidation;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static br.com.alelo.consumer.consumerpat.core.util.ConsumerValidation.shouldHaveId;
import static java.util.Objects.nonNull;


@Service
@RequiredArgsConstructor
public class UpdateConsumerUseCaseImpl implements UpdateConsumerUseCase {

    private final ConsumerGateway consumerGateway;
    private final FindConsumerUseCase findConsumerUseCase;

    @Override
    public void execute(UpdateConsumerDTO dto) {
        // Prepare
        final UpdateConsumerRequest request = ObjectUtils.getRequest(dto);

        // Validate
        shouldHaveId(request.getId());

        // Execute
        Consumer consumerDB = getConsumerDB(request);
        ObjectUtils.copyNonNullProperties(request, consumerDB);

        final Consumer consumer = ConsumerAdapter.toConsumer(request);
        updateCards(consumer, consumerDB);
        ConsumerValidation.validate(consumerDB);

        final Consumer result = consumerGateway.save(consumer);
        dto.setResponse(ConsumerAdapter.getUpdateConsumerResponseFrom(result));
    }

    private Consumer getConsumerDB(UpdateConsumerRequest request) {
        FindConsumerDTO dto = FindConsumerDTO.builder()
                .request(request)
                .build();
        findConsumerUseCase.execute(dto);
        return ConsumerAdapter.toConsumer(dto.getResponse());
    }

    private void updateCards(Consumer consumer, Consumer consumerDb) {
        // TODO Check for unique card Number
        if (nonNull(consumer.getCards())) {
            final List<Card> cardsToAdd = new LinkedList<>();
            final List<Card> cardList = Optional.ofNullable(consumerDb.getCards()).orElse(Collections.emptyList());
            Map<Long, Card> cardsMap = cardList
                    .stream()
                    .collect(Collectors.toMap(Card::getId, Function.identity()));
            consumer.getCards().forEach(it -> {
                if (cardsMap.containsKey(it.getId())) {
                    final Card cardDB = cardsMap.get(it.getId());
                    cardDB.setNumber(it.getNumber());
                    cardDB.setTypes(it.getTypes());
                } else {
                    it.setId(null);
                    setDefault(it, consumerDb);
                    cardsToAdd.add(it);
                }
            });
            cardList.addAll(cardsToAdd);
            consumer.setCards(cardList);
        }
    }

    private void setDefault(Card card, Consumer entity) {
        card.setConsumer(entity);
        card.setBalance(Optional.ofNullable(card.getBalance()).orElse(BigDecimal.ZERO));
    }

}
