package com.mindhubbrothers.Mindhub.Brothers.Bank.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private long id;
    private String number;
    private String Balance;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public Account(String number, String Balance, LocalDate LocalDate) {
    }

    public Account(long id, String number, String balance, LocalDate localDate) {
        this.id = id;
        this.number = number;
        this.Balance = balance;
    }

    public Account() {

    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public void setClient(Client client) {
    }
}

