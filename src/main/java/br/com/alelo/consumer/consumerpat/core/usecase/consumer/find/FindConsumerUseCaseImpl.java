package br.com.alelo.consumer.consumerpat.core.usecase.consumer.find;

import br.com.alelo.consumer.consumerpat.core.entity.Consumer;
import br.com.alelo.consumer.consumerpat.core.exception.NotFoundException;
import br.com.alelo.consumer.consumerpat.core.gateway.ConsumerGateway;
import br.com.alelo.consumer.consumerpat.core.usecase.FindConsumerUseCase;
import br.com.alelo.consumer.consumerpat.core.adpter.ConsumerAdapter;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.find.dto.FindConsumerDTO;
import br.com.alelo.consumer.consumerpat.core.usecase.dto.ConsumerIdRequest;
import br.com.alelo.consumer.consumerpat.core.util.Constants;
import br.com.alelo.consumer.consumerpat.core.util.ConsumerValidation;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindConsumerUseCaseImpl implements FindConsumerUseCase {

    private final ConsumerGateway consumerGateway;

    @Override
    public void execute(FindConsumerDTO dto) {
        // Prepare
        final ConsumerIdRequest request = ObjectUtils.getRequest(dto);

        // Validate
        ConsumerValidation.shouldHaveId(request.getId());

        // Execute
        Consumer consumer = consumerGateway.findById(request.getId())
                .orElseThrow(() -> new NotFoundException(String.format(Constants.CONSUMER_ID_D_NOT_FOUND, request.getId()),
                        Constants.CONSUMER_NOT_FOUND));

        dto.setResponse(ConsumerAdapter.getConsumerResponseFrom(consumer));
    }
}
