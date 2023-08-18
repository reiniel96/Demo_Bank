package com.mindhubbrothers.Mindhub.Brothers.Bank.dto;

import com.mindhubbrothers.Mindhub.Brothers.Bank.Enums.CardColor;
import com.mindhubbrothers.Mindhub.Brothers.Bank.Enums.CardType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Card;

import java.time.LocalDate;

public class CardDTO {

    private Long id;
    private CardType type;
    private CardColor color;
    private String number;
    private Integer cvv;
    private LocalDate fromDate;
    private LocalDate thruDate;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.type = card.getType();
        this.color = card.getColor();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.fromDate = card.getFromDate();
        this.thruDate = card.getThruDate();
    }

    public Long getId() {
        return id;
    }



    public CardType getType() {
        return type;
    }


    public CardColor getColor() {
        return color;
    }


    public String getNumber() {
        return number;
    }


    public Integer getCvv() {
        return cvv;
    }


    public LocalDate getFromDate() {
        return fromDate;
    }



    public LocalDate getThruDate() {
        return thruDate;
    }

}
