package br.com.alelo.consumer.consumerpat.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Extract implements Entity<Long> {
    private Long id;
    private String establishmentName;
    private String productDescription;
    private LocalDateTime dateBuy;
    private BigDecimal value;
    private Card card;
}
