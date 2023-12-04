package com.advance.poker.controller;

import com.advance.poker.application.PokerGame;
import com.advance.poker.model.Card;
import com.advance.poker.service.PokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/poker")
public class PokerController {

    @Autowired
    private PokerService pokerService;

    @Autowired
    private PokerGame pokerGame;

    @GetMapping("/play")
    public String playEndpoint(@RequestParam(defaultValue = "5") int handSize) {
        return pokerGame.play(handSize);
    }

    @GetMapping("/dealDeck")
    public List<Card> dealDeckEndpoint() {
        return pokerService.dealDeck();
    }

    @GetMapping("/dealHand")
    public List<Card> dealHandEndpoint(@RequestParam(defaultValue = "5") int handSize) {
        return pokerService.dealHand(handSize);
    }
}
