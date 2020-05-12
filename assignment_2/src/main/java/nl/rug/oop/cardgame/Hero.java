package nl.rug.oop.cardgame;

import lombok.Data;

/**
 * Hero class to store health, mana, deck, and deck hand
 */
@Data
public class Hero {

    private int health;
    private int mana;
    Deck deck;
    DeckHand deckHand;

    /**
     * Creates a new Hero
     * @param playerName Player name
     * @param health Player health
     * @param mana Player mana
     */
    public Hero(String playerName, int health, int mana) {
        this.deck = new Deck();
        this.deckHand = new DeckHand();
        this.mana = mana;
        this.health = health;
    }


}
