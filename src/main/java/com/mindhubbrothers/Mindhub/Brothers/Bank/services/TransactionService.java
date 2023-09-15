package com.mindhubbrothers.Mindhub.Brothers.Bank.services;


import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.TransactionDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> getAll();

    TransactionDTO getById(Long id);

    void makeTransaction(Double amount, String description, String accountFromNumber, String toAccountNumber, Authentication authentication);

    void saveTransaction(Transaction transaction);
    }

