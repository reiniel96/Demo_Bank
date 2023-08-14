package com.mindhubbrothers.Mindhub.Brothers.Bank.dto;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account;

public class AccountDTO {
    private long id;
    private String number;
    private String balance;

    public AccountDTO(Account account){
        id = account.getId();
        number = account.getNumber();
        balance = String.valueOf(account.getBalance());
    }



    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getBalance() {
        return balance;
    }
}
