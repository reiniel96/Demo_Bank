package com.mindhubbrothers.Mindhub.Brothers.Bank.repositories;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// Se solicit dates con methods http y se recipe con XML o Json
@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Long> {

    public Client findByEmail(String email);
}
