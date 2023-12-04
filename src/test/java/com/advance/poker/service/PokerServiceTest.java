package com.advance.poker.service;

import com.advance.poker.model.Card;
import com.advance.poker.model.Deck;
import com.advance.poker.model.Rank;
import com.advance.poker.model.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PokerServiceTest {

    @Mock
    private Deck deck;

    @InjectMocks
    private PokerService pokerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDealDeck() {
        List<Card> expectedDeck = Arrays.asList(new Card(), new Card(), new Card());
        when(deck.getCards()).thenReturn(expectedDeck);

        List<Card> result = pokerService.dealDeck();

        assertEquals(expectedDeck, result);
        verify(deck, times(1)).getCards();
    }

    @Test
    void testDealHand() {
        List<Card> expectedHand = Arrays.asList(new Card(), new Card());
        int numberOfCards = 2;
        when(deck.dealHand(numberOfCards)).thenReturn(expectedHand);

        List<Card> result = pokerService.dealHand(numberOfCards);

        assertEquals(expectedHand, result);
        verify(deck, times(1)).dealHand(numberOfCards);
    }

    @Test
    void testInitializeDeck() {
        // Create cards with specific Suit and Rank
        Card defaultCard = new Card(Suit.SPADES, Rank.TWO);
        Card customCard = new Card(Suit.HEARTS, Rank.ACE);

        pokerService.initializeDeck();

        verify(deck, times(1)).initializeDeck();
    }

}
