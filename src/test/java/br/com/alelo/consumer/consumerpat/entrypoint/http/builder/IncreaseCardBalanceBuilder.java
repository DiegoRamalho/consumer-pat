package br.com.alelo.consumer.consumerpat.entrypoint.http.builder;

import br.com.alelo.consumer.consumerpat.entrypoint.http.request.IncreaseCardBalanceHttpRequest;

import java.math.BigDecimal;

public final class IncreaseCardBalanceBuilder {

    public static IncreaseCardBalanceHttpRequest fullIncreaseCardBalanceDTO() {
        IncreaseCardBalanceHttpRequest request = new IncreaseCardBalanceHttpRequest();
        request.setNumber("#1");
        request.setValue(BigDecimal.ONE);
        return request;
    }
}
