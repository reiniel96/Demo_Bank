package com.mindhubbrothers.Mindhub.Brothers.Bank.dto;

import com.mindhubbrothers.Mindhub.Brothers.Bank.Enums.TransactionType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction;

import java.time.LocalDate;


public class TransactionDTO {

    private Long id;
    private TransactionType type;
    private Double amount;
    private LocalDate date;
    private String description;
    public TransactionDTO(){};

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.date = transaction.getDate();
        this.description = transaction.getDescription();
    }

    public Long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
