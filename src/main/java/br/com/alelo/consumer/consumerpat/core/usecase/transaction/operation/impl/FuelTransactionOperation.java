package br.com.alelo.consumer.consumerpat.core.usecase.transaction.operation.impl;

import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;
import br.com.alelo.consumer.consumerpat.core.usecase.transaction.operation.TransactionOperation;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class FuelTransactionOperation implements TransactionOperation {

    @Override
    public BigDecimal calculate(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(1.35)).setScale(2, RoundingMode.FLOOR);
    }

    @Override
    public CardType getCardType() {
        return CardType.FUEL_CARD;
    }
}
