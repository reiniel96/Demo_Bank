package com.mindhubbrothers.Mindhub.Brothers.Bank.repositories;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
