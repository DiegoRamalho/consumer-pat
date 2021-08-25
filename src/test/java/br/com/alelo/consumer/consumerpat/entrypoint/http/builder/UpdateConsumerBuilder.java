package br.com.alelo.consumer.consumerpat.entrypoint.http.builder;

import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.UpdateCardHttpRequest;
import br.com.alelo.consumer.consumerpat.entrypoint.http.request.UpdateConsumerHttpRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public final class UpdateConsumerBuilder {

    public static UpdateConsumerHttpRequest fullUpdateConsumerDTO() {
        final UpdateConsumerHttpRequest result = new UpdateConsumerHttpRequest();
        result.setId(1L);
        result.setName("update-name");
        result.setDocumentNumber("update-documentNumber");
        result.setBirthDate(LocalDate.of(2000, 3, 31));
        result.setMobilePhoneNumber("update-mobilePhoneNumber");
        result.setResidencePhoneNumber("update-residencePhoneNumber");
        result.setPhoneNumber("update-phoneNumber");
        result.setEmail("update-email");
        result.setStreet("update-street");
        result.setNumber("update-number");
        result.setCity("update-city");
        result.setCountry("update-country");
        result.setPostalCode("update-postalCode");
        result.setCards(Arrays.asList(foodAndFuelUpdateCardDTO(), drugstoreUpdateCardDTO(), null));
        return result;
    }

    public static UpdateCardHttpRequest foodAndFuelUpdateCardDTO() {
        UpdateCardHttpRequest result = new UpdateCardHttpRequest();
        result.setId(1L);
        result.setNumber("update-#1");
        result.setTypes(new HashSet<>(Arrays.asList(CardType.FOOD_CARD, CardType.FUEL_CARD)));
        return result;
    }

    public static UpdateCardHttpRequest drugstoreUpdateCardDTO() {
        UpdateCardHttpRequest result = new UpdateCardHttpRequest();
        result.setNumber("update-#2");
        result.setTypes(new HashSet<>(Collections.singletonList(CardType.DRUGSTORE_CARD)));
        return result;
    }
}
