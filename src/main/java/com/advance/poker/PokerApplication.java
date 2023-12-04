package com.advance.poker;

import com.advance.poker.application.PokerGame;
import com.advance.poker.model.Card;
import com.advance.poker.model.Deck;
import com.advance.poker.service.PokerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PokerApplication.class, args);

	}
}
