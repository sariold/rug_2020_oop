package nl.rug.oop.cardgame;

import lombok.Data;

import java.util.Scanner;

/**
 * Hero class to store health, mana, deck, and deck hand
 */
@Data
public class Hero {

    private int health;
    private int mana;
    private int maxMana;
    Deck deck;
    DeckHand deckHand;

    /**
     * Creates a new Hero
     * @param playerName Player name
     * @param health Player health
     * @param mana Player mana
     * @param maxMana Player max mana
     */
    public Hero(String playerName, int health, int mana, int maxMana) {
        this.deck = new Deck();
        this.deckHand = new DeckHand();
        this.mana = mana;
        this.maxMana = maxMana;
        this.health = health;
    }

    /**
     * Play a card
     */
    public void playCard() {
        if(this.deckHand.getDeckHand().size() > 0) {
            Scanner scanner = new Scanner(System.in);
            this.deckHand.viewHand();
            System.out.println("Which card would you like to play?");
            boolean start = true;
            int currentMove = 0;
            while (start) {
                try {
                    currentMove = scanner.nextInt();
                    if(this.deckHand.getDeckHand().get(currentMove).getCost() <= this.mana) {
                        this.deckHand.getDeckHand().get(currentMove).play();
                        this.deckHand.getDeckHand().remove(currentMove);
                    } else System.out.println("You cease to have enough mana!");
                    start = false;
                } catch (Exception e) {
                    System.out.println("NOT VALID INPUT!");
                }
            }
        } else System.out.println("Empty Hand!");
    }


}
