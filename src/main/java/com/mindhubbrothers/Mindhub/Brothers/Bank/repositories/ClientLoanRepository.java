package com.mindhubbrothers.Mindhub.Brothers.Bank.repositories;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientLoanRepository extends JpaRepository<LoanRepository,Long> {
}
