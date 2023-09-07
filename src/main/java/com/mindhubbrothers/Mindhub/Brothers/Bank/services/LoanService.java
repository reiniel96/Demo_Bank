package com.mindhubbrothers.Mindhub.Brothers.Bank.services;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.ClientLoanRecord;
import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.LoanDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface LoanService {

    List<LoanDTO> getAll();

    void createLoan(
            ClientLoanRecord clientLoanRecord,
            Authentication authentication);
}
