package com.mindhubbrothers.Mindhub.Brothers.Bank.controllers;


import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.CardDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardRepository cardRepository;
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

}