package nl.rug.oop.cardgame.model.deck;

import lombok.Data;
import nl.rug.oop.cardgame.model.card.*;

import java.util.ArrayList;

/**
 * Deck for the card collection
 */
@Data
public class CollectionDeck {

    private ArrayList<Card> deckList;
    private int startingCard;

    /**
     * Creates a new Deck and fills it with one of each card
     */
    public CollectionDeck() {
        this.startingCard = 0;
        this.deckList = new ArrayList<>();
        deckList.add(new CreatureCard(EnumCard.CREATURE_ZOMBIE));
        deckList.add(new CreatureCard(EnumCard.CREATURE_WEREWOLF));
        deckList.add(new CreatureCard(EnumCard.CREATURE_WOLF));
        deckList.add(new CreatureCard(EnumCard.CREATURE_DRAGON));
        deckList.add(new CreatureCard(EnumCard.CREATURE_ARCHER));
        deckList.add(new CreatureCard(EnumCard.CREATURE_GUARD));
        deckList.add(new CreatureCard(EnumCard.CREATURE_DEMON));
        deckList.add(new CreatureCard(EnumCard.CREATURE_TORTOISE));
        deckList.add(new CreatureCard(EnumCard.CREATURE_GARGOYLE));
        deckList.add(new CopySpell(EnumCard.SPELL_COPYPASTE));
        deckList.add(new HealthSpell(EnumCard.SPELL_INSTANTDAMAGE));
        deckList.add(new DrawSpell(EnumCard.SPELL_INSTANTDRAW));
        deckList.add(new HealthSpell(EnumCard.SPELL_INSTANTHEALTH));
        deckList.add(new HellFireSpell(EnumCard.SPELL_HELLFIRE));
        deckList.add(new DamageBuffSpell(EnumCard.SPELL_DAMAGEBUFF));
    }

    /**
     * Increases the card from which the collection will start showing the deck
     */
    public void increaseStartingCard() {
        if (startingCard < getDeckList().size()-4) startingCard++;
    }

    /**
     * Decreases the card from which the collection will start showing the deck
     */
    public void decreaseStartingCard() {
        if (startingCard > 0) startingCard--;
    }
}
