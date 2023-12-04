package com.advance.poker.application;

import com.advance.poker.model.Card;
import com.advance.poker.model.Rank;
import com.advance.poker.model.Suit;
import com.advance.poker.service.PokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PokerGame {

    private final PokerService pokerService;

    @Autowired
    public PokerGame(PokerService pokerService) {
        this.pokerService = pokerService;
        this.pokerService.initializeDeck();
    }

    public String play(int handSize) {

        // Deal a hand of cards with the specified hand size
        List<Card> hand = pokerService.dealHand(handSize);

        // Evaluate the hand
        return evaluateHand(hand);
    }

    private String evaluateHand(List<Card> hand) {
        String output = "Shuffling... Shuffling... Shuffling...\nYour Hand: "+hand+"\nYou Have : ";

        if (isStraightFlush(hand)) {
            return (output+"Straight Flush!");
        } else if (isFourOfAKind(hand)) {
            return (output+"Four of a Kind!");
        } else if (isFullHouse(hand)) {
            return (output+"Full House!");
        } else if (isFlush(hand)) {
            return (output+"Flush!");
        } else if (isStraight(hand)) {
            return (output+"Straight!");
        } else if (isThreeOfAKind(hand)) {
            return (output+"Three of a Kind!");
        } else if (isTwoPair(hand)) {
            return (output+"Two Pair!");
        } else if (isOnePair(hand)) {
            return (output+"One Pair!");
        } else {
            return (output+"High Card!");
        }
    }

    private boolean isStraightFlush(List<Card> hand) {
        return isStraight(hand) && isFlush(hand);
    }

    private boolean isFourOfAKind(List<Card> hand) {
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.merge(card.getRank(), 1, Integer::sum);
        }

        for (Integer count : rankCount.values()) {
            if (count == 4) {
                return true;
            }
        }

        return false;
    }

    private boolean isFullHouse(List<Card> hand) {
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.merge(card.getRank(), 1, Integer::sum);
        }

        boolean hasThreeOfAKind = false;
        boolean hasPair = false;

        for (Integer count : rankCount.values()) {
            if (count == 3) {
                hasThreeOfAKind = true;
            } else if (count == 2) {
                hasPair = true;
            }
        }

        return hasThreeOfAKind && hasPair;
    }

    private boolean isFlush(List<Card> hand) {
        Suit firstSuit = hand.get(0).getSuit();
        for (Card card : hand) {
            if (card.getSuit() != firstSuit) {
                return false;
            }
        }
        return true;
    }

    private boolean isStraight(List<Card> hand) {
        hand.sort((card1, card2) -> card2.getRank().ordinal() - card1.getRank().ordinal());

        for (int i = 1; i < hand.size(); i++) {
            if (hand.get(i).getRank().ordinal() != hand.get(i - 1).getRank().ordinal() + 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isThreeOfAKind(List<Card> hand) {
        Map<Rank, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.merge(card.getRank(), 1, Integer::sum);
        }

        for (Integer count : rankCount.values()) {
            if (count == 3) {
                return true;
            }
        }

        return false;
    }

    private boolean isTwoPair(List<Card> hand) {
        Map<Rank, Integer> rankCount = new HashMap<>();
        int pairCount = 0;

        for (Card card : hand) {
            rankCount.merge(card.getRank(), 1, Integer::sum);
        }

        for (Integer count : rankCount.values()) {
            if (count == 2) {
                pairCount++;
            }
        }

        return pairCount == 2;
    }

    private boolean isOnePair(List<Card> hand) {
        Map<Rank, Integer> rankCount = new HashMap<>();

        for (Card card : hand) {
            rankCount.merge(card.getRank(), 1, Integer::sum);
        }

        for (Integer count : rankCount.values()) {
            if (count == 2) {
                return true;
            }
        }

        return false;
    }
}
