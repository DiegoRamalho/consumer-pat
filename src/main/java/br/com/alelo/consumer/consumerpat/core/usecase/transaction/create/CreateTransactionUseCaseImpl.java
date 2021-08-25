package br.com.alelo.consumer.consumerpat.core.usecase.transaction.create;

import br.com.alelo.consumer.consumerpat.core.adpter.CardAdapter;
import br.com.alelo.consumer.consumerpat.core.adpter.ExtractAdapter;
import br.com.alelo.consumer.consumerpat.core.entity.Card;
import br.com.alelo.consumer.consumerpat.core.entity.Extract;
import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.core.exception.ValidationException;
import br.com.alelo.consumer.consumerpat.core.gateway.CardGateway;
import br.com.alelo.consumer.consumerpat.core.gateway.ExtractGateway;
import br.com.alelo.consumer.consumerpat.core.usecase.CreateTransactionUseCase;
import br.com.alelo.consumer.consumerpat.core.usecase.FindCardByNumberUseCase;
import br.com.alelo.consumer.consumerpat.core.usecase.card.find.dto.FindCardByNumberDTO;
import br.com.alelo.consumer.consumerpat.core.usecase.transaction.create.dto.CreateTransactionDTO;
import br.com.alelo.consumer.consumerpat.core.usecase.transaction.create.dto.CreateTransactionRequest;
import br.com.alelo.consumer.consumerpat.core.usecase.transaction.operation.TransactionOperationStrategy;
import br.com.alelo.consumer.consumerpat.core.util.CardValidation;
import br.com.alelo.consumer.consumerpat.core.util.Constants;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final FindCardByNumberUseCase findCardByNumberUseCase;
    private final TransactionOperationStrategy transactionOperationStrategy;
    private final CardGateway cardGateway;
    private final ExtractGateway extractGateway;

    @Override
    public void execute(CreateTransactionDTO dto) {
        // Prepare
        final CreateTransactionRequest request = ObjectUtils.getRequest(dto);

        //Validate
        ObjectUtils.shouldNotBeNull(request.getEstablishmentType(), Constants.ESTABLISHMENT_TYPE_FIELD);
        ObjectUtils.shouldNotBeNull(request.getValue(), Constants.VALUE_FIELD);
        CardValidation.shouldHaveNumber(request.getNumber());

        CardType type = CardType.fromCode(request.getEstablishmentType());
        ObjectUtils.shouldNotBeNull(type, Constants.ESTABLISHMENT_TYPE_FIELD);

        Card card = getCardDB(request);

        shouldMatchTypes(card, type);
        BigDecimal value = transactionOperationStrategy.calculate(request.getValue(), type);
        card.setBalance(card.getBalance().subtract(value));

        card = cardGateway.save(card);

        Extract entity = Extract.builder()
                .card(card)
                .dateBuy(LocalDateTime.now())
                .establishmentName(request.getEstablishmentName())
                .productDescription(request.getProductDescription())
                .value(value)
                .build();
        final Extract result = extractGateway.save(entity);
        dto.setResponse(ExtractAdapter.getCreateTransactionResponseFrom(result));
    }

    private Card getCardDB(CreateTransactionRequest request) {
        FindCardByNumberDTO dto = FindCardByNumberDTO.builder()
                .request(request)
                .build();
        findCardByNumberUseCase.execute(dto);
        return CardAdapter.toCard(dto.getResponse());
    }

    private void shouldMatchTypes(Card card, CardType type) {
        Optional.ofNullable(card.getTypes())
                .filter(it -> it.contains(type))
                .orElseThrow(() -> new ValidationException(Constants.ESTABLISHMENT_TYPE_IS_NOT_SUPPORTED_BY_THE_RELATED_CARD));
    }
}
