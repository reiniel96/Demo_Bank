package com.mindhubbrothers.Mindhub.Brothers.Bank.services.implementations;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.TransactionDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.TransactionType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.AccountRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.TransactionRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;
@Service
public class TransactionServicesImplementations implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<TransactionDTO> getAll() {
        return transactionRepository.findAll().stream()
                .map(transaction -> new TransactionDTO(transaction))
                .collect(toList());
    }

    @Override
    public TransactionDTO getById(Long id) {
        return new TransactionDTO(transactionRepository.findById(id).orElse(null));
    }

    @Override
    public void makeTransaction(Double amount, String description, String accountFromNumber, String toAccountNumber, Authentication authentication) {

        //creamos la transaccion
        Transaction transactionDebit = new Transaction(TransactionType.DEBIT,amount, description);
        Transaction transactionCredit = new Transaction(TransactionType.CREDIT,amount, description);

        //actualizamos el balance de la cuenta origen
        accountRepository.findByNumber(accountFromNumber).setBalance(accountRepository.findByNumber(accountFromNumber).getBalance() - amount);
        accountRepository.findByNumber(accountFromNumber).addTransaction(transactionDebit);
        //actualizamos el balance de la cuenta destino
        accountRepository.findByNumber(toAccountNumber).setBalance(accountRepository.findByNumber(toAccountNumber).getBalance() + amount);
        accountRepository.findByNumber(toAccountNumber).addTransaction(transactionCredit);
        //guardamos la transaccion
        transactionRepository.save(transactionDebit);
        transactionRepository.save(transactionCredit);
        //guardamos los cambios en las cuentas
        accountRepository.save(accountRepository.findByNumber(accountFromNumber));
        accountRepository.save(accountRepository.findByNumber(toAccountNumber));
    }
}
