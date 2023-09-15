package com.mindhubbrothers.Mindhub.Brothers.Bank.repositories;


import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByClient(Client client);
    Account  findByNumber(String number);
}
