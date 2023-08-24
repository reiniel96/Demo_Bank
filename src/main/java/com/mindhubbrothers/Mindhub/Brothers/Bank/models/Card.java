package com.mindhubbrothers.Mindhub.Brothers.Bank.models;

import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardColor;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Random;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="cardHolder_id")
    private Client cardHolder;

    private CardType type;
    private CardColor color;
    private String number;
    private Integer cvv;
    private LocalDate fromDate;
    private LocalDate thruDate;


    public Card(){};

    public Card( CardType type, CardColor color, LocalDate fromDate) {
        this.type = type;
        this.color = color;
        setNumber();
        setCvv(this.number);
        this.fromDate = fromDate;
        setThruDate(fromDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber() {
        String cardNumber = "8545";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            cardNumber += "-" + String.format("%04d", random.nextInt(10000));
        }
        this.number = cardNumber;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(String cardNumber) {
        int sum = 0;
        for (String bloque : cardNumber.split("-")) {
            int aux = 0;
            for (int i = 0; i < 3; i++) {
                aux += Character.getNumericValue(bloque.charAt(i));
            }
            sum += aux;
        }
        this.cvv= 300+(sum % 10000);
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDate thruDate) {
        this.thruDate = thruDate;
    }

    public Client getCardHolder() {

        return cardHolder;
    }

    public void addCardHolder(Client client) {
        this.cardHolder = client;
    }


    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", type=" + type +
                ", color=" + color +
                ", number=" + number +
                ", cvv=" + cvv +
                ", fromDate=" + fromDate +
                ", thruDate=" + thruDate +
                '}';

    }
}
