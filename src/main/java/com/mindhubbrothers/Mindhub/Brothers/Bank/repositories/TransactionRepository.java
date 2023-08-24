package com.mindhubbrothers.Mindhub.Brothers.Bank.repositories;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
