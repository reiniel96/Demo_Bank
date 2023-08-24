package com.mindhubbrothers.Mindhub.Brothers.Bank.dto;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;

import java.util.List;

import static java.util.stream.Collectors.toList;


public class ClientDTO  {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private List<AccountDTO> accounts;
    private List<ClientLoanDTO> loans;
    private List<CardDTO> cards;
    public ClientDTO(Client client) {

        this.id = client.getId();

        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accounts= client.getAccounts().stream()
                .map(com.mindhubbrothers.Mindhub.Brothers.Bank.dto.AccountDTO::new)
                .collect(toList());
        this.loans = client.getClientLoans().stream()
                .map(ClientLoanDTO::new)
                .collect(toList());
        this.cards = client.getCards().stream()
                .map(CardDTO::new)
                .collect(toList());
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }
    public List<ClientLoanDTO> getLoans() {
        return loans;
    }
    public List<CardDTO> getCards() {
        return cards;
    }
}
