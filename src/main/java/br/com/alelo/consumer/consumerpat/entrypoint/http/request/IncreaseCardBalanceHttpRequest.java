package br.com.alelo.consumer.consumerpat.entrypoint.http.request;

import br.com.alelo.consumer.consumerpat.core.usecase.card.balance.dto.IncreaseCardBalanceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncreaseCardBalanceHttpRequest implements HttpRequest, IncreaseCardBalanceRequest {
    private String number;
    private BigDecimal value;
}
