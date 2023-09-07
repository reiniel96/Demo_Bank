package com.mindhubbrothers.Mindhub.Brothers.Bank.controllers;


import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.CardDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardColor;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.CardRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.ClientRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CardService cardService;
    @RequestMapping("/cards")
    public List<CardDTO> getAll(){
        return cardService.getAllCardsDto();
    }
    @RequestMapping("/cards/{id}")
    public CardDTO getById(@PathVariable Long id){
        return cardService.getById(id);
    }


    @RequestMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(@RequestParam  CardType cardType, @RequestParam CardColor cardColor, Authentication authentication) {

        Client AuthClient = clientRepository.findByEmail(authentication.getName());


        if (cardRepository.findByOwner(AuthClient).stream()
                .anyMatch(card -> card.getType().equals(cardType) && card.getColor().equals(cardColor))) {
            return new ResponseEntity<>("Already have a "+cardType+" card "+cardColor+".", HttpStatus.FORBIDDEN);
        }

        cardService.createCard(cardType, cardColor, AuthClient);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}