package com.mindhubbrothers.Mindhub.Brothers.Bank.controllers;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.ClientDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.ClientRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;
    @RequestMapping("/clients")
    public List<ClientDTO> getAll(){
        return clientService.getClientsDTO();
    }

    @RequestMapping(path = "/clients", method = RequestMethod.POST)

    public ResponseEntity<Object> saveClient(

            @RequestParam String firstName, @RequestParam String lastName,

            @RequestParam String email, @RequestParam String password) {

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }

        if (clientRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
        }

        clientService.saveClient(new Client(firstName, lastName, email, passwordEncoder.encode(password)));

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getById(@PathVariable Long id){
        return clientService.getClientsDTOById(id);
    }

    @RequestMapping("/clients/current")
    public ClientDTO getCurrentClient(Authentication authentication) {
        return clientService.getCurrentClient(authentication);
    }


}
