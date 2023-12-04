package com.advance.poker.service;

import com.advance.poker.model.Card;
import com.advance.poker.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PokerService {

    private final Deck deck;

    @Autowired
    public PokerService(Deck deck) {
        this.deck = deck;
        this.initializeDeck();
    }

    public List<Card> dealDeck() {
        return deck.getCards();
    }

    public List<Card> dealHand(int numberOfCards) {
        return deck.dealHand(numberOfCards);
    }

    public void initializeDeck() {
        deck.initializeDeck();
    }
}
