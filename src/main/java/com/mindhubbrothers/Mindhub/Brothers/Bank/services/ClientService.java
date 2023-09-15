package com.mindhubbrothers.Mindhub.Brothers.Bank.services;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.ClientDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.ClientLoan;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClientsDTO();
    ClientDTO getClientDTO (long id);
    ClientDTO getClientDTOByEmailCurrent(String email);
    Client findById (long id);
    Client findByEmail (String email);
    void saveClient(Client client);

    ClientDTO getClientsDTOById(Long id);

    ClientDTO getCurrentClient(Authentication authentication);
}

