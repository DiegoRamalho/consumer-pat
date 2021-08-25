package br.com.alelo.consumer.consumerpat.core.usecase.card.find;

import br.com.alelo.consumer.consumerpat.core.entity.Card;
import br.com.alelo.consumer.consumerpat.core.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.core.gateway.CardGateway;
import br.com.alelo.consumer.consumerpat.core.usecase.FindCardByNumberUseCase;
import br.com.alelo.consumer.consumerpat.core.adpter.CardAdapter;
import br.com.alelo.consumer.consumerpat.core.usecase.card.find.dto.FindCardByNumberDTO;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.CardNumberRequest;
import br.com.alelo.consumer.consumerpat.core.util.CardValidation;
import br.com.alelo.consumer.consumerpat.core.util.Constants;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FindCardByNumberUseCaseImpl implements FindCardByNumberUseCase {

    private final CardGateway cardGateway;

    @Override
    public void execute(FindCardByNumberDTO dto) {
        // Prepare
        final CardNumberRequest request = ObjectUtils.getRequest(dto);

        // Validate
        CardValidation.shouldHaveNumber(request.getNumber());

        // Execute
        Card card = cardGateway.findOneByNumber(request.getNumber())
                .orElseThrow(() -> new NotFoundException(
                        String.format(Constants.CARD_NUMBER_NOT_FOUND, request.getNumber()),
                        Constants.CARD_NOT_FOUND));

        dto.setResponse(CardAdapter.getCardResponseFrom(card));
    }
}
