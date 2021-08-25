package br.com.alelo.consumer.consumerpat.core.usecase.card.balance;

import br.com.alelo.consumer.consumerpat.core.adpter.CardAdapter;
import br.com.alelo.consumer.consumerpat.core.entity.Card;
import br.com.alelo.consumer.consumerpat.core.gateway.CardGateway;
import br.com.alelo.consumer.consumerpat.core.usecase.FindCardByNumberUseCase;
import br.com.alelo.consumer.consumerpat.core.usecase.IncreaseCardBalanceUseCase;
import br.com.alelo.consumer.consumerpat.core.usecase.card.balance.dto.IncreaseCardBalanceDTO;
import br.com.alelo.consumer.consumerpat.core.usecase.card.balance.dto.IncreaseCardBalanceRequest;
import br.com.alelo.consumer.consumerpat.core.usecase.card.find.dto.FindCardByNumberDTO;
import br.com.alelo.consumer.consumerpat.core.util.CardValidation;
import br.com.alelo.consumer.consumerpat.core.util.Constants;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncreaseCardBalanceUseCaseImpl implements IncreaseCardBalanceUseCase {

    private final CardGateway cardGateway;
    private final FindCardByNumberUseCase findCardByNumberUseCase;

    @Override
    public void execute(IncreaseCardBalanceDTO dto) {
        // Prepare
        final IncreaseCardBalanceRequest request = ObjectUtils.getRequest(dto);

        // Validate
        ObjectUtils.shouldNotBeNull(request.getValue(), Constants.VALUE_FIELD);
        CardValidation.shouldHaveNumber(request.getNumber());

        // Execute
        Card cardDB = getCardDB(request);
        cardDB.setBalance(Optional.ofNullable(cardDB.getBalance())
                .orElse(BigDecimal.ZERO)
                .add(request.getValue())
        );
        cardGateway.save(cardDB);
    }

    private Card getCardDB(IncreaseCardBalanceRequest request) {
        FindCardByNumberDTO dto = FindCardByNumberDTO.builder()
                .request(request)
                .build();
        findCardByNumberUseCase.execute(dto);
        return CardAdapter.toCard(dto.getResponse());
    }
}
