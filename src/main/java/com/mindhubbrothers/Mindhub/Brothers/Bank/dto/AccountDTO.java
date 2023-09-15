package com.mindhubbrothers.Mindhub.Brothers.Bank.dto;


import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


import static java.util.stream.Collectors.toList;

public class AccountDTO {
    private Long id;
    private String number;
    private LocalDate creationDate;
    private Double balance;
    private List<TransactionDTO> transactions;

    public AccountDTO(Account account) {
        id = account.getId();
        number = account.getNumber();
        creationDate = account.getCreationDate();
        balance = account.getBalance();
        transactions = account.getTransactions().stream().map( transaction -> new TransactionDTO(transaction)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }
}
