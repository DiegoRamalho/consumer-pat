package br.com.alelo.consumer.consumerpat.entrypoint.http.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class ConsumerHttpResponse implements HttpResponse {
    private Long id;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    //contacts
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    //Address
    private String street;
    private Integer number;
    private String city;
    private String country;
    private String postalCode;

    //cards
    private List<CardHttpResponse> cards;
}
