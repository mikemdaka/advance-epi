package com.advance.poker.model;

import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
