package com.mindhubbrothers.Mindhub.Brothers.Bank.dto;

import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;

    private List<AccountDTO> accounts;

    public ClientDTO (Client client){
        id = client.getId();
        name = client.getName();
        lastname = client.getLastname();
        email = client.getEmail();
        accounts = client.getAccounts().stream().map(element -> new AccountDTO(element)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public Object getEmail() {
        return email;

    }
    public Set<AccountDTO> getAccounts(){
        return (Set<AccountDTO>) accounts;
        }

}
