package br.com.alelo.consumer.consumerpat.core.util;

import br.com.alelo.consumer.consumerpat.core.entity.Card;

public final class CardValidation {

    public static void validate(Card card) {
        shouldNotBeNull(card);
        shouldHaveNumber(card);
        shouldHaveBalance(card);
    }

    public static void shouldNotBeNull(Card card) {
        ObjectUtils.shouldNotBeNull(card, Constants.CARD);
    }

    public static void shouldHaveNumber(Card card) {
        shouldHaveNumber(card.getNumber());
    }

    public static void shouldHaveNumber(String number) {
        ObjectUtils.shouldNotBeNull(number, Constants.CARD_NUMBER);
    }

    public static void shouldHaveBalance(Card card) {
        ObjectUtils.shouldNotBeNull(card.getBalance(), Constants.CARD_BALANCE);
    }

}
