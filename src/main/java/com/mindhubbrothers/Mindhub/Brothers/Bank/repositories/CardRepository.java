package com.mindhubbrothers.Mindhub.Brothers.Bank.repositories;


import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Card;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.ArrayList;

@RepositoryRestResource
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByNumber(String number);

    ArrayList<Card> findByOwner(Client authClient);
}
