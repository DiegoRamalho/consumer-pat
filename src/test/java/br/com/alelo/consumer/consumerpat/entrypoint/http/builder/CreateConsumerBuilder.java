package br.com.alelo.consumer.consumerpat.entrypoint.http.builder;

import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.CreateCardHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.CreateConsumerHttpRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public final class CreateConsumerBuilder {

    public static CreateConsumerHttpRequest fullCreateConsumerDTO() {
        final CreateConsumerHttpRequest result = new CreateConsumerHttpRequest();
        result.setName("create-name");
        result.setDocumentNumber("create-documentNumber");
        result.setBirthDate(LocalDate.of(2000, 1, 31));
        result.setMobilePhoneNumber("create-mobilePhoneNumber");
        result.setResidencePhoneNumber("create-residencePhoneNumber");
        result.setPhoneNumber("create-phoneNumber");
        result.setEmail("create-email");
        result.setStreet("create-street");
        result.setNumber("create-number");
        result.setCity("create-city");
        result.setCountry("create-country");
        result.setPostalCode("create-postalCode");
        result.setCards(Arrays.asList(foodAndFuelCreateCardDTO(), drugstoreCreateCardDTO(), null));
        return result;
    }

    public static CreateCardHttpRequest foodAndFuelCreateCardDTO() {
        CreateCardHttpRequest result = new CreateCardHttpRequest();
        result.setBalance(BigDecimal.TEN);
        result.setNumber("create-#1");
        result.setTypes(new HashSet<>(Arrays.asList(CardType.FOOD_CARD, CardType.FUEL_CARD)));
        return result;
    }

    public static CreateCardHttpRequest drugstoreCreateCardDTO() {
        CreateCardHttpRequest result = new CreateCardHttpRequest();
        result.setNumber("create-#2");
        result.setTypes(new HashSet<>(Collections.singletonList(CardType.DRUGSTORE_CARD)));
        return result;
    }
}
