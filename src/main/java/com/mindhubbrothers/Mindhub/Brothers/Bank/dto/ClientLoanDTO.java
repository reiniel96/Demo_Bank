package com.mindhubbrothers.Mindhub.Brothers.Bank.dto;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Loan;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientLoanDTO {
    private Long id;

    private double payments;
    private double amount;

    private List<ClientDTO> clients;
    private List<LoanDTO> loans;

    public ClientLoanDTO (ClientLoan clientLoan){
        id = clientLoan.getId();
        payments= clientLoan.getPayments();
        amount = clientLoan.getAmount();

    }

    public Long getId() {
        return id;
    }

    public double getPayments() {
        return payments;
    }

    public double getAmount() {
        return amount;
    }
    public Set<ClientDTO> getClient(){return (Set<ClientDTO>) clients;
    }
    public Set<LoanDTO> getLoan(){
        return (Set<LoanDTO>) loans;
    }
}
