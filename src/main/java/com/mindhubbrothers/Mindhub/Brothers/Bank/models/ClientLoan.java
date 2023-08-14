package com.mindhubbrothers.Mindhub.Brothers.Bank.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private double payments;
    private double amount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Loan loan;

    public ClientLoan(){

    }

    public ClientLoan(Long id, double payments, double amount, Client client, Loan loan) {
        this.id = id;
        this.payments = payments;
        this.amount = amount;
        this.client = client;
        this.loan = loan;
    }

    public Long getId() {
        return id;
    }

    public double getPayments() {
        return payments;
    }

    public void setPayments(double payments) {
        this.payments = payments;
    }

    public double getAmount() {
        return amount;
    }


    public Client getClient() {
        return client;
    }


    public Loan getLoan() {
        return loan;
    }


}
