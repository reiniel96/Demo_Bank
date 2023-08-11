package com.mindhubbrothers.Mindhub.Brothers.Bank.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private long id;
    private String number;
    private double balance;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;


    public Account(long id, String number, double balance, LocalDate localDate) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }

    public Account() {

    }

    public Account(String number, String number1, LocalDate now) {
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


    public double getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        balance = balance;
    }

    public void setClient(Client client) {
    }
}

