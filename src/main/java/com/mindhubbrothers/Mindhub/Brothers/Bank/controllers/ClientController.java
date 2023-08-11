package com.mindhubbrothers.Mindhub.Brothers.Bank.controllers;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.ClientDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;



    @RequestMapping("/clients")
    public List<ClientDTO>  getClients(){

        List<Client> listclient =   clientRepository.findAll();

        List<ClientDTO> clientDTOList = listclient.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());

        return clientDTOList;
    }

    @RequestMapping("/clients2")
    public List<ClientDTO> getPersons2(){
        return clientRepository.findAll().stream().map( client -> new ClientDTO(client)).collect(Collectors.toList());
    }
    @RequestMapping("/clients/{valor}")
    public ClientDTO getClient(@PathVariable Long valor){
        return new ClientDTO (clientRepository.findById(valor).orElse(null));
    }
}
