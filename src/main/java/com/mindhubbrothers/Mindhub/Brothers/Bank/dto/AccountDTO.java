package com.mindhubbrothers.Mindhub.Brothers.Bank.dto;


import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account;

import java.time.LocalDate;
import java.util.List;


import static java.util.stream.Collectors.toList;

public class AccountDTO {
    private Long id;
    private String number;
    private Double balance;
    private LocalDate date;
    private List<TransactionDTO> transactions;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.balance = account.getBalance();
        this.date = account.getDate();
        this.transactions= account.getTransactions().stream()
                .map(TransactionDTO::new)
                .collect(toList());
    }

    public Long getId(){
        return id;
    }
    public String getNumber() {
        return number;
    }

    public Double getBalance() {
        return balance;
    }

    public LocalDate getDate(){
        return date;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }
}
