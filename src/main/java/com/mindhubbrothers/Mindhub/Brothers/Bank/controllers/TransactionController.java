package com.mindhubbrothers.Mindhub.Brothers.Bank.controllers;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.TransactionRepository;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.TransactionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;
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
}






