package br.com.alelo.consumer.consumerpat.entrypoint.http.request;

import br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto.CreateConsumerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateConsumerHttpRequest implements HttpRequest, CreateConsumerRequest {
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
    private String number;
    private String city;
    private String country;
    private String postalCode;

    private List<CreateCardHttpRequest> cards;
}
