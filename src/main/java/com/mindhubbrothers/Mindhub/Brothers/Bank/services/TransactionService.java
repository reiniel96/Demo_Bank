package com.mindhubbrothers.Mindhub.Brothers.Bank.services;


import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.TransactionDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> getAll();

    TransactionDTO getById(@PathVariable Long id);

    void makeTransaction(
            @RequestParam Double amount, @RequestParam String description ,
            @RequestParam(value = "fromAccountNumber") String accountFromNumber, @RequestParam String toAccountNumber,
            Authentication authentication);



}
