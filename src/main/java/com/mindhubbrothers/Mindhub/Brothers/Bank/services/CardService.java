package com.mindhubbrothers.Mindhub.Brothers.Bank.services;

import com.mindhubbrothers.Mindhub.Brothers.Bank.dto.CardDTO;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardColor;
import com.mindhubbrothers.Mindhub.Brothers.Bank.enums.CardType;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Card;
import com.mindhubbrothers.Mindhub.Brothers.Bank.models.Client;

import java.util.List;

public interface CardService {
    List<CardDTO> getAllCardsDto();

    CardDTO getById(Long id);

    void createCard(CardType cardType, CardColor cardColor, Client AuthClient);

    List<CardDTO> getCardsDTO();
    CardDTO getCardDTO (long id);
    Card findById (long id);
    void saveCard(Card card);

}
