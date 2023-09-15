package com.mindhubbrothers.Mindhub.Brothers.Bank.services;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.AccountDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import org.springframework.security.core.Authentication;

import java.util.List;

        public interface AccountService {
                List<AccountDTO> getCurrentAccounts(Authentication authentication);

                void createAccount(Authentication authentication);

                List<AccountDTO> getAccountsDTO();
                AccountDTO getAccountDTOCurrent (long id);
                List<Account> accounts (Client client);
                void saveAccount(Account account);
                List<Account> findByClientList (Client client);
                Account findByNumber(String number);

        }

