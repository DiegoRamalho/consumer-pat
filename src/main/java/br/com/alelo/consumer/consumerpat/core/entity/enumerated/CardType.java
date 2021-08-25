package br.com.alelo.consumer.consumerpat.core.entity.enumerated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
@ToString
public enum CardType {
    FOOD_CARD(1, "Food Card"),
    DRUGSTORE_CARD(2, "Drugstore Card"),
    FUEL_CARD(3, "Fuel Card");

    private final int code;
    private final String description;

    public static CardType fromCode(Integer value) {
        return Arrays.stream(values())
                .filter(it -> Objects.equals(it.code, value))
                .findFirst()
                .orElse(null);
    }
}
