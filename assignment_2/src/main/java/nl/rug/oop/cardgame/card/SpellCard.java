package nl.rug.oop.cardgame.card;

import lombok.Data;
import nl.rug.oop.cardgame.Battlefield;

import java.awt.*;

/**
 * Spell card
 */
@Data
public class SpellCard extends Card{

    /**
     * Creates a new spell card
     * @param name Name
     * @param cost Cost
     * @param image Image
     */
    public SpellCard(String name, int cost, Image image) {
        super(name, cost, image);
    }

    /**
     * Play method
     */
    @Override
    public void play(Battlefield battlefield, int hero) {
        System.out.println("Played a spell you draw 2 cards");
        if (hero == 0) {
            for (int i = 0; i < 2; i++) {
                Card card = battlefield.getPlayer().getDeck().drawCard();
                System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
                battlefield.getPlayer().getDeckHand().addCard(card);
            }
            return;
        }
        for (int i = 0; i < 2; i++) {
            Card card = battlefield.getAi().getDeck().drawCard();
            System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
            battlefield.getAi().getDeckHand().addCard(card);
        }
    }
}
