package com.mindhubbrothers.Mindhub.Brothers.Bank.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private long id;
    private String number;
    private  String creationDate;
    private String Balance;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public Account(String number, LocalDate now, String Balance) {
    }

    public Account(long id, String number, String creationDate, String balance) {
        this.id = id;
        this.number = number;
        this.Balance = balance;
        this.creationDate =creationDate;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
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

