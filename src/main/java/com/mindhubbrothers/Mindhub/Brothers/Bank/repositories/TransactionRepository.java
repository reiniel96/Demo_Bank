package com.mindhubbrothers.Mindhub.Brothers.Bank.repositories;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
