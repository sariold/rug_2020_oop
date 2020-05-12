package nl.rug.oop.cardgame;

import lombok.Data;
import nl.rug.oop.cardgame.card.Card;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Player's deck hand
 */
@Data
public class DeckHand {

    private ArrayList<Card> deckHand;

    /**
     * Generates a player's deck hand
     */
    public DeckHand() {
        this.deckHand = new ArrayList<Card>();
    }

    /**
     * Adds a card to player's deck hand
     * @param card Takes a card
     */
    public void addCard(Card card) {
        if(this.deckHand.size() > DefaultStats.MAX_HAND_CARDS) System.out.println("You have too many cards in hand the " +
                "drawn card is dicarded!");
        else this.deckHand.add(card);
    }

    /**
     * Views the player's deck hand
     */
    public void viewHand() {
        System.out.println("Your hand contains:");
        if(this.deckHand.size() > 0) {
            for(int i = 0; i < this.getDeckHand().size(); i++) {
                System.out.println(i + ") " + this.getDeckHand().get(i).getName() + ": Mana Cost -> "
                        + this.getDeckHand().get(i).getCost());
            }
            System.out.println("");
        } else System.out.println("Nothing!");
    }

    /**
     * Removes a card from player's deck hand
     */
    public void discardCard() {
        if(this.deckHand.size() > 0) {
            Scanner scanner = new Scanner(System.in);
            viewHand();
            System.out.println("Which card would you like to discard?");
            boolean start = true;
            int currentMove = 0;
            while (start) {
                try {
                    currentMove = scanner.nextInt();
                    this.deckHand.remove(currentMove);
                    start = false;
                } catch (Exception e) {
                    System.out.println("NOT VALID INPUT!");
                }
            }
        } else System.out.println("Empty Hand!");
    }


}
