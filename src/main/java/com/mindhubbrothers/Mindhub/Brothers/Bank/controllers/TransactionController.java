package com.mindhubbrothers.Mindhub.Brothers.Bank.controllers;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.TransactionDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.AccountRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.TransactionType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping(value = "/transactions",method = RequestMethod.GET)
    public List<TransactionDTO> getAll(){
        return transactionRepository.findAll().stream()
                .map(transaction -> new TransactionDTO(transaction))
                .collect(toList());
    }
    @RequestMapping(value = "/transactions/{id}",method = RequestMethod.GET)
    public TransactionDTO getById(@PathVariable Long id){
        return new TransactionDTO(transactionRepository.findById(id).orElse(null));
    }
    @Transactional
    @RequestMapping(value = "/transactions",method = RequestMethod.POST)
    public ResponseEntity makeTransaction(
            @RequestParam Double amount, @RequestParam String description ,
            @RequestParam(value = "fromAccountNumber") String accountFromNumber, @RequestParam String toAccountNumber,
            Authentication authentication){


        //Verificamos que los parámetros no esten vacios
        if (amount == null || description == null || accountFromNumber == null || toAccountNumber == null) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        //Verificamos que el número de cuenta de origen y destino no la misma
        if (accountFromNumber.equals(toAccountNumber)) {
            return new ResponseEntity<>("The origin and destination accounts are the same", HttpStatus.FORBIDDEN);
        }
        //Verificamos que el número de cuenta de origen exista
        if (accountRepository.findByNumber(accountFromNumber) == null) {
            return new ResponseEntity<>("The origin account does not exist", HttpStatus.FORBIDDEN);
        }
        //Verificamos que el número de cuenta de destino exista
        if (accountRepository.findByNumber(toAccountNumber) == null) {
            return new ResponseEntity<>("The destination account does not exist", HttpStatus.FORBIDDEN);
        }
        //Verificamos que el número de cuenta de origen pertenezca al cliente actual
        if (!accountRepository.findByNumber(accountFromNumber).getOwner().getEmail().equals(authentication.getName())) {
            return new ResponseEntity<>("The origin account does not belong to the logged in client", HttpStatus.FORBIDDEN);
        }
        //Vericiamos que halla fondos en la cuenta
        if (accountRepository.findByNumber(accountFromNumber).getBalance() < amount) {
            return new ResponseEntity<>("Insufficient funds", HttpStatus.FORBIDDEN);
        }
        //Verificamos que el monto sea mayor a 0 pesos
        if (amount <= 0) {
            return new ResponseEntity<>("The amount must be greater than 0", HttpStatus.FORBIDDEN);
        }

        //Creamos la transaccion
        Transaction transactionDebit = new Transaction(TransactionType.DEBIT,amount, description);
        Transaction transactionCredit = new Transaction(TransactionType.CREDIT,amount, description);

        //Actualizamos el balance de la cuenta origen
        accountRepository.findByNumber(accountFromNumber).setBalance(accountRepository.findByNumber(accountFromNumber).getBalance() - amount);
        accountRepository.findByNumber(accountFromNumber).addTransaction(transactionDebit);
        //Actualizamos el balance de la cuenta destino
        accountRepository.findByNumber(toAccountNumber).setBalance(accountRepository.findByNumber(toAccountNumber).getBalance() + amount);
        accountRepository.findByNumber(toAccountNumber).addTransaction(transactionCredit);
        //Guardamos la transaccion
        transactionRepository.save(transactionDebit);
        transactionRepository.save(transactionCredit);
        //Guardamos los cambios en las cuentas
        accountRepository.save(accountRepository.findByNumber(accountFromNumber));
        accountRepository.save(accountRepository.findByNumber(toAccountNumber));

        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}






