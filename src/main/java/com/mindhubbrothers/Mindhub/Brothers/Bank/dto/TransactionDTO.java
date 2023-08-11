package com.mindhubbrothers.Mindhub.Brothers.Bank.dto;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction;

public class TransactionDTO {

    private Long id;
    private Enum type;
    private String amount;
    private String description;

    enum Level {
        CREDIT,
        DEBIT
    }

    public TransactionDTO(TransactionDTO transaction) {
        id = transaction.getId();
        type = transaction.getType();
        amount = transaction.getAmount();
        description = transaction.getDescription();
    }

    public Long getId() {
        return id;
    }

    public Enum getType() {
        return type;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
