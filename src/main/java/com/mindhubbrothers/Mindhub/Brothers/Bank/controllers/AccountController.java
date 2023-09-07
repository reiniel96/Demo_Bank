package com.mindhubbrothers.Mindhub.Brothers.Bank.controllers;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.AccountDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.AccountRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.ClientRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/accounts")
    public List<AccountDTO> getAll(){
        return accountService.getAll();
    }
    @RequestMapping("/accounts/{id}")
    public AccountDTO getById(@PathVariable Long id, Authentication authentication){

        if(!accountRepository.findById(id).get().getOwner().getEmail().equals(authentication.getName())){
            return null;
        }

        return accountService.getById(id, authentication);
    }

    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.GET)

    public List<AccountDTO> getCurrentAccounts( Authentication authentication) {

        return accountService.getCurrentAccounts(authentication);
    }

    @RequestMapping(path = "/clients/current/accounts", method = RequestMethod.POST)

    public ResponseEntity<Object> createAccount( Authentication authentication) {


        if (clientRepository.findByEmail(authentication.getName()).getAccounts().stream().count()==3) {

            return new ResponseEntity<>("Already have 3 accounts", HttpStatus.FORBIDDEN);

        }

        accountService.createAccount(authentication);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
