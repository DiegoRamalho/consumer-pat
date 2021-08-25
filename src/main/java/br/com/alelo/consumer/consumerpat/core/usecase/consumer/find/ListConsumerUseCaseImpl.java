package br.com.alelo.consumer.consumerpat.core.usecase.consumer.find;

import br.com.alelo.consumer.consumerpat.core.entity.Consumer;
import br.com.alelo.consumer.consumerpat.core.gateway.ConsumerGateway;
import br.com.alelo.consumer.consumerpat.core.usecase.ListConsumerUseCase;
import br.com.alelo.consumer.consumerpat.core.adpter.ConsumerAdapter;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.find.dto.ListConsumerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListConsumerUseCaseImpl implements ListConsumerUseCase {

    private final ConsumerGateway consumerGateway;

    @Override
    public void execute(ListConsumerDTO dto) {
        //Execute
        List<Consumer> consumers = consumerGateway.findAllWithCards();
        dto.setResponse(Optional.ofNullable(consumers)
                .orElse(Collections.emptyList())
                .stream()
                .map(ConsumerAdapter::getConsumerResponseFrom)
                .collect(Collectors.toList())
        );
    }
}
