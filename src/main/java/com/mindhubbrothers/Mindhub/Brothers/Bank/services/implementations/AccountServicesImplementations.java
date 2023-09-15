package com.mindhubbrothers.Mindhub.Brothers.Bank.services.implementations;


import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.AccountDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.AccountRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.services.AccountService;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServicesImplementations implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDTO> getCurrentAccounts(Authentication authentication) {
        return null;
    }

    @Override
    public void createAccount(Authentication authentication) {

    }

    @Override
    public List<AccountDTO> getAccountsDTO() {
        return accountRepository.findAll()
                .stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toList());
    }
    @Override
    public AccountDTO getAccountDTOCurrent(long id) {
        return new AccountDTO(accountRepository.findById(id).orElse(null));
    }
    @Override
    public List<Account> accounts(Client client) {
        return accountRepository.findByClient(client);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<Account> findByClientList(Client client) {
        return accountRepository.findByClient(client);
    }

    @Override
    public Account findByNumber(String number) {
        return accountRepository.findByNumber(number );
    }

}