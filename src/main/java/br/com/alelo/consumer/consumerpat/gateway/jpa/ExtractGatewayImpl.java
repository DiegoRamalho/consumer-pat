package br.com.alelo.consumer.consumerpat.gateway.jpa;

import br.com.alelo.consumer.consumerpat.core.entity.Extract;
import br.com.alelo.consumer.consumerpat.core.gateway.ExtractGateway;
import br.com.alelo.consumer.consumerpat.gateway.jpa.respository.ExtractJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.alelo.consumer.consumerpat.gateway.jpa.adpter.ExtractJpaAdapter.fromExtract;
import static br.com.alelo.consumer.consumerpat.gateway.jpa.adpter.ExtractJpaAdapter.toExtract;


@Component
@RequiredArgsConstructor
public class ExtractGatewayImpl implements ExtractGateway {

    private final ExtractJpaRepository repository;

    @Override
    public Extract save(Extract extract) {
        return toExtract(repository.save(fromExtract(extract)));
    }
}
