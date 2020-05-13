package nl.rug.oop.cardgame.model.deck;

import lombok.Data;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.card.SpellCard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Deck class to store cards
 */
@Data
public class Deck {

    private ArrayList<Card> deckList;

    /**
     * Creates a new Deck and fills it with cards and shuffles it
     */
    public Deck() {
        Image creature = null;
        Image spell = null;
        try {
            File pathToFile = new File("target" + File.separator + "classes" + File.separator + "textures"
                    + File.separator + "Creature_blank.jpg");
            creature = ImageIO.read(pathToFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            File pathToFile = new File("target" + File.separator + "classes" + File.separator + "textures"
                    + File.separator + "Spell_blank.jpg");
            spell = ImageIO.read(pathToFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.deckList = new ArrayList<>();
        deckList.add(new CreatureCard("Creature", 3, creature, 2, 2, false));
        deckList.add(new CreatureCard("Creature", 3, creature, 2, 2, false));
        deckList.add(new SpellCard("Spell", 4, spell));
        deckList.add(new SpellCard("Spell", 4, spell));
        deckList.add(new CreatureCard("Creature", 2, creature, 2, 2, false));
        deckList.add(new CreatureCard("Creature", 2, creature, 2, 2, false));
        deckList.add(new SpellCard("Spell", 4, spell));
        deckList.add(new SpellCard("Spell", 4, spell));
        Collections.shuffle(deckList);
    }

    /**
     * Removes a card from the top of the deck and returns it
     * @return Card drawn
     */
    public Card drawCard() {
        Card card;
        if (this.deckList.size() == 0) {
            System.out.println("Deck empty!");
            return null;
        }
        card = this.deckList.get(0);
        this.deckList.remove(0);
        return card;
    }


}
