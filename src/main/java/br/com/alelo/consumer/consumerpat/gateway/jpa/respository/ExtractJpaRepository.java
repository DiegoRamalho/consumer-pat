package br.com.alelo.consumer.consumerpat.gateway.jpa.respository;

import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.ExtractJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractJpaRepository extends JpaRepository<ExtractJpa, Long> {
}
