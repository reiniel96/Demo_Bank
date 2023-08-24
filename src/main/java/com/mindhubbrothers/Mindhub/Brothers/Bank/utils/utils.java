package com.mindhubbrothers.Mindhub.Brothers.Bank.utils;

import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.AccountRepository;

public class utils {




    public static String genAccountId(AccountRepository accountRepository) {
        return "VIN"+String.format("%03d",accountRepository.count()+1 );
    }

}
