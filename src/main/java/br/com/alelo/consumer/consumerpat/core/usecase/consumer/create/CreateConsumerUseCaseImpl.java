package br.com.alelo.consumer.consumerpat.core.usecase.consumer.create;

import br.com.alelo.consumer.consumerpat.core.entity.Consumer;
import br.com.alelo.consumer.consumerpat.core.gateway.ConsumerGateway;
import br.com.alelo.consumer.consumerpat.core.usecase.CreateConsumerUseCase;
import br.com.alelo.consumer.consumerpat.core.adpter.ConsumerAdapter;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto.CreateConsumerDTO;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto.CreateConsumerRequest;
import br.com.alelo.consumer.consumerpat.core.util.ConsumerValidation;
import br.com.alelo.consumer.consumerpat.core.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CreateConsumerUseCaseImpl implements CreateConsumerUseCase {

    private final ConsumerGateway consumerGateway;

    @Override
    public void execute(CreateConsumerDTO dto) {
        // Prepare
        final CreateConsumerRequest request = ObjectUtils.getRequest(dto);
        final Consumer consumer = ConsumerAdapter.toConsumer(request);
        setDefaults(consumer);

        // Validate
        ConsumerValidation.validate(consumer);

        //Execute
        final Consumer result = consumerGateway.save(consumer);
        dto.setResponse(ConsumerAdapter.getCreateConsumerResponseFrom(result));
    }


    private void setDefaults(Consumer entity) {
        entity.setCards(Optional.ofNullable(entity.getCards())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(it -> {
                    it.setConsumer(entity);
                    it.setBalance(Optional.ofNullable(it.getBalance()).orElse(BigDecimal.ZERO));
                    return it;
                })
                .collect(Collectors.toList()));
    }
}
