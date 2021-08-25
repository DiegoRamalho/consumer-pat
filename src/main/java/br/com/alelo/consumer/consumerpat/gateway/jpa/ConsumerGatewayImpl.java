package br.com.alelo.consumer.consumerpat.gateway.jpa;

import br.com.alelo.consumer.consumerpat.core.entity.Consumer;
import br.com.alelo.consumer.consumerpat.core.gateway.ConsumerGateway;
import br.com.alelo.consumer.consumerpat.gateway.jpa.adpter.ConsumerJpaAdapter;
import br.com.alelo.consumer.consumerpat.gateway.jpa.respository.ConsumerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.alelo.consumer.consumerpat.gateway.jpa.adpter.ConsumerJpaAdapter.fromConsumer;
import static br.com.alelo.consumer.consumerpat.gateway.jpa.adpter.ConsumerJpaAdapter.toConsumer;

@Component
@RequiredArgsConstructor
public class ConsumerGatewayImpl implements ConsumerGateway {

    private final ConsumerJpaRepository repository;

    @Override
    public Consumer save(Consumer consumer) {
        return toConsumer(repository.save(fromConsumer(consumer)));
    }

    @Override
    public Optional<Consumer> findById(Long id) {
        return repository.findById(id)
                .map(ConsumerJpaAdapter::toConsumer);
    }

    @Override
    public List<Consumer> findAllWithCards() {
        return repository.findAllWithCards().stream()
                .map(ConsumerJpaAdapter::toConsumer)
                .collect(Collectors.toList());
    }
}
