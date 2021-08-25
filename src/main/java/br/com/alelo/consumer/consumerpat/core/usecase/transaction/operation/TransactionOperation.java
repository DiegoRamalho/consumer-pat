package br.com.alelo.consumer.consumerpat.core.usecase.transaction.operation;


import br.com.alelo.consumer.consumerpat.core.entity.enumerated.CardType;

import java.math.BigDecimal;

public interface TransactionOperation {
    BigDecimal calculate(BigDecimal value);

    CardType getCardType();
}
