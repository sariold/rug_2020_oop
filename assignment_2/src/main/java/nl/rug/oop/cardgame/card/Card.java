package nl.rug.oop.cardgame.card;

import lombok.Data;
import nl.rug.oop.cardgame.battlefield.Battlefield;
import nl.rug.oop.cardgame.interfaces.Playable;

import java.awt.*;

/**
 * A card used in the game
 */
@Data
public abstract class Card implements Playable {

        protected String name;
        protected int cost;
        protected Image image;

    /**
     * Creates a new Card
     * @param name Name
     * @param cost Cost
     * @param image Image
     */
    public Card(String name, int cost, Image image) {
        this.name = name;
        this.cost = cost;
        this.image = image;
    }

    /**
     * Play method
     */
    @Override
    public void play(Battlefield battlefield, int hero) {
        System.out.println("You played " + this.name);
    }

    /**
     *
     * @return Card name
     */
    public String getName() {
        return name;
    }
}
