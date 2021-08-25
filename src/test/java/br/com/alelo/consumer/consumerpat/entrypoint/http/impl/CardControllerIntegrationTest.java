package br.com.alelo.consumer.consumerpat.entrypoint.http.impl;

import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.entrypoint.http.BaseIntegrationTest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.CreateTransactionHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.IncreaseCardBalanceHttpRequest;
import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.CardJpa;
import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.ConsumerJpa;
import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.ExtractJpa;
import br.com.alelo.consumer.consumerpat.gateway.jpa.respository.CardJpaRepository;
import br.com.alelo.consumer.consumerpat.gateway.jpa.respository.ConsumerJpaRepository;
import br.com.alelo.consumer.consumerpat.gateway.jpa.respository.ExtractJpaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.entrypoint.http.builder.CreateTransactionBuilder.fullTransactionDTO;
import static br.com.alelo.consumer.consumerpat.entrypoint.http.builder.IncreaseCardBalanceBuilder.fullIncreaseCardBalanceDTO;
import static br.com.alelo.consumer.consumerpat.gateway.builder.CardJpaBuilder.*;
import static br.com.alelo.consumer.consumerpat.gateway.builder.ConsumerJpaBuilder.emptyConsumerJpa;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CardControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private CardJpaRepository cardRepository;
    @Autowired
    private ConsumerJpaRepository consumerRepository;
    @Autowired
    private ExtractJpaRepository extractRepository;

    private CardJpa fuelCard;
    private CardJpa foodCard;
    private CardJpa drugstoreCard;

    @BeforeEach
    public void resetRepository() {
        cleanRepository();
        final ConsumerJpa consumerDB = consumerRepository.save(getConsumer());

        fuelCard = fuelCardJpa();
        fuelCard.setConsumer(consumerDB);
        fuelCard = cardRepository.save(fuelCard);

        foodCard = foodCardJpa();
        foodCard.setConsumer(consumerDB);
        foodCard = cardRepository.save(foodCard);

        drugstoreCard = drugstoreCardJpa();
        drugstoreCard.setConsumer(consumerDB);
        drugstoreCard = cardRepository.save(drugstoreCard);
    }

    @AfterEach
    public void cleanRepository() {
        extractRepository.deleteAll();
        cardRepository.deleteAll();
        consumerRepository.deleteAll();
    }

    @Test
    public void shouldIncreaseCardBalance() throws Exception {
        IncreaseCardBalanceHttpRequest request = fullIncreaseCardBalanceDTO();
        request.setNumber(fuelCard.getNumber());

        mvc.perform(post("/card/increaseCardBalance")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isNoContent());

        CardJpa card = getCard(fuelCard.getId());
        assertEquals(new BigDecimal("11"), card.getBalance());
    }

    @Test
    public void shouldProcessFoodTransaction() throws Exception {
        CreateTransactionHttpRequest request = fullTransactionDTO();
        request.setNumber(foodCard.getNumber());
        request.setEstablishmentType(CardType.FOOD_CARD.getCode());

        mvc.perform(post("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isNoContent());

        CardJpa card = getCard(foodCard.getId());
        assertEquals(new BigDecimal("0.10"), card.getBalance());
        assertExtract(request, new BigDecimal("0.90"));
    }

    @Test
    public void shouldProcessFuelTransaction() throws Exception {
        CreateTransactionHttpRequest request = fullTransactionDTO();
        request.setNumber(fuelCard.getNumber());
        request.setEstablishmentType(CardType.FUEL_CARD.getCode());

        mvc.perform(post("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isNoContent());

        CardJpa card = getCard(fuelCard.getId());
        assertEquals(new BigDecimal("8.65"), card.getBalance());
        assertExtract(request, new BigDecimal("1.35"));
    }

    @Test
    public void shouldProcessDrugstoreTransaction() throws Exception {
        CreateTransactionHttpRequest request = fullTransactionDTO();
        request.setNumber(drugstoreCard.getNumber());
        request.setEstablishmentType(CardType.DRUGSTORE_CARD.getCode());

        mvc.perform(post("/card/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)))
                .andExpect(status().isNoContent());

        CardJpa card = getCard(drugstoreCard.getId());
        assertEquals(new BigDecimal("9.00"), card.getBalance());
        assertExtract(request, new BigDecimal("1.00"));
    }

    private CardJpa getCard(Long id) {
        assertNotNull(id);
        final Optional<CardJpa> optional = cardRepository.findById(id);
        assertTrue(optional.isPresent());
        return optional.get();
    }

    private void assertExtract(CreateTransactionHttpRequest request, BigDecimal value) {
        final List<ExtractJpa> extracts = extractRepository.findAll();
        assertEquals(1, extracts.size());
        final ExtractJpa extract = extracts.get(0);
        assertEquals(request.getNumber(), extract.getCard().getNumber());
        assertEquals(request.getEstablishmentName(), extract.getEstablishmentName());
        assertEquals(request.getProductDescription(), extract.getProductDescription());
        assertEquals(value, extract.getValue());
    }

    private ConsumerJpa getConsumer() {
        return consumerRepository.save(emptyConsumerJpa());
    }
}