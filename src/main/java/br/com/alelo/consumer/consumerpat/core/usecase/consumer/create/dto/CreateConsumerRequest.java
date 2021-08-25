package br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto;

import br.com.alelo.consumer.consumerpat.core.usecase.dto.Request;

import java.time.LocalDate;
import java.util.List;

public interface CreateConsumerRequest extends Request {

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
    List<? extends CreateCardRequest> getCards();
}
