package com.mindhubbrothers.Mindhub.Brothers.Bank.repositories;


import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

}
