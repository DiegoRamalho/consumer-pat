package br.com.alelo.consumer.consumerpat.gateway.jpa.respository;

import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.ConsumerJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumerJpaRepository extends JpaRepository<ConsumerJpa, Long> {

    @Query("SELECT DISTINCT c FROM ConsumerJpa c LEFT JOIN FETCH c.cards cd LEFT JOIN FETCH cd.types")
    List<ConsumerJpa> findAllWithCards();
}
