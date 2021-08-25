package br.com.alelo.consumer.consumerpat.core.gateway;

import br.com.alelo.consumer.consumerpat.core.entity.Consumer;

import java.util.List;
import java.util.Optional;

public interface ConsumerGateway {

    Consumer save(Consumer consumer);

    Optional<Consumer> findById(Long id);

    List<Consumer> findAllWithCards();
}
