package br.com.alelo.consumer.consumerpat.entrypoint.http.request;

import br.com.alelo.consumer.consumerpat.core.usecase.transaction.create.dto.CreateTransactionRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateTransactionHttpRequest implements HttpRequest, CreateTransactionRequest {
    private Integer establishmentType;
    private String establishmentName;
    @JsonProperty(value = "cardNumber")
    private String number;
    private String productDescription;
    private BigDecimal value;

}
