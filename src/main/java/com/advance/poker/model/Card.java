package com.advance.poker.model;

import lombok.Getter;
import lombok.Setter;

public class Card {
    @Getter
    @Setter
    private Suit suit;
    @Getter
    @Setter
    private Rank rank;

    public Card(){

    }

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return rank.getSymbol() + suit.getSymbol();
    }
}
