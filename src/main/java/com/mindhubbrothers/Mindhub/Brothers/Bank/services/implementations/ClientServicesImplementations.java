package com.mindhubbrothers.Mindhub.Brothers.Bank.services.implementations;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.ClientDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Account;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.AccountRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.ClientRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ClientServicesImplementations implements ClientService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<ClientDTO> getClientsDTO() {
        return clientRepository.findAll().stream()
                .map(client -> new ClientDTO(client))
                .collect(toList());
    }

    @Override
    public ClientDTO getClientDTO(long id) {
        return null;
    }

    @Override
    public ClientDTO getClientDTOByEmailCurrent(String email) {
        return null;
    }

    @Override
    public Client findById(long id) {
        return null;
    }

    @Override
    public Client findByEmail(String email) {
        return null;
    }

    @Override
    public void saveClient(Client newClient) {
        Account account= new Account("VIN"+String.format("%03d",accountRepository.count()+1) , 0.0, LocalDate.now());
        newClient.addAccount(account);
        clientRepository.save(newClient);
        accountRepository.save(account);
    }

    @Override
    public ClientDTO getClientsDTOById(Long id) {
        return new ClientDTO(clientRepository.findById(id).orElse(null));
    }
    @Override
    public ClientDTO getCurrentClient(Authentication authentication) {
        return new ClientDTO(clientRepository.findByEmail(authentication.getName()));
    }

}
