package com.mindhubbrothers.Mindhub.Brothers.Bank.services;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.AccountDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAll();

    AccountDTO getById(Long id, Authentication authentication);

    List<AccountDTO> getCurrentAccounts( Authentication authentication);


    void createAccount(Authentication authentication);
}
