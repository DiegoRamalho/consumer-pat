package br.com.alelo.consumer.consumerpat.entrypoint.http.response;

import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CardHttpResponse implements HttpResponse {
    private Long id;
    private String number;
    private BigDecimal balance;
    private Set<CardType> types;
}
