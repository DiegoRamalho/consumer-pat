package br.com.alelo.consumer.consumerpat.gateway.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "extract")
public class ExtractJpa {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="extract_seq")
    @SequenceGenerator(name = "extract_seq", allocationSize = 1)
    private Long id;
    private String establishmentName;
    private String productDescription;
    private Date dateBuy;
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id", nullable = false,
            foreignKey = @ForeignKey(name = "extract_fk_01"))
    private CardJpa card;
}
