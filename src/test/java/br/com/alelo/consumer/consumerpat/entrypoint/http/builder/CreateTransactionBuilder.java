package br.com.alelo.consumer.consumerpat.entrypoint.http.builder;

import br.com.alelo.consumer.consumerpat.entrypoint.http.request.CreateTransactionHttpRequest;

import java.math.BigDecimal;

public final class CreateTransactionBuilder {

    public static CreateTransactionHttpRequest fullTransactionDTO() {
        CreateTransactionHttpRequest result = new CreateTransactionHttpRequest();
        result.setEstablishmentType(1);
        result.setEstablishmentName("establishmentName");
        result.setNumber("#1");
        result.setProductDescription("productDescription");
        result.setValue(BigDecimal.ONE);
        return result;
    }
}
