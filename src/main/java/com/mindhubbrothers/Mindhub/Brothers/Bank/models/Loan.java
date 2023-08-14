package com.mindhubbrothers.Mindhub.Brothers.Bank.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

@Entity
public class Loan {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private static Long id;
    private static String name;
    private static double maxAmount;
    private static double payments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "loan")
    private List<ClientLoan> clientLoans = new ArrayList<>();

    public Loan() {
    }

    public Loan(Long id, String name, double maxAmount, double payments) {
        this.id = id;
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
    }

    public Loan(String name, double maxAmount, double payments) {

    }

    public static Long getId() {
        return id;
    }


    public static String getName() {
        return name;
    }

    public static double getmaxAmount() {
        return 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public static double getPayments() {
        return payments;
    }

    public void setPayments(double payments) {
        this.payments = payments;
    }

    public DoubleStream stream() {
        return null;
    }
}
