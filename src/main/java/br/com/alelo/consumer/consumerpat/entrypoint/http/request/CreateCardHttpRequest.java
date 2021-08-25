package br.com.alelo.consumer.consumerpat.entrypoint.http.request;

import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.core.usecase.consumer.create.dto.CreateCardRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardHttpRequest implements HttpRequest, CreateCardRequest {

    private String number;
    private BigDecimal balance;
    private Set<CardType> types;
}
