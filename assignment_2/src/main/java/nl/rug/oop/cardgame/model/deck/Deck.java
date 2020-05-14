package nl.rug.oop.cardgame.model.deck;

import lombok.Data;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.card.EnumCard;
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
        this.deckList = new ArrayList<>();
        deckList.add(new CreatureCard(EnumCard.CREATURE_ZOMBIE));
        deckList.add(new CreatureCard(EnumCard.CREATURE_ZOMBIE));
        deckList.add(new CreatureCard(EnumCard.CREATURE_ZOMBIE));

        deckList.add(new CreatureCard(EnumCard.CREATURE_WEREWOLF));
        deckList.add(new CreatureCard(EnumCard.CREATURE_WEREWOLF));
        deckList.add(new CreatureCard(EnumCard.CREATURE_WEREWOLF));

        deckList.add(new CreatureCard(EnumCard.CREATURE_WOLF));
        deckList.add(new CreatureCard(EnumCard.CREATURE_WOLF));
        deckList.add(new CreatureCard(EnumCard.CREATURE_WOLF));

        deckList.add(new CreatureCard(EnumCard.CREATURE_DRAGON));
        deckList.add(new CreatureCard(EnumCard.CREATURE_DRAGON));
        deckList.add(new CreatureCard(EnumCard.CREATURE_DRAGON));

        deckList.add(new CreatureCard(EnumCard.SPELL_COPYPASTE));
        deckList.add(new CreatureCard(EnumCard.SPELL_COPYPASTE));

        deckList.add(new CreatureCard(EnumCard.SPELL_INSTANTDAMAGE));
        deckList.add(new CreatureCard(EnumCard.SPELL_INSTANTDAMAGE));

        deckList.add(new CreatureCard(EnumCard.SPELL_INSTANTDRAW));
        deckList.add(new CreatureCard(EnumCard.SPELL_INSTANTDRAW));

        deckList.add(new CreatureCard(EnumCard.SPELL_COPYPASTE));
        deckList.add(new CreatureCard(EnumCard.SPELL_COPYPASTE));

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
