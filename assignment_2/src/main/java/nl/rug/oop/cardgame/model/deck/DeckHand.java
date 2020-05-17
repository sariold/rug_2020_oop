package nl.rug.oop.cardgame.model.deck;

import lombok.Data;
import nl.rug.oop.cardgame.DefaultStats;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.hero.Hero;

import java.util.HashMap;

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
     * returns the next free postion in the players hand
     * @return Free Position
     */
    public int getNextFreePosition() {
        int freePosition = 0;
        for (Card card: this.getDeckHand().values()) freePosition++;
        return freePosition;
    }

    /**
     * Adds a card to player's deck hand
     *
     * @param card Takes a card
     */
    public void addCard(Card card, Hero hero) {
        if (this.deckHand.size() == DefaultStats.MAX_HAND_CARDS){
            System.out.println("You have too many cards in " +
                    "hand the drawn card is discarded!");
            hero.getDiscardDeck().discard(card);
        }
        else this.deckHand.put(card.getCardNumber(), card);
        System.out.println(card.getCardNumber());
    }

    /**
     * Views the player's deck hand
     */
    public void viewHand() {
        System.out.println("Your hand contains:");
        if (this.deckHand.size() > 0) {
            for (Card card : this.getDeckHand().values()) {
                System.out.println(card.getCardNumber() + ") " + card.getName() + ": Mana Cost -> "
                        + card.getCost());
            }
            System.out.println();
        } else System.out.println("Nothing!");
    }

    /**
     * Removes a card from player's deck hand
     *
     * @param discardDeck
     */
    public void discardCard(DiscardDeck discardDeck, int key) {
        if (this.deckHand.size() > 0) {
                Card discarded = this.deckHand.get(key);
                discarded.setHandPos(-1);
                this.deckHand.remove(key);
                discardDeck.discard(discarded);
        } else System.out.println("Empty Hand!");
    }

    /**
     * Returns the next free position in the hand
     * @return Free Position
     */
    public int freePostion() {
        int i;
        for (i = 0; i < this.getDeckHand().values().size(); i++) { }
        return i;
    }


}
