package com.mindhubbrothers.Mindhub.Brothers.Bank.services.implementations;


import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.CardDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardColor;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Card;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.AccountRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.repositories.CardRepository;
import com.mindhubbrothers.Mindhub.Brothers.Bank.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.mindhubbrothers.Mindhub.Brothers.Bank.utils.utils.genCvv;
import static com.mindhubbrothers.Mindhub.Brothers.Bank.utils.utils.genRandomCardNumber;
import static java.util.stream.Collectors.toList;
@Service
public class CardServicesImplementations implements CardService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CardRepository cardRepository;


    @Override
    public List<CardDTO> getAllCardsDto() {
        return cardRepository.findAll().stream()
                .map(card -> new CardDTO(card))
                .collect(toList());
    }

    @Override
    public CardDTO getById(Long id) {
        return new CardDTO(cardRepository.findById(id).orElse(null));
    }


    @Override
    public void createCard(CardType cardType, CardColor cardColor, Client AuthClient) {
        Card newCard = new Card(cardType, cardColor, LocalDate.now());

        do {
            newCard.setNumber(genRandomCardNumber());
        } while (cardRepository.findByNumber(newCard.getNumber()) != null);

        newCard.setCvv(genCvv(newCard.getNumber()));

        AuthClient.addCard(newCard);
        cardRepository.save(newCard);

    }


    @Override
    public List<CardDTO> getCardsDTO() {
        return null;
    }

    @Override
    public CardDTO getCardDTO(long id) {
        return null;
    }

    @Override
    public Card findById(long id) {
        return null;
    }

    @Override
    public void saveCard(Card card) {

    }
}
