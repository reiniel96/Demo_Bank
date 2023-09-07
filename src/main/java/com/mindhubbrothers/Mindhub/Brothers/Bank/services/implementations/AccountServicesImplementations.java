package com.mindhubbrothers.Mindhub.Brothers.Bank.services.implementations;


import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.AccountDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.AccountRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.services.AccountService;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class AccountServicesImplementations implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<AccountDTO> getAll() {
        return accountRepository.findAll().stream()
                .map(account -> new AccountDTO(account))
                .collect(toList());
    }

    @Override
    public AccountDTO getById(Long id, Authentication authentication) {
        return new AccountDTO(accountRepository.findById(id).orElse(null));
    }

    @Override
    public List<AccountDTO> getCurrentAccounts(Authentication authentication) {
        return accountRepository.findAll().stream()
                .filter(account -> account.getOwner().getEmail().equals(authentication.getName()))
                .map(account -> new AccountDTO(account))
                .collect(toList());
    }

    @Override
    public void createAccount(Authentication authentication) {
        Account newAccount= new Account("VIN"+String.format("%03d",accountRepository.count()+1) , 0.0, LocalDate.now());
        Client AuthClient = clientRepository.findByEmail(authentication.getName());
        AuthClient.addAccount(newAccount);
        accountRepository.save(newAccount);
    }
}
