package nl.rug.oop.cardgame;

import lombok.Data;
import nl.rug.oop.cardgame.card.Card;

import java.util.Scanner;

/**
 * Hero class to store health, mana, deck, and deck hand
 */
@Data
public class Hero {

    protected String name;
    protected int health;
    protected int mana;
    protected int maxMana;
    protected Deck deck;
    protected DeckHand deckHand;

    /**
     * Creates a new Hero
     * @param playerName Player name
     * @param health Player health
     * @param mana Player mana
     * @param maxMana Player max mana
     */
    public Hero(String playerName, int health, int mana, int maxMana) {
        this.name = playerName;
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

    /**
     * Present the player with game options
     * @param battlefield Playing board
     */
    public void takeTurn(Battlefield battlefield) {
        Scanner scanner = new Scanner(System.in);
        boolean start = true;
        int currentMove = 0;
        System.out.println(this.name + "'s TURN!");
        while (start) {
            System.out.println("Current Mana: " + this.getMana());
            System.out.println("0) Draw a Card");
            System.out.println("1) Check Hand");
            System.out.println("2) Play a Card");
            System.out.println("3) Discard a Card");
            System.out.println("4) End turn");
            try {
                currentMove = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("NOT VALID INPUT!");
            }
            switch (currentMove) {
                case 0:
                    Card card = this.getDeck().drawCard();
                    System.out.println("Drawing card: " + card.getName());
                    this.getDeckHand().addCard(card);
                    break;
                case 1:
                    this.getDeckHand().viewHand();
                    break;
                case 2:
                    this.playCard();
                    battlefield.setPlayerTurn(false);
                    start = false;
                    break;
                case 3:
                    this.getDeckHand().discardCard();
                    break;
                case 4:
                    battlefield.setPlayerTurn(false);
                    start = false;
                    break;
            }
        }
    }


}
