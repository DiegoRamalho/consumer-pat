package br.com.alelo.consumer.consumerpat.gateway.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
@Table(name = "consumer")
public class ConsumerJpa {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="consumer_seq")
    @SequenceGenerator(name = "consumer_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String documentNumber;
    private LocalDate birthDate;

    //contacts
    private String mobilePhoneNumber;
    private String residencePhoneNumber;
    private String phoneNumber;
    private String email;

    //Address
    private String street;
    private String number;
    private String city;
    private String country;
    private String postalCode;

    //cards
    @OneToMany(mappedBy = "consumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CardJpa> cards;
}
