package nl.rug.oop.cardgame;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck class to store cards
 */
@Data
public class Deck {

    private ArrayList<String> deckList;

    /**
     * Creates a new Deck and fills it with cards and shuffles it
     */
    public Deck() {
        this.deckList = new ArrayList<String>();
        deckList.add("Monster");
        deckList.add("Monster");
        deckList.add("Spell");
        deckList.add("Spell");
        deckList.add("Monster");
        deckList.add("Monster");
        deckList.add("Spell");
        deckList.add("Spell");
        Collections.shuffle(deckList);
    }

    /**
     * Removes a card from the top of the deck and returns it
     * @return
     */
    public String drawCard() {
        String card;
        card = this.deckList.get(0);
        this.deckList.remove(0);
        return card;
    }


}
