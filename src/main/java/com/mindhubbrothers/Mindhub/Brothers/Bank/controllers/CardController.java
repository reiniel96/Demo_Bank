package com.mindhubbrothers.Mindhub.Brothers.Bank.controllers;


import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardColor;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.CardDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Card;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.AccountRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.CardRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.mindhubbrothers.Mindhub.Brothers.Bank.utils.utils.genCvv;
import static com.mindhubbrothers.Mindhub.Brothers.Bank.utils.utils.genRandomCardNumber;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping("/cards")
    public List<CardDTO> getAll(){
        return cardRepository.findAll().stream()
                .map(card -> new CardDTO(card))
                .collect(toList());
    }
    @RequestMapping("/cards/{id}")
    public CardDTO getById(@PathVariable Long id){
        return new CardDTO(cardRepository.findById(id).orElse(null));
    }

    @RequestMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(@RequestParam  CardType cardType, @RequestParam CardColor cardColor, Authentication authentication) {

        Client AuthClient = clientRepository.findByEmail(authentication.getName());


        if (cardRepository.findByOwner(AuthClient).stream()
                .anyMatch(card -> card.getType().equals(cardType) && card.getColor().equals(cardColor))) {
            return new ResponseEntity<>("Already have a "+cardType+" card "+cardColor+".", HttpStatus.FORBIDDEN);
        }

        Card newCard = new Card(cardType, cardColor, LocalDate.now());

        do {
            newCard.setNumber(genRandomCardNumber());
        } while (cardRepository.findByNumber(newCard.getNumber()) != null);

        newCard.setCvv(genCvv(newCard.getNumber()));

        AuthClient.addCard(newCard);
        cardRepository.save(newCard);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}