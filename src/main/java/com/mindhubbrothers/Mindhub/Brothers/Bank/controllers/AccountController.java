package com.mindhubbrothers.Mindhub.Brothers.Bank.controllers;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.AccountDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.AccountRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/accounts")
    public List<AccountDTO> getAll(){
        return accountRepository.findAll().stream()
                .map(account -> new AccountDTO(account))
                .collect(toList());
    }
    @RequestMapping("/accounts/{id}")
    public AccountDTO getById(@PathVariable Long id, Authentication authentication){
        if(!accountRepository.findById(id).get().getOwner().getEmail().equals(authentication.getName())){
            return null;
        }
        return new AccountDTO(accountRepository.findById(id).orElse(null));
    }

    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.GET)

    public List<AccountDTO> getCurrentAccounts( Authentication authentication) {

        return accountRepository.findAll().stream()
                .filter(account -> account.getOwner().getEmail().equals(authentication.getName()))
                .map(account -> new AccountDTO(account))
                .collect(toList());
    }

    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)

    public ResponseEntity<Object> createAccount(Authentication authentication) {


        if (clientRepository.findByEmail(authentication.getName()).getAccounts().stream().count()==3) {

            return new ResponseEntity<>("Already have 3 accounts", HttpStatus.FORBIDDEN);

        }

        Account newAccount= new Account("VIN"+String.format("%03d",accountRepository.count()+1) , 0.0, LocalDate.now());
        Client AuthClient = clientRepository.findByEmail(authentication.getName());
        AuthClient.addAccount(newAccount);
        accountRepository.save(newAccount);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
