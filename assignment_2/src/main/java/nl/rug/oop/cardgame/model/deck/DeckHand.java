package nl.rug.oop.cardgame.model.deck;

import lombok.Data;
import nl.rug.oop.cardgame.model.card.Card;

import nl.rug.oop.cardgame.DefaultStats;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Player's deck hand
 */
@Data
public class DeckHand {

    private HashMap<Integer, Card> deckHand;

    /**
     * Generates a player's deck hand
     */
    public DeckHand() {
        this.deckHand = new HashMap<>();
    }

    /**
     * Adds a card to player's deck hand
     * @param card Takes a card
     */
    public void addCard(Card card, DiscardDeck discardDeck) {
        if(this.deckHand.size() == DefaultStats.MAX_HAND_CARDS){
            System.out.println("You have too many cards in " +
                    "hand the drawn card is discarded!");
            discardDeck.discard(card);
        }
        else this.deckHand.put(card.getCardNumber(), card);
    }

    /**
     * Views the player's deck hand
     */
    public void viewHand() {
        System.out.println("Your hand contains:");
        if(this.deckHand.size() > 0) {
            for(Card card : this.getDeckHand().values()) {
                System.out.println(card.getCardNumber() + ") " + card.getName() + ": Mana Cost -> "
                        + card.getCost());
            }
            System.out.println();
        } else System.out.println("Nothing!");
    }

    /**
     * Removes a card from player's deck hand
     * @param discardDeck
     */
    public void discardCard(DiscardDeck discardDeck) {
        if(this.deckHand.size() > 0) {
            Scanner scanner = new Scanner(System.in);
            viewHand();
            System.out.println("Which card would you like to discard?");
            boolean start = true;
            int currentMove;
            while (start) {
                try {
                    currentMove = scanner.nextInt();
                    Card discarded = this.deckHand.get(currentMove);
                    this.deckHand.remove(currentMove);
                    discardDeck.discard(discarded);
                    start = false;
                } catch (Exception e) {
                    System.out.println("NOT VALID INPUT!");
                }
            }
        } else System.out.println("Empty Hand!");
    }


}
