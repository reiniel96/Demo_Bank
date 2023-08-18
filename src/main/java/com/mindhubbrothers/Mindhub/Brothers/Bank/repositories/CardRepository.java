package com.mindhubbrothers.Mindhub.Brothers.Bank.repositories;



import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.Optional;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {

}
