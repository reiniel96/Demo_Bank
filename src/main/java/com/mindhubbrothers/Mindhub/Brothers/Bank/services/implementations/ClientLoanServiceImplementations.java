package com.mindhubbrothers.Mindhub.Brothers.Bank.services.implementations;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.ClientLoanRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientLoanServiceImplementations implements ClientLoanService {
    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @Override
    public void saveClientLoan(ClientLoan clientLoan) {
        clientLoanRepository.save(clientLoan);
    }
}
