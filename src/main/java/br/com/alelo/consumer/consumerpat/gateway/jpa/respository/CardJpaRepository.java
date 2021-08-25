package br.com.alelo.consumer.consumerpat.gateway.jpa.respository;

import br.com.alelo.consumer.consumerpat.core.entity.Consumer;
import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.CardJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardJpaRepository extends JpaRepository<CardJpa, Long> {
    Optional<CardJpa> findOneByNumber(String number);

    @Query("SELECT c FROM CardJpa c INNER JOIN FETCH c.types WHERE c.consumer = ?1")
    List<CardJpa> findAllByConsumer(Consumer consumer);
}
