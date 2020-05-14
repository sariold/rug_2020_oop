package nl.rug.oop.cardgame.model.deck;

import lombok.Data;
import nl.rug.oop.cardgame.model.card.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

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

        deckList.add(new CopySpell(EnumCard.SPELL_COPYPASTE));
        deckList.add(new CopySpell(EnumCard.SPELL_COPYPASTE));

        deckList.add(new HealthSpell(EnumCard.SPELL_INSTANTDAMAGE));
        deckList.add(new HealthSpell(EnumCard.SPELL_INSTANTDAMAGE));

        deckList.add(new DrawSpell(EnumCard.SPELL_INSTANTDRAW));
        deckList.add(new DrawSpell(EnumCard.SPELL_INSTANTDRAW));

        deckList.add(new HealthSpell(EnumCard.SPELL_INSTANTHEALTH));
        deckList.add(new HealthSpell(EnumCard.SPELL_INSTANTHEALTH));

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
