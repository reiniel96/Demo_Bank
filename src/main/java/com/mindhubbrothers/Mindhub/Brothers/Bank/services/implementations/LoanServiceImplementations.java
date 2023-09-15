package com.mindhubbrothers.Mindhub.Brothers.Bank.services.implementations;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.ClientLoanRecord;
import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.LoanDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.LoanType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Loan;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.TransactionType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.*;
import com.mindhubbrothers.Mindhub.Brothers.Bank.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
@Service
public class LoanServiceImplementations implements LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<LoanDTO> getAll() {
        return null;
    }

    @Override
    public void createLoan(ClientLoanRecord clientLoanRecord, Authentication authentication) {

    }

    @Override
    public List<LoanDTO> getLoanApplicationDTO() {
        return loanRepository.findAll()
                .stream().map(loan -> new LoanDTO(loan))
                .collect(Collectors.toList());
    }
    @Override
    public void saveLoan(Loan Loan) {
        loanRepository.save(Loan);
    }
    @Override
    public Loan findByName(LoanType name) {
        return loanRepository.findByName(name);
    }
}