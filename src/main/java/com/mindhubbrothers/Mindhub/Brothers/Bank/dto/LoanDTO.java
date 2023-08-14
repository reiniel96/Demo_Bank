package com.mindhubbrothers.Mindhub.Brothers.Bank.dto;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Loan;

public class LoanDTO {
    private Long id;
    private String name;
    private double maxAmount;
    private double payments;

    public LoanDTO (Loan loan) {
        id= Loan.getId();
        name = Loan.getName();
        maxAmount = Loan.getMaxAmount();
        payments= Loan.getPayments();

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public double getPayments() {
        return payments;
    }
}
