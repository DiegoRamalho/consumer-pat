package br.com.alelo.consumer.consumerpat.entrypoint.http.impl;

import br.com.alelo.consumer.consumerpat.core.util.Constants;
import br.com.alelo.consumer.consumerpat.entrypoint.http.BaseIntegrationTest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.CreateConsumerHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.UpdateConsumerHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.BaseHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.ConsumerHttpResponse;
import br.com.alelo.consumer.consumerpat.entrypoint.http.response.CreateConsumerHttpResponse;
import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.CardJpa;
import br.com.alelo.consumer.consumerpat.gateway.jpa.entity.ConsumerJpa;
import br.com.alelo.consumer.consumerpat.gateway.jpa.respository.ConsumerJpaRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;
import java.util.Optional;

import static br.com.alelo.consumer.consumerpat.entrypoint.http.builder.CreateConsumerBuilder.fullCreateConsumerDTO;
import static br.com.alelo.consumer.consumerpat.entrypoint.http.builder.UpdateConsumerBuilder.fullUpdateConsumerDTO;
import static br.com.alelo.consumer.consumerpat.service.ConsumerServiceTest.assertCreateConsumer;
import static br.com.alelo.consumer.consumerpat.service.ConsumerServiceTest.assertUpdateConsumer;
import static java.util.Objects.nonNull;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ConsumerControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private ConsumerJpaRepository consumerRepository;

    @BeforeEach
    public void resetRepository() {
        cleanRepository();
    }

    @AfterEach
    public void cleanRepository() {
        consumerRepository.deleteAll();
    }

    @Test
    public void shouldCreateAndUpdateConsumerWithAllFields() throws Exception {
        final ConsumerJpa consumer = shouldCreateConsumerAndGetIt();
        shouldUpdateConsumer(consumer);
        shouldFindAll();
    }

    @Test
    public void shouldNotUpdateNonExistentConsumer() throws Exception {
        final UpdateConsumerHttpRequest updateRequest = fullUpdateConsumerDTO();
        mvc.perform(patch("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updateRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").isNotEmpty())
                .andExpect(jsonPath("$.result.userMessage", containsString(Constants.CONSUMER_NOT_FOUND)))
                .andExpect(jsonPath("$.result.developerMessage", containsString(updateRequest.getId().toString())));
    }

    private void shouldUpdateConsumer(ConsumerJpa consumer) throws Exception {
        final UpdateConsumerHttpRequest updateRequest = getUpdateConsumer(consumer);
        mvc.perform(patch("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updateRequest)))
                .andExpect(status().isNoContent());
        ConsumerJpa consumerDB = getConsumerDB(consumer.getId());
        assertUpdateConsumer(updateRequest, consumerDB);
    }

    private UpdateConsumerHttpRequest getUpdateConsumer(ConsumerJpa consumer) {
        final UpdateConsumerHttpRequest updateRequest = fullUpdateConsumerDTO();
        updateRequest.setId(consumer.getId());
        updateRequest.getCards().forEach(it -> {
            if (nonNull(it) && nonNull(it.getId())) {
                it.setId(consumer.getCards().stream()
                        .filter(c -> c.getNumber().split("#")[1].equals(it.getNumber().split("#")[1]))
                        .map(CardJpa::getId).findFirst().orElse(null));
            }
        });
        return updateRequest;
    }

    private ConsumerJpa shouldCreateConsumerAndGetIt() throws Exception {
        final CreateConsumerHttpRequest createRequest = fullCreateConsumerDTO();
        MockHttpServletResponse response = mvc.perform(post("/consumer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(createRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").isNotEmpty())
                .andReturn()
                .getResponse();

        BaseHttpResponse<CreateConsumerHttpResponse> body = getResponseBody(response);
        final Long id = body.getResult().getId();
        ConsumerJpa consumerDB = getConsumerDB(id);

        assertEquals(id, consumerDB.getId());
        assertCreateConsumer(createRequest, consumerDB);
        return consumerDB;
    }

    private void shouldFindAll() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/consumer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.result").isNotEmpty())
                .andReturn()
                .getResponse();

        BaseHttpResponse<List<ConsumerHttpResponse>> body = getResponseBodyList(response);

        List<ConsumerJpa> consumerDB = findAll();

        final List<ConsumerHttpResponse> result = body.getResult();
        assertEquals(consumerDB.size(), result.size());
    }

    @SneakyThrows
    private BaseHttpResponse<List<ConsumerHttpResponse>> getResponseBodyList(MockHttpServletResponse response) {
        BaseHttpResponse<List<ConsumerHttpResponse>> result = asBaseHttpResponseList(response.getContentAsString());
        assertNotNull(result);
        assertNotNull(result.getResult());
        return result;
    }

    @SneakyThrows
    private BaseHttpResponse<CreateConsumerHttpResponse> getResponseBody(MockHttpServletResponse response) {
        BaseHttpResponse<CreateConsumerHttpResponse> result = asBaseHttpResponse(response.getContentAsString(), CreateConsumerHttpResponse.class);
        assertNotNull(result);
        assertNotNull(result.getResult());
        return result;
    }

    private ConsumerJpa getConsumerDB(Long id) {
        assertNotNull(id);
        final Optional<ConsumerJpa> optional = consumerRepository.findById(id);
        assertTrue(optional.isPresent());
        return optional.get();
    }

    private List<ConsumerJpa> findAll() {
        return consumerRepository.findAll();
    }
}