package br.com.alelo.consumer.consumerpat.core.usecase.consumer.update.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.ConsumerIdRequest;

import java.time.LocalDate;
import java.util.List;

public interface UpdateConsumerRequest extends ConsumerIdRequest {

    String getName();

    String getDocumentNumber();

    LocalDate getBirthDate();

    //contacts
    String getMobilePhoneNumber();

    String getResidencePhoneNumber();

    String getPhoneNumber();

    String getEmail();

    //Address
    String getStreet();

    String getNumber();

    String getCity();

    String getCountry();

    String getPostalCode();

    //cards
    List<? extends UpdateCardRequest> getCards();
}
